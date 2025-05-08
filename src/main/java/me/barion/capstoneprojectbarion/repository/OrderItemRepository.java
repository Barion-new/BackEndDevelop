package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
