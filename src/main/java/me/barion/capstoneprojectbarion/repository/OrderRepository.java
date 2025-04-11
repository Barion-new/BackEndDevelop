
package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // 기존 메서드들
    List<Order> findByStoreStoreId(Integer storeId);

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o")
    int sumTotalAmount();

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

    // 특정 기간의 날짜별 매출 조회
    @Query(value = "SELECT DATE(order_date) as date, COALESCE(SUM(total_amount), 0) as total FROM orders WHERE order_date BETWEEN :startDate AND :endDate GROUP BY DATE(order_date) ORDER BY date", nativeQuery = true)
    List<Map<String, Object>> findSalesByDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // 특정 기간의 월별 매출 조회
    @Query(value = "SELECT YEAR(order_date) as year, MONTH(order_date) as month, COALESCE(SUM(total_amount), 0) as total FROM orders WHERE order_date BETWEEN :startDate AND :endDate GROUP BY YEAR(order_date), MONTH(order_date) ORDER BY year, month", nativeQuery = true)
    List<Map<String, Object>> findSalesByMonthBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // 특정 기간의 연도별 매출 조회
    @Query(value = "SELECT YEAR(order_date) as year, COALESCE(SUM(total_amount), 0) as total FROM orders WHERE order_date BETWEEN :startDate AND :endDate GROUP BY YEAR(order_date) ORDER BY year", nativeQuery = true)
    List<Map<String, Object>> findSalesByYearBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    // 특정 매장의 시간별 매출 조회
    @Query(value = "SELECT HOUR(order_date) as hour, COALESCE(SUM(total_amount), 0) as total FROM orders WHERE store_id = :storeId GROUP BY HOUR(order_date) ORDER BY hour", nativeQuery = true)
    List<Map<String, Object>> findSalesByHourAndStoreId(@Param("storeId") Integer storeId);

    // 특정 매장의 날짜별 매출 조회
    @Query(value = "SELECT DATE(order_date) as date, COALESCE(SUM(total_amount), 0) as total FROM orders WHERE store_id = :storeId GROUP BY DATE(order_date) ORDER BY date", nativeQuery = true)
    List<Map<String, Object>> findSalesByDateAndStoreId(@Param("storeId") Integer storeId);

    // 특정 매장의 월별 매출 조회
    @Query(value = "SELECT YEAR(order_date) as year, MONTH(order_date) as month, COALESCE(SUM(total_amount), 0) as total FROM orders WHERE store_id = :storeId GROUP BY YEAR(order_date), MONTH(order_date) ORDER BY year, month", nativeQuery = true)
    List<Map<String, Object>> findSalesByMonthAndStoreId(@Param("storeId") Integer storeId);

    // 특정 매장의 연도별 매출 조회
    @Query(value = "SELECT YEAR(order_date) as year, COALESCE(SUM(total_amount), 0) as total FROM orders WHERE store_id = :storeId GROUP BY YEAR(order_date) ORDER BY year", nativeQuery = true)
    List<Map<String, Object>> findSalesByYearAndStoreId(@Param("storeId") Integer storeId);
}