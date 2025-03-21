
package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.Entity.Store;
import me.barion.capstoneprojectbarion.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public Store updateStoreName(Integer storeId, String storeName) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new EntityNotFoundException("가게를 찾을 수 없습니다: " + storeName));

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

}
