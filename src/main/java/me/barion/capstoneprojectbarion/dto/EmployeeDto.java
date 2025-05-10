package me.barion.capstoneprojectbarion.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDto {

    private Integer employeeId;   // 직원 ID
    private Integer storeId;      // 매장 ID
    private String employeeName;  // 직원 이름
    private String phoneNumber;   // 전화번호
    private Integer salary;   // 시간당 급여
    private String position;      // 직책
    private String bankAccount;   // 은행 계좌

}
