package me.barion.capstoneprojectbarion.dto;


import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto {
    private Long menuId;
    private String menuName;    // 응답 시 포함
    private Integer quantity;
    private Integer unitPrice;
    private Integer totalPrice; // ★ 추가
}