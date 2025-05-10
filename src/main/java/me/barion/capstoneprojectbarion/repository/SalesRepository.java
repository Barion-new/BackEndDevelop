package me.barion.capstoneprojectbarion.repository;

import me.barion.capstoneprojectbarion.Entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface SalesRepository extends JpaRepository<Sales, Integer> {

    @Query("SELECT SUM(s.totalSales - s.totalCost) FROM Sales s")
    Integer sumTotalProfit();
}
