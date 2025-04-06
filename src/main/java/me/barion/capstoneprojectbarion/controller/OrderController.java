package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import me.barion.capstoneprojectbarion.dto.OrderDto;
import me.barion.capstoneprojectbarion.Entity.Order;
import me.barion.capstoneprojectbarion.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "주문 생성", description = "새로운 주문을 생성합니다.")
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto) {
        Order createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(createdOrder);
    }

    @Operation(summary = "주문 조회", description = "주문 정보를 단건 조회합니다.")
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer orderId) {
        try {
            Order order = orderService.getOrder(orderId);
            return ResponseEntity.ok(order);
        } catch (EntityNotFoundException | NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "매장의 모든 주문 조회", description = "storeId로 해당 매장의 모든 주문을 조회합니다.")
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<Order>> getOrdersByStoreId(@PathVariable Integer storeId) {
        List<Order> orders = orderService.getOrdersByStoreId(storeId);
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "주문 수정", description = "주문 정보를 수정합니다.")
    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Integer orderId,
                                             @RequestBody OrderDto orderDto) {
        try {
            Order updatedOrder = orderService.updateOrder(orderId, orderDto);
            return ResponseEntity.ok(updatedOrder);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "주문 삭제", description = "주문 정보를 삭제합니다.")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        try {
            orderService.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
