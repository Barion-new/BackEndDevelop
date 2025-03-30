package me.barion.capstoneprojectbarion.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "store_id", nullable = false)
    private Integer storeId;

    @Column(name = "employee_name", nullable = false, length = 100)
    private String employeeName;

    @Column(name = "phone_number", nullable = false, length = 100)
    private String phoneNumber;

    @Column(name = "hourly_wage", nullable = false)
    private Integer hourlyWage;

    @Column(name = "position", nullable = false, length = 100)
    private String position;

    @Column(name = "bank_account", nullable = false, length = 100)
    private String bankAccount;

}
