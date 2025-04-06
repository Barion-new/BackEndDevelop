package me.barion.capstoneprojectbarion.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders") // SQL 예약어 충돌로 orders로 변경
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    // Store와 N:1 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "store_id")
    private Store store;


    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "total_amount")
    private Integer totalAmount;

    @Column(name = "order_status", length = 100)
    private String orderStatus;

    public Order() {
    }

    public Order(Store store, LocalDateTime orderDate, Integer totalAmount, String orderStatus) {
        this.store = store;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }
}

