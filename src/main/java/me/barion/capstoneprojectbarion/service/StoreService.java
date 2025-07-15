package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.Entity.Store;
import me.barion.capstoneprojectbarion.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StoreService(StoreRepository storeRepository, PasswordEncoder passwordEncoder) {
        this.storeRepository = storeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Store createStore(String storeName, String password) {
        if (storeRepository.findByStoreName(storeName).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 가게 이름입니다.");
        }
        String encodedPassword = passwordEncoder.encode(password);
        Store newStore = new Store(storeName, null, encodedPassword, false); // storeType은 나중에, onboarding은 false로 시작
        return storeRepository.save(newStore);
    }

    @Transactional
    public Store updateStoreName(Integer storeId, String storeName) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("가게를 찾을 수 없습니다: " + storeId)); // storeName 대신 storeId로 변경

        store.updateStoreName(storeName);

        return storeRepository.save(store);
    }
    @Transactional
    public Store updateStoreType(Integer storeId, String storeType) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("가게를 찾을 수 없습니다: " + storeId));

        store.updateStoreType(storeType);

        return storeRepository.save(store);
    }

    @Transactional(readOnly = true)
    public Boolean getOnboardingStatus(Integer storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("가게를 찾을 수 없습니다: " + storeId));
        return store.getOnboardingStatus();
    }
}
