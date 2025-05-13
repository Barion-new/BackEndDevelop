package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "주문 생성", description = "새로운 주문을 생성하고 생성된 주문의 URI를 Location 헤더에 반환합니다.")
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto dto) {
        OrderDto saved = orderService.createOrder(dto);
        URI location = URI.create("/orders/" + saved.getOrderId());
        return ResponseEntity
                .created(location)   // 상태코드 201
                .body(saved);
    }
    @Operation(summary = "단일 주문 조회", description = "orderId에 해당하는 주문 상세 정보를 조회합니다.")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }

    @Operation(summary = "매장별 주문 조회", description = "storeId에 해당하는 매장의 모든 주문 목록을 반환합니다.")
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<OrderDto>> getByStore(@PathVariable Integer storeId) {
        return ResponseEntity.ok(orderService.getOrdersByStoreId(storeId));
    }

    @Operation(summary = "주문 수정", description = "orderId에 해당하는 주문 정보를 수정하고 수정된 정보를 반환합니다.")
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(
            @PathVariable Integer orderId,
            @RequestBody OrderDto dto) {
        return ResponseEntity.ok(orderService.updateOrder(orderId, dto));
    }

    @Operation(summary = "주문 삭제", description = "orderId에 해당하는 주문을 삭제합니다.")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}

