package me.barion.capstoneprojectbarion.service;

import jakarta.persistence.EntityNotFoundException;
import me.barion.capstoneprojectbarion.dto.OrderDto;
import me.barion.capstoneprojectbarion.Entity.Order;
import me.barion.capstoneprojectbarion.Entity.Store;
import me.barion.capstoneprojectbarion.repository.OrderRepository;
import me.barion.capstoneprojectbarion.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, StoreRepository storeRepository) {
        this.orderRepository = orderRepository;
        this.storeRepository = storeRepository;
    }

    /**
     * 주문 생성
     */
    @Transactional
    public Order createOrder(OrderDto orderDto) {
        // storeId를 통해 Store 엔티티를 가져옴
        Store store = storeRepository.findById(orderDto.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + orderDto.getStoreId()));

        Order order = new Order();
        order.setStore(store);
        order.setOrderDate(orderDto.getOrderDate());
        order.setTotalAmount(orderDto.getTotalAmount());
        order.setOrderStatus(orderDto.getOrderStatus());

        return orderRepository.save(order);
    }

    /**
     * 주문 조회 (단건)
     */
    @Transactional(readOnly = true)
    public Order getOrder(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));
    }



    @Transactional(readOnly = true)
    public List<Order> getOrdersByStoreId(Integer storeId) {
        return (List<Order>) orderRepository.findByStoreStoreId(storeId);
    }


    /**
     * 주문 수정
     */
    @Transactional
    public Order updateOrder(Integer orderId, OrderDto orderDto) {
        // 기존 주문 정보 조회
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderId));

        // storeId가 변경될 수 있다면, Store도 다시 매핑
        if (orderDto.getStoreId() != null) {
            Store store = storeRepository.findById(orderDto.getStoreId())
                    .orElseThrow(() -> new EntityNotFoundException("Store not found with id: " + orderDto.getStoreId()));
            order.setStore(store);
        }

        if (orderDto.getOrderDate() != null) {
            order.setOrderDate(orderDto.getOrderDate());
        }
        if (orderDto.getTotalAmount() != null) {
            order.setTotalAmount(orderDto.getTotalAmount());
        }
        if (orderDto.getOrderStatus() != null) {
            order.setOrderStatus(orderDto.getOrderStatus());
        }

        return orderRepository.save(order);
    }

    /**
     * 주문 삭제
     */
    @Transactional
    public void deleteOrder(Integer orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new EntityNotFoundException("Order not found with id: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }
}
