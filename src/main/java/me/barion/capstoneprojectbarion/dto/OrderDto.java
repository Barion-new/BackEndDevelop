package me.barion.capstoneprojectbarion.dto;


import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderDto {
    private Integer orderId;
    private Integer storeId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private List<OrderItemDto> items;
    private Integer totalAmount;
}
