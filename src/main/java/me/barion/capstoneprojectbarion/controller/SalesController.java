package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.barion.capstoneprojectbarion.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;


    // 총 매출 계산
    @Operation(summary = "총 매출", description = "총 매출액을 조회합니다")
    @GetMapping("/total")
    public ResponseEntity<Map<String, Integer>> getTotalSales() {
        int totalSales = salesService.calculateTotalSales();
        Map<String, Integer> response = new HashMap<>();
        response.put("total_sales", totalSales);
        return ResponseEntity.ok(response);
    }

    // 시간별 매출 조회
    @Operation(summary = "시간별 매출", description = "시간별 매출액을 조회합니다")
    @GetMapping("/hourly")
    public ResponseEntity<List<Map<String, Object>>> getSalesByHour() {
        return ResponseEntity.ok(salesService.getSalesByHour());
    }

    // 날짜별 매출 조회
    @Operation(summary = "날짜별 매출", description = "날짜별 매출액을 조회합니다")
    @GetMapping("/daily")
    public ResponseEntity<List<Map<String, Object>>> getSalesByDate() {
        return ResponseEntity.ok(salesService.getSalesByDate());
    }

    // 월별 매출 조회
    @Operation(summary = "월별 매출", description = "월별 매출액을 조회합니다")
    @GetMapping("/monthly")
    public ResponseEntity<List<Map<String, Object>>> getSalesByMonth() {
        return ResponseEntity.ok(salesService.getSalesByMonth());
    }

    // 연도별 매출 조회
    @Operation(summary = "연도별 매출", description = "연도별 매출액을 조회합니다")
    @GetMapping("/yearly")
    public ResponseEntity<List<Map<String, Object>>> getSalesByYear() {
        return ResponseEntity.ok(salesService.getSalesByYear());
    }


}
