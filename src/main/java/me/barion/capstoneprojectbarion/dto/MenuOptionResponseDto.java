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

    public static MenuOptionResponseDto fromEntity(MenuOption option) {
        return MenuOptionResponseDto.builder()
                .optionId(option.getOptionId())
                .optionName(option.getOptionName())
                .quantity(option.getQuantity())
                .build();
    }
}