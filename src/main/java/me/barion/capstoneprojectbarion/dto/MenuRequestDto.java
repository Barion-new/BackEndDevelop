package me.barion.capstoneprojectbarion.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class MenuRequestDto {
    private int category;
//    private int orderId;
    private String menuName;
    private int price;
    private int cost;
    private String menuPresent;
    private String base64Image; // base64로 인코딩된 이미지
}
