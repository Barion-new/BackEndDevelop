package me.barion.capstoneprojectbarion.dto;

import lombok.*;
import me.barion.capstoneprojectbarion.Entity.Menu;
import me.barion.capstoneprojectbarion.Entity.MenuOption;

import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuResponseDto {
    private Long menuId;
    private int category;
    private int orderId;
    private String menuName;
    private int price;
    private int cost;
    private String menuPresent;
    private String menuImage;
    private List<MenuOptionResponseDto> options;

    public static MenuResponseDto fromEntity(Menu menu) {
        List<MenuOptionResponseDto> optionDtos = menu.getOptions() != null ?
                menu.getOptions().stream()
                        .map(MenuOptionResponseDto::fromEntity)
                        .collect(Collectors.toList()) :
                null;

        return MenuResponseDto.builder()
                .menuId(menu.getMenuId())
                .category(menu.getCategory())
                .orderId(menu.getOrderId())
                .menuName(menu.getMenuName())
                .price(menu.getPrice())
                .cost(menu.getCost())
                .menuPresent(menu.getMenuPresent())
                .menuImage(menu.getMenuImage())
                .options(optionDtos)
                .build();
    }
}