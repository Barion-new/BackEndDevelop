package me.barion.capstoneprojectbarion.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
public class MenuOptionRequestDto {
    private Long menuId;     // 옵션이 속한 메뉴의 ID
    private String optionName;  // 옵션 이름 (예: "샷 추가", "시럽 추가" 등)
    private int quantity;    // 옵션 수량
}