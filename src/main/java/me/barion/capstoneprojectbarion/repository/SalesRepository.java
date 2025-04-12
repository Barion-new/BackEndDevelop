package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    // 시간별 매출 조회 (0-23시)
    @Query(value = "SELECT HOUR(order_date) as hour, COALESCE(SUM(total_amount), 0) as total FROM orders GROUP BY HOUR(order_date) ORDER BY hour", nativeQuery = true)
    List<Map<String, Object>> findSalesByHour();

    // 날짜별 매출 조회
    @Query(value = "SELECT DATE(order_date) as date, COALESCE(SUM(total_amount), 0) as total FROM orders GROUP BY DATE(order_date) ORDER BY date", nativeQuery = true)
    List<Map<String, Object>> findSalesByDate();

    // 월별 매출 조회
    @Query(value = "SELECT YEAR(order_date) as year, MONTH(order_date) as month, COALESCE(SUM(total_amount), 0) as total FROM orders GROUP BY YEAR(order_date), MONTH(order_date) ORDER BY year, month", nativeQuery = true)
    List<Map<String, Object>> findSalesByMonth();

    // 연도별 매출 조회
    @Query(value = "SELECT YEAR(order_date) as year, COALESCE(SUM(total_amount), 0) as total FROM orders GROUP BY YEAR(order_date) ORDER BY year", nativeQuery = true)
    List<Map<String, Object>> findSalesByYear();

    // 특정 날짜의 시간별 매출 조회
    @Query(value = "SELECT HOUR(order_date) as hour, COALESCE(SUM(total_amount), 0) as total FROM orders WHERE DATE(order_date) = :date GROUP BY HOUR(order_date) ORDER BY hour", nativeQuery = true)
    List<Map<String, Object>> findSalesByHourForDate(@Param("date") LocalDate date);


}
