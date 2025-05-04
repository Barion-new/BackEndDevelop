package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.Entity.Menu;
import me.barion.capstoneprojectbarion.Entity.MenuOption;
import me.barion.capstoneprojectbarion.dto.MenuOptionRequestDto;
import me.barion.capstoneprojectbarion.repository.MenuOptionRepository;
import me.barion.capstoneprojectbarion.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuOptionService {
    private final MenuRepository menuRepository;
    private final MenuOptionRepository optionRepository;

    public MenuOptionService(MenuRepository menuRepository, MenuOptionRepository optionRepository) {
        this.menuRepository = menuRepository;
        this.optionRepository = optionRepository;
    }

    @Transactional
    public MenuOption createOption(MenuOptionRequestDto dto) {
        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id: " + dto.getMenuId()));

        MenuOption option = new MenuOption();
        option.setMenu(menu);
        option.setOptionName(dto.getOptionName());
        option.setQuantity(dto.getQuantity());

        return optionRepository.save(option);
    }

    @Transactional(readOnly = true)
    public List<MenuOption> getMenuOptions(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id: " + menuId));
        return menu.getOptions();
    }

    @Transactional
    public MenuOption updateOption(Long optionId, MenuOptionRequestDto dto) {
        MenuOption option = optionRepository.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("Option not found with id: " + optionId));

        Menu menu = menuRepository.findById(dto.getMenuId())
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id: " + dto.getMenuId()));

        option.setMenu(menu);
        option.setOptionName(dto.getOptionName());
        option.setQuantity(dto.getQuantity());

        return optionRepository.save(option);
    }

    @Transactional
    public void deleteOption(Long optionId) {
        if (!optionRepository.existsById(optionId)) {
            throw new IllegalArgumentException("Option not found with id: " + optionId);
        }
        optionRepository.deleteById(optionId);
    }
}


