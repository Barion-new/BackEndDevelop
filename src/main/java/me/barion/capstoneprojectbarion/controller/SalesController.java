package me.barion.capstoneprojectbarion.controller;

import me.barion.capstoneprojectbarion.Entity.Sales;
import me.barion.capstoneprojectbarion.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping
    public List<Sales> getAllSales() {
        return salesService.getAllSales();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSalesById(@PathVariable int id) {
        Optional<Sales> sales = salesService.getSalesById(id);
        return sales.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sales createSales(@RequestBody Sales sales) {
        return salesService.saveSales(sales);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable int id) {
        salesService.deleteSales(id);
        return ResponseEntity.noContent().build();
    }

    // 총 매출 계산
    @GetMapping("/total")
    public ResponseEntity<Map<String, Integer>> getTotalSales() {
        int totalSales = salesService.calculateTotalSales();
        Map<String, Integer> response = new HashMap<>();
        response.put("total_sales", totalSales);
        return ResponseEntity.ok(response);
    }

    // 시간별 매출 조회
    @GetMapping("/hourly")
    public ResponseEntity<List<Map<String, Object>>> getSalesByHour() {
        return ResponseEntity.ok(salesService.getSalesByHour());
    }

    // 날짜별 매출 조회
    @GetMapping("/daily")
    public ResponseEntity<List<Map<String, Object>>> getSalesByDate() {
        return ResponseEntity.ok(salesService.getSalesByDate());
    }

    // 월별 매출 조회
    @GetMapping("/monthly")
    public ResponseEntity<List<Map<String, Object>>> getSalesByMonth() {
        return ResponseEntity.ok(salesService.getSalesByMonth());
    }

    // 연도별 매출 조회
    @GetMapping("/yearly")
    public ResponseEntity<List<Map<String, Object>>> getSalesByYear() {
        return ResponseEntity.ok(salesService.getSalesByYear());
    }

    // 특정 날짜의 시간별 매출 조회
    @GetMapping("/hourly/{date}")
    public ResponseEntity<List<Map<String, Object>>> getSalesByHourForDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(salesService.getSalesByHourForDate(date));
    }

    // 특정 기간의 날짜별 매출 조회
    @GetMapping("/daily/period")
    public ResponseEntity<List<Map<String, Object>>> getSalesByDateBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(salesService.getSalesByDateBetween(startDate, endDate));
    }

    // 특정 기간의 월별 매출 조회
    @GetMapping("/monthly/period")
    public ResponseEntity<List<Map<String, Object>>> getSalesByMonthBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(salesService.getSalesByMonthBetween(startDate, endDate));
    }

    // 특정 기간의 연도별 매출 조회
    @GetMapping("/yearly/period")
    public ResponseEntity<List<Map<String, Object>>> getSalesByYearBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return ResponseEntity.ok(salesService.getSalesByYearBetween(startDate, endDate));
    }
}
