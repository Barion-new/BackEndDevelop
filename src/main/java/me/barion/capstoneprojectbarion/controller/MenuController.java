package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.barion.capstoneprojectbarion.Entity.Menu;
import me.barion.capstoneprojectbarion.dto.MenuOptionRequestDto;
import me.barion.capstoneprojectbarion.dto.MenuOptionResponseDto;
import me.barion.capstoneprojectbarion.dto.MenuRequestDto;
import me.barion.capstoneprojectbarion.dto.MenuResponseDto;
import me.barion.capstoneprojectbarion.service.MenuOptionService;
import me.barion.capstoneprojectbarion.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/menus")
public class MenuController {
    private final MenuService menuService;
    private final MenuOptionService menuOptionService;

    public MenuController(MenuService menuService, MenuOptionService menuOptionService) {
        this.menuService = menuService;
        this.menuOptionService = menuOptionService;
    }

    @Operation(summary = "메뉴 생성", description = "Base64로 인코딩된 이미지를 포함하여 새로운 메뉴를 생성합니다.")
    @PostMapping
    public ResponseEntity<MenuResponseDto> createMenu(@RequestBody MenuRequestDto dto) throws IOException {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    @Operation(summary = "모든 메뉴 조회", description = "페이지 단위로 전체 메뉴를 조회하며, category 파라미터로 카테고리별 필터링이 가능합니다.")
    @GetMapping
    public ResponseEntity<Page<MenuResponseDto>> getAllMenus(
            @PageableDefault(size = 100) Pageable pageable,
            @RequestParam(required = false) Integer category) {
        if (category != null) {
            return ResponseEntity.ok(menuService.getMenusByCategory(category, pageable));
        }
        return ResponseEntity.ok(menuService.getAllMenus(pageable));
    }

    @Operation(summary = "단일 메뉴 조회", description = "menuId에 해당하는 메뉴 정보를 반환합니다.")
    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long menuId) {
        return ResponseEntity.ok(menuService.getMenu(menuId));
    }

    @Operation(summary = "메뉴 수정", description = "기존 메뉴 정보를 수정하며, Base64 이미지가 함께 전송될 경우 이미지를 업데이트합니다.")
    @PutMapping("/{menuId}")
    public ResponseEntity<MenuResponseDto> updateMenu(
            @PathVariable Long menuId,
            @RequestBody MenuRequestDto dto) throws IOException {
        return ResponseEntity.ok(menuService.updateMenu(menuId, dto));
    }

    @Operation(summary = "메뉴 삭제", description = "menuId에 해당하는 메뉴를 삭제합니다.")
    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "메뉴 옵션 생성", description = "특정 메뉴(menuId)에 새로운 옵션을 추가합니다.")
    @PostMapping("/{menuId}/options")
    public ResponseEntity<MenuOptionResponseDto> createOption(
            @PathVariable Long menuId,
            @RequestBody MenuOptionRequestDto dto) {
        dto.setMenuId(menuId);
        return ResponseEntity.ok(menuOptionService.createOption(dto));
    }

    @Operation(summary = "메뉴 옵션 조회", description = "특정 메뉴(menuId)에 등록된 모든 옵션 목록을 반환합니다.")
    @GetMapping("/{menuId}/options")
    public ResponseEntity<List<MenuOptionResponseDto>> getMenuOptions(@PathVariable Long menuId) {
        return ResponseEntity.ok(menuOptionService.getMenuOptions(menuId));
    }

    @Operation(summary = "메뉴 옵션 수정", description = "특정 옵션(optionId)에 대한 정보를 수정합니다.")
    @PutMapping("/{menuId}/options/{optionId}")
    public ResponseEntity<MenuOptionResponseDto> updateOption(
            @PathVariable Long menuId,
            @PathVariable Long optionId,
            @RequestBody MenuOptionRequestDto dto) {
        dto.setMenuId(menuId);
        return ResponseEntity.ok(menuOptionService.updateOption(optionId, dto));
    }

    @Operation(summary = "메뉴 옵션 삭제", description = "특정 옵션(optionId)을 삭제합니다.")
    @DeleteMapping("/{menuId}/options/{optionId}")
    public ResponseEntity<Void> deleteOption(
            @PathVariable Long menuId,
            @PathVariable Long optionId) {
        menuOptionService.deleteOption(optionId);
        return ResponseEntity.noContent().build();
    }
}

