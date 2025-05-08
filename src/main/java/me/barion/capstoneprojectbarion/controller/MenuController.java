package me.barion.capstoneprojectbarion.controller;

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

    // 메뉴 생성 (base64 이미지 포함)
    @PostMapping
    public ResponseEntity<MenuResponseDto> createMenu(@RequestBody MenuRequestDto dto) throws IOException {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    // 모든 메뉴 조회
    @GetMapping
    public ResponseEntity<Page<MenuResponseDto>> getAllMenus(
            @PageableDefault(size = 10) Pageable pageable,
            @RequestParam(required = false) Integer category) {
        if (category != null) {
            return ResponseEntity.ok(menuService.getMenusByCategory(category, pageable));
        }
        return ResponseEntity.ok(menuService.getAllMenus(pageable));
    }

    // 특정 메뉴 조회
    @GetMapping("/{menuId}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable Long menuId) {
        return ResponseEntity.ok(menuService.getMenu(menuId));
    }

    // 메뉴 수정
    @PutMapping("/{menuId}")
    public ResponseEntity<MenuResponseDto> updateMenu(
            @PathVariable Long menuId,
            @RequestBody MenuRequestDto dto) throws IOException {
        return ResponseEntity.ok(menuService.updateMenu(menuId, dto));
    }

    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.noContent().build();
    }

    // 메뉴 옵션 생성 - 수정된 부분
    @PostMapping("/{menuId}/options")
    public ResponseEntity<MenuOptionResponseDto> createOption(
            @PathVariable Long menuId,
            @RequestBody MenuOptionRequestDto dto) {
        dto.setMenuId(menuId);
        return ResponseEntity.ok(menuOptionService.createOption(dto));
    }

    // 메뉴 옵션 조회 - 수정된 부분
    @GetMapping("/{menuId}/options")
    public ResponseEntity<List<MenuOptionResponseDto>> getMenuOptions(@PathVariable Long menuId) {
        return ResponseEntity.ok(menuOptionService.getMenuOptions(menuId));
    }

    // 메뉴 옵션 수정 - 수정된 부분
    @PutMapping("/{menuId}/options/{optionId}")
    public ResponseEntity<MenuOptionResponseDto> updateOption(
            @PathVariable Long menuId,
            @PathVariable Long optionId,
            @RequestBody MenuOptionRequestDto dto) {
        dto.setMenuId(menuId);
        return ResponseEntity.ok(menuOptionService.updateOption(optionId, dto));
    }

    // 메뉴 옵션 삭제
    @DeleteMapping("/{menuId}/options/{optionId}")
    public ResponseEntity<Void> deleteOption(
            @PathVariable Long menuId,
            @PathVariable Long optionId) {
        menuOptionService.deleteOption(optionId);
        return ResponseEntity.noContent().build();
    }
}
