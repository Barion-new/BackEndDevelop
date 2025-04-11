
package me.barion.capstoneprojectbarion.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int salesId;

    @Column(nullable = false)
    private LocalDateTime salesDate;

    @Column(nullable = false)
    private int totalSales;

    @Column(nullable = false)
    private int totalCost;

    @Column(nullable = false)
    private int profit;

    @Column(nullable = false)
    private int hourlySales;

    @Column(nullable = false)
    private int hourlyProfit;

    @Column(nullable = false)
    private int dailySales;

    @Column(nullable = false)
    private int dailyProfit;

    @Column(nullable = false)
    private int monthlySales;

    @Column(nullable = false)
    private int monthlyProfit;

    @Column(nullable = false)
    private int yearlySales;

    @Column(nullable = false)
    private int yearlyProfit;

}