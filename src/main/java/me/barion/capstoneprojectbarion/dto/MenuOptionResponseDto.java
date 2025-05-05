package me.barion.capstoneprojectbarion.dto;

import lombok.*;
import me.barion.capstoneprojectbarion.Entity.MenuOption;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOptionResponseDto {
    private Long optionId;
    private String optionName;
    private int quantity;
    private Long menuId; // 메뉴 ID만 포함 (전체 객체 대신)

    public static MenuOptionResponseDto fromEntity(MenuOption option) {
        return MenuOptionResponseDto.builder()
                .optionId(option.getOptionId())
                .optionName(option.getOptionName())
                .quantity(option.getQuantity())
                .menuId(option.getMenu() != null ? option.getMenu().getMenuId() : null)
                .build();
    }
}