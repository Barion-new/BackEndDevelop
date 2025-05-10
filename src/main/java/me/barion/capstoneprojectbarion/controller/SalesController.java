package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.barion.capstoneprojectbarion.dto.SalesDto;
import me.barion.capstoneprojectbarion.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @Operation(summary = "총 매출 조회", description = "총 매출액을 조회합니다")
    @GetMapping("/total")
    public ResponseEntity<SalesDto> getTotalSales() {
        SalesDto totalSales = salesService.calculateTotalSales();
        return ResponseEntity.ok(totalSales);
    }

    @Operation(summary = "시간별 매출 조회", description = "시간별 주문을 조회합니다.")
    @GetMapping("/hourly")
    public ResponseEntity<List<SalesDto>> getHourlySales() {
        List<SalesDto> hourlySales = salesService.calculateHourlySales();
        return ResponseEntity.ok(hourlySales);
    }

    @Operation(summary = "일별 매출 조회", description = "일별 주문을 조회합니다.")
    @GetMapping("/daily")
    public ResponseEntity<List<SalesDto>> getDailySales() {
        List<SalesDto> dailySales = salesService.calculateDailySales();
        return ResponseEntity.ok(dailySales);
    }

    @Operation(summary = "연도별 매출 조회", description = "연도별 주문을 조회합니다.")
    @GetMapping("/yearly")
    public ResponseEntity<List<SalesDto>> getYearlySales() {
        List<SalesDto> yearlySales = salesService.calculateYearlySales();
        return ResponseEntity.ok(yearlySales);
    }

    @Operation(summary = "월별 매출 조회", description = "월별 주문을 조회합니다.")
    @GetMapping("/monthly")
    public ResponseEntity<List<SalesDto>> getMonthlySales() {
        List<SalesDto> monthlySales = salesService.calculateMonthlySales();
        return ResponseEntity.ok(monthlySales);
    }

    @Operation(summary = "총 순이익 조회", description = "총 순이익을 조회합니다.")
    @GetMapping("/totalProfit")
    public ResponseEntity<SalesDto> getTotalProfit() {
        SalesDto totalProfit = salesService.calculateTotalProfit();
        return ResponseEntity.ok(totalProfit);
    }

    @Operation(summary = "월별 순이익 조회", description = "월별 순이익을 조회합니다.")
    @GetMapping("/monthlyProfit")
    public ResponseEntity<List<SalesDto>> getMonthlyProfit() {
        List<SalesDto> monthlyProfit = salesService.calculateMonthlyProfit();
        return ResponseEntity.ok(monthlyProfit);
    }
}
