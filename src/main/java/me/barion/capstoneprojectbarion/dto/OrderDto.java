package me.barion.capstoneprojectbarion.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    private Integer storeId;
    private LocalDateTime orderDate;
    private Integer totalAmount;
    private String orderStatus;
}
