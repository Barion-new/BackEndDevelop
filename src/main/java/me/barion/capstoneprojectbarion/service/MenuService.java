package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.Entity.Menu;
import me.barion.capstoneprojectbarion.dto.MenuRequestDto;
import me.barion.capstoneprojectbarion.dto.MenuResponseDto;
import me.barion.capstoneprojectbarion.repository.MenuRepository;
import me.barion.capstoneprojectbarion.s3.S3Uploader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final S3Uploader s3Uploader;

    public MenuService(MenuRepository menuRepository, S3Uploader s3Uploader) {
        this.menuRepository = menuRepository;
        this.s3Uploader = s3Uploader;
    }

    @Transactional
    public MenuResponseDto createMenu(MenuRequestDto dto) throws IOException {
        Menu menu = new Menu();
        menu.setMenuName(dto.getMenuName());
        menu.setCategory(dto.getCategory());
        menu.setOrderId(dto.getOrderId());
        menu.setPrice(dto.getPrice());
        menu.setCost(dto.getCost());
        menu.setMenuPresent(dto.getMenuPresent());

        // base64 이미지 업로드
        if (StringUtils.hasText(dto.getBase64Image())) {
            String imageUrl = s3Uploader.uploadBase64Image(dto.getBase64Image(), "menu-images");
            menu.setMenuImage(imageUrl);
        }

        Menu savedMenu = menuRepository.save(menu);
        return MenuResponseDto.fromEntity(savedMenu);
    }

    @Transactional(readOnly = true)
    public MenuResponseDto getMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id: " + menuId));
        return MenuResponseDto.fromEntity(menu);
    }

    @Transactional(readOnly = true)
    public Page<MenuResponseDto> getAllMenus(Pageable pageable) {
        return menuRepository.findAll(pageable)
                .map(MenuResponseDto::fromEntity);
    }

    @Transactional(readOnly = true)
    public Page<MenuResponseDto> getMenusByCategory(int category, Pageable pageable) {
        return menuRepository.findByCategory(category, pageable)
                .map(MenuResponseDto::fromEntity);
    }

    @Transactional
    public MenuResponseDto updateMenu(Long menuId, MenuRequestDto dto) throws IOException {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id: " + menuId));

        menu.setMenuName(dto.getMenuName());
        menu.setCategory(dto.getCategory());
        menu.setOrderId(dto.getOrderId());
        menu.setPrice(dto.getPrice());
        menu.setCost(dto.getCost());
        menu.setMenuPresent(dto.getMenuPresent());

        // base64 이미지 업로드 (새 이미지가 있을 경우)
        if (StringUtils.hasText(dto.getBase64Image())) {
            String imageUrl = s3Uploader.uploadBase64Image(dto.getBase64Image(), "menu-images");
            menu.setMenuImage(imageUrl);
        }

        Menu updatedMenu = menuRepository.save(menu);
        return MenuResponseDto.fromEntity(updatedMenu);
    }

    @Transactional
    public void deleteMenu(Long menuId) {
        if (!menuRepository.existsById(menuId)) {
            throw new IllegalArgumentException("Menu not found with id: " + menuId);
        }
        menuRepository.deleteById(menuId);
    }
}

