package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // JpaRepository를 상속받으면 기본적인 CRUD 메소드가 자동으로 제공됩니다.

    List<Order> findByStoreStoreId(Integer storeId);
}
