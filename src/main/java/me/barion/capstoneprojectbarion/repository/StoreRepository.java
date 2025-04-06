package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
    // 기본 CRUD 메소드 자동 제공
}
