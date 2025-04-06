package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.Entity.Employee;
import me.barion.capstoneprojectbarion.dto.EmployeeDto;
import me.barion.capstoneprojectbarion.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // 모든 직원 조회
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    // 특정 직원 조회
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 직원이 존재하지 않습니다: " + id));
    }


    // 직원 생성
    @Transactional
    public Employee createEmployee(EmployeeDto employeeDto) {
        if (employeeDto.getEmployeeId() != null && employeeRepository.existsById(employeeDto.getEmployeeId())) {
            throw new IllegalArgumentException("이미 존재하는 ID: " + employeeDto.getEmployeeId());
        }

        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setStoreId(employeeDto.getStoreId());
        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setHourlyWage(employeeDto.getHourlyWage());
        employee.setPosition(employeeDto.getPosition());
        employee.setBankAccount(employeeDto.getBankAccount());

        return employeeRepository.save(employee);
    }

    // 직원 삭제
    @Transactional
    public void deleteEmployee(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 직원이 없습니다: " + id);
        }
        employeeRepository.deleteById(id);
    }
}
