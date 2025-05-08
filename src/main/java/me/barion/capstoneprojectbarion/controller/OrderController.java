package me.barion.capstoneprojectbarion.controller;

import me.barion.capstoneprojectbarion.dto.OrderDto;
import me.barion.capstoneprojectbarion.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto) {
        OrderDto saved = orderService.createOrder(dto);
        URI location = URI.create("/orders/" + saved.getOrderId());
        return ResponseEntity
                .created(location)   // 상태코드 201
                .body(saved);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<OrderDto>> getByStore(@PathVariable Integer storeId) {
        return ResponseEntity.ok(orderService.getOrdersByStoreId(storeId));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(
            @PathVariable Integer orderId,
            @RequestBody OrderDto dto) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, dto));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

