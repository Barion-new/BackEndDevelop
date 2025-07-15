package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.Entity.Store;
import me.barion.capstoneprojectbarion.repository.StoreRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StoreDetailsServiceImpl implements UserDetailsService {

    private final StoreRepository storeRepository;

    public StoreDetailsServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String storeName) throws UsernameNotFoundException {
        Store store = storeRepository.findByStoreName(storeName)
                .orElseThrow(() -> new UsernameNotFoundException("가게를 찾을 수 없습니다: " + storeName));

        return User.builder()
                .username(store.getStoreName())
                .password(store.getPassword())
                .roles("USER") // 모든 가게에 기본 'USER' 권한 부여
                .build();
    }
}
