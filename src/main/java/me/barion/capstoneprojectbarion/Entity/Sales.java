package me.barion.capstoneprojectbarion.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer salesId;

    @Column(nullable = false)
    private LocalDateTime salesDate; // 매출 기준 날짜/시간

    @Column(nullable = false)
    private Integer totalSales; // 총 매출 금액

    @Column(nullable = false)
    private Integer totalCost; // 총 비용

    public Sales() {
    }

    public Sales(LocalDateTime salesDate, Integer totalSales, Integer totalCost) {
        this.salesDate = salesDate;
        this.totalSales = totalSales;
        this.totalCost = totalCost;
    }
}
