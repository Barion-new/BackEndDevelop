package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import me.barion.capstoneprojectbarion.repository.EmployeeRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // 기본 CRUD 메소드 자동 제공
}

