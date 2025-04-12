
package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Integer> {
    // 기존 메서드들
    List<Order> findByStoreStoreId(Integer storeId);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o")
    int sumTotalAmount();



}