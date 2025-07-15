package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional; // Optional 임포트 추가

public interface StoreRepository extends JpaRepository<Store, Integer> {
    Optional<Store> findByStoreName(String storeName);
}
