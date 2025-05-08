package me.barion.capstoneprojectbarion.service;

import jakarta.persistence.EntityNotFoundException;
import me.barion.capstoneprojectbarion.dto.OrderDto;
import me.barion.capstoneprojectbarion.dto.OrderItemDto;
import me.barion.capstoneprojectbarion.Entity.Menu;
import me.barion.capstoneprojectbarion.Entity.Order;
import me.barion.capstoneprojectbarion.Entity.OrderItem;
import me.barion.capstoneprojectbarion.Entity.Store;
import me.barion.capstoneprojectbarion.repository.MenuRepository;
import me.barion.capstoneprojectbarion.repository.OrderRepository;
import me.barion.capstoneprojectbarion.repository.StoreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final StoreRepository storeRepo;
    private final MenuRepository menuRepo;

    public OrderService(OrderRepository orderRepo, StoreRepository storeRepo, MenuRepository menuRepo) {
        this.orderRepo = orderRepo;
        this.storeRepo = storeRepo;
        this.menuRepo = menuRepo;
    }

    @Transactional
    public OrderDto createOrder(OrderDto dto) {
        Store store = storeRepo.findById(dto.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found: " + dto.getStoreId()));

        Order order = Order.builder()
                .store(store)
                .orderDate(dto.getOrderDate())
                .orderStatus(dto.getOrderStatus())
                .build();

        int total = 0;
        for (OrderItemDto itemDto : dto.getItems()) {
            Menu menu = menuRepo.findById(itemDto.getMenuId())
                    .orElseThrow(() -> new EntityNotFoundException("Menu not found: " + itemDto.getMenuId()));

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .menu(menu)
                    .quantity(itemDto.getQuantity())
                    .unitPrice(menu.getPrice())
                    .build();

            order.getItems().add(item);
            total += item.getUnitPrice() * item.getQuantity();
        }
        order.setTotalAmount(total);
        Order saved = orderRepo.save(order);

        // 엔티티 → DTO 변환
        OrderDto result = toDto(saved);
        return result;
    }

    @Transactional(readOnly = true)
    public OrderDto getOrder(Integer id) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));
        return toDto(order);
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getOrdersByStoreId(Integer storeId) {
        return orderRepo.findByStoreStoreId(storeId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDto updateOrder(Integer id, OrderDto dto) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));

        if (dto.getOrderDate() != null) order.setOrderDate(dto.getOrderDate());
        if (dto.getOrderStatus() != null) order.setOrderStatus(dto.getOrderStatus());
        // items 수정 로직 생략 (필요시 추가)

        Order updated = orderRepo.save(order);
        return toDto(updated);
    }

    @Transactional
    public void deleteOrder(Integer id) {
        if (!orderRepo.existsById(id)) {
            throw new EntityNotFoundException("Order not found: " + id);
        }
        orderRepo.deleteById(id);
    }

    private OrderDto toDto(Order order) {
        List<OrderItemDto> items = order.getItems().stream()
                .map(i -> OrderItemDto.builder()
                        .menuId(i.getMenu().getMenuId())
                        .menuName(i.getMenu().getMenuName())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getUnitPrice())
                        // 아래에서 메뉴별 총합 가격 계산
                        .totalPrice(i.getUnitPrice() * i.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return OrderDto.builder()
                .orderId(order.getOrderId())
                .storeId(order.getStore().getStoreId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .items(items)
                .totalAmount(order.getTotalAmount())
                .build();
    }
}
