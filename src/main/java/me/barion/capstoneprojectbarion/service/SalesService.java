package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.dto.SalesDto;
import me.barion.capstoneprojectbarion.Entity.Order; // Order 엔티티는 이 메소드에서 직접 사용하지 않음
import me.barion.capstoneprojectbarion.Entity.Sales;
import me.barion.capstoneprojectbarion.repository.OrderRepository;
import me.barion.capstoneprojectbarion.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.time.YearMonth;

@Service
public class SalesService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SalesRepository salesRepository;

    public SalesDto calculateTotalSales() {
        Integer totalAmount = orderRepository.sumTotalAmount();
        return new SalesDto(LocalDateTime.now(), totalAmount != null ? totalAmount : 0);
    }

    public List<SalesDto> calculateYearlySales() {
        List<Order> orders = orderRepository.findAll();
        List<SalesDto> yearlySalesDtos = new ArrayList<>();

        Map<Integer, Integer> salesByYear = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().getYear(),
                        Collectors.summingInt(Order::getTotalAmount)
                ));

        salesByYear.forEach((year, total) -> {
            yearlySalesDtos.add(new SalesDto(LocalDateTime.of(year, 1, 1, 0, 0), total));
        });
        yearlySalesDtos.sort((s1, s2) -> s1.getSalesDate().compareTo(s2.getSalesDate()));
        return yearlySalesDtos;
    }

    public List<SalesDto> calculateMonthlySales() {
        List<Order> orders = orderRepository.findAll();
        List<SalesDto> monthlySalesDtos = new ArrayList<>();

        Map<YearMonth, Integer> salesByMonth = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> YearMonth.from(order.getOrderDate()),
                        Collectors.summingInt(Order::getTotalAmount)
                ));

        salesByMonth.forEach((yearMonth, total) -> {
            monthlySalesDtos.add(new SalesDto(yearMonth.atDay(1).atStartOfDay(), total));
        });
        monthlySalesDtos.sort((s1, s2) -> s1.getSalesDate().compareTo(s2.getSalesDate()));
        return monthlySalesDtos;
    }
    public List<SalesDto> calculateHourlySales() {
        List<Order> orders = orderRepository.findAll();
        List<SalesDto> hourlySalesDtos = new ArrayList<>();

        Map<LocalDateTime, Integer> salesByHour = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().withMinute(0).withSecond(0).withNano(0),
                        Collectors.summingInt(Order::getTotalAmount)
                ));

        salesByHour.forEach((hour, total) -> {
            hourlySalesDtos.add(new SalesDto(hour, total));
        });
        hourlySalesDtos.sort((s1, s2) -> s1.getSalesDate().compareTo(s2.getSalesDate()));
        return hourlySalesDtos;
    }

    public List<SalesDto> calculateDailySales() {
        List<Order> orders = orderRepository.findAll();
        List<SalesDto> dailySalesDtos = new ArrayList<>();

        Map<LocalDateTime, Integer> salesByDay = orders.stream()
                .collect(Collectors.groupingBy(
                        order -> order.getOrderDate().toLocalDate().atStartOfDay(),
                        Collectors.summingInt(Order::getTotalAmount)
                ));

        salesByDay.forEach((day, total) -> {
            dailySalesDtos.add(new SalesDto(day, total));
        });
        dailySalesDtos.sort((s1, s2) -> s1.getSalesDate().compareTo(s2.getSalesDate()));
        return dailySalesDtos;
    }

    public SalesDto calculateTotalProfit() {

        Integer totalProfit = salesRepository.sumTotalProfit();
        return new SalesDto(LocalDateTime.now(), totalProfit != null ? totalProfit : 0);
    }

    public List<SalesDto> calculateMonthlyProfit() {
        List<Sales> salesList = salesRepository.findAll(); // 모든 Sales 데이터 조회
        List<SalesDto> monthlyProfitDtos = new ArrayList<>();

        Map<YearMonth, Integer> profitByMonth = salesList.stream()
                .collect(Collectors.groupingBy(
                        sales -> YearMonth.from(sales.getSalesDate()), // salesDate에서 YearMonth 추출
                        Collectors.summingInt(sales -> sales.getTotalSales() - sales.getTotalCost()) // 순이익 계산
                ));

        profitByMonth.forEach((yearMonth, profit) -> {

            monthlyProfitDtos.add(new SalesDto(yearMonth.atDay(1).atStartOfDay(), profit));
        });

        monthlyProfitDtos.sort((dto1, dto2) -> dto1.getSalesDate().compareTo(dto2.getSalesDate()));

        return monthlyProfitDtos;
    }
}
