package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.dto.SalesDto;
import me.barion.capstoneprojectbarion.Entity.Order;
import me.barion.capstoneprojectbarion.Entity.Sales;
import me.barion.capstoneprojectbarion.repository.OrderRepository;
import me.barion.capstoneprojectbarion.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SalesRepository salesRepository;

    // 총 매출 계산 (모든 주문의 총 금액 합산)
    public SalesDto calculateTotalSales() {
        Integer totalAmount = orderRepository.sumTotalAmount(); // 모든 주문의 총 금액 합산
        return new SalesDto(LocalDateTime.now(), totalAmount != null ? totalAmount : 0);
    }

    // 연도별 매출 계산 (Order 데이터를 기반으로 그룹화)
    public List<SalesDto> calculateYearlySales() {
        List<Order> orders = orderRepository.findAll();
        List<SalesDto> yearlySalesDtos = new ArrayList<>();

        orders.stream()
                .map(order -> order.getOrderDate().withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0)) // 연도 단위로 그룹화
                .distinct()
                .forEach(year -> {
                    Integer yearlyTotal = orders.stream()
                            .filter(order -> order.getOrderDate().getYear() == year.getYear())
                            .mapToInt(Order::getTotalAmount)
                            .sum();
                    yearlySalesDtos.add(new SalesDto(year, yearlyTotal));
                });

        return yearlySalesDtos;
    }

    // 월별 매출 계산 (Order 데이터를 기반으로 그룹화)
    public List<SalesDto> calculateMonthlySales() {
        List<Order> orders = orderRepository.findAll();
        List<SalesDto> monthlySalesDtos = new ArrayList<>();

        orders.stream()
                .map(order -> order.getOrderDate().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0)) // 월 단위로 그룹화
                .distinct()
                .forEach(month -> {
                    Integer monthlyTotal = orders.stream()
                            .filter(order -> order.getOrderDate().getYear() == month.getYear()
                                    && order.getOrderDate().getMonth() == month.getMonth())
                            .mapToInt(Order::getTotalAmount)
                            .sum();
                    monthlySalesDtos.add(new SalesDto(month, monthlyTotal));
                });

        return monthlySalesDtos;
    }


    // 시간별 매출 계산 (Order 데이터를 기반으로 그룹화)
    public List<SalesDto> calculateHourlySales() {
        List<Order> orders = orderRepository.findAll(); // 모든 주문 조회
        List<SalesDto> hourlySalesDtos = new ArrayList<>();

        orders.stream()
                .map(order -> order.getOrderDate().withMinute(0).withSecond(0).withNano(0)) // 시간 단위로 그룹화
                .distinct()
                .forEach(hour -> {
                    Integer hourlyTotal = orders.stream()
                            .filter(order -> order.getOrderDate().withMinute(0).withSecond(0).withNano(0).equals(hour))
                            .mapToInt(Order::getTotalAmount)
                            .sum();
                    hourlySalesDtos.add(new SalesDto(hour, hourlyTotal));
                });

        return hourlySalesDtos;
    }

    // 일별 매출 계산 (Order 데이터를 기반으로 그룹화)
    public List<SalesDto> calculateDailySales() {
        List<Order> orders = orderRepository.findAll(); // 모든 주문 조회
        List<SalesDto> dailySalesDtos = new ArrayList<>();

        orders.stream()
                .map(order -> order.getOrderDate().toLocalDate()) // 날짜 단위로 그룹화
                .distinct()
                .forEach(date -> {
                    Integer dailyTotal = orders.stream()
                            .filter(order -> order.getOrderDate().toLocalDate().equals(date))
                            .mapToInt(Order::getTotalAmount)
                            .sum();
                    dailySalesDtos.add(new SalesDto(date.atStartOfDay(), dailyTotal));
                });

        return dailySalesDtos;
    }

    // 순 이익 계산
    public SalesDto calculateTotalProfit() {
        Integer totalProfit = salesRepository.sumTotalProfit();
        return new SalesDto(LocalDateTime.now(), totalProfit != null ? totalProfit : 0);
    }
}
