package me.barion.capstoneprojectbarion.service;

import me.barion.capstoneprojectbarion.Entity.Sales;
import me.barion.capstoneprojectbarion.repository.OrderRepository;
import me.barion.capstoneprojectbarion.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private OrderRepository ordersRepository;

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Optional<Sales> getSalesById(int salesId) {
        return salesRepository.findById(salesId);
    }

    public Sales saveSales(Sales sales) {
        return salesRepository.save(sales);
    }

    public void deleteSales(int salesId) {
        salesRepository.deleteById(salesId);
    }

    // Order 테이블의 total_amount 합계 계산
    public int calculateTotalSales() {
        return ordersRepository.sumTotalAmount();
    }

    // 시간별 매출 조회
    public List<Map<String, Object>> getSalesByHour() {
        return ordersRepository.findSalesByHour();
    }

    // 날짜별 매출 조회
    public List<Map<String, Object>> getSalesByDate() {
        return ordersRepository.findSalesByDate();
    }

    // 월별 매출 조회
    public List<Map<String, Object>> getSalesByMonth() {
        return ordersRepository.findSalesByMonth();
    }

    // 연도별 매출 조회
    public List<Map<String, Object>> getSalesByYear() {
        return ordersRepository.findSalesByYear();
    }

    // 특정 날짜의 시간별 매출 조회
    public List<Map<String, Object>> getSalesByHourForDate(LocalDate date) {
        return ordersRepository.findSalesByHourForDate(date);
    }

    // 특정 기간의 날짜별 매출 조회
    public List<Map<String, Object>> getSalesByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return ordersRepository.findSalesByDateBetween(startDate, endDate);
    }

    // 특정 기간의 월별 매출 조회
    public List<Map<String, Object>> getSalesByMonthBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return ordersRepository.findSalesByMonthBetween(startDate, endDate);
    }

    // 특정 기간의 연도별 매출 조회
    public List<Map<String, Object>> getSalesByYearBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return ordersRepository.findSalesByYearBetween(startDate, endDate);
    }
}
