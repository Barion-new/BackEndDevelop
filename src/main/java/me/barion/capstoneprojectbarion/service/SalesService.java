package me.barion.capstoneprojectbarion.service;


import me.barion.capstoneprojectbarion.repository.OrderRepository;
import me.barion.capstoneprojectbarion.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SalesService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private OrderRepository ordersRepository;



    public int calculateTotalSales() {
        return ordersRepository.sumTotalAmount();
    }

    // 시간별 매출 조회
    public List<Map<String, Object>> getSalesByHour() {
        return salesRepository.findSalesByHour();
    }

    // 날짜별 매출 조회
    public List<Map<String, Object>> getSalesByDate() {
        return salesRepository.findSalesByDate();
    }

    // 월별 매출 조회
    public List<Map<String, Object>> getSalesByMonth() {
        return salesRepository.findSalesByMonth();
    }

    // 연도별 매출 조회
    public List<Map<String, Object>> getSalesByYear() {
        return salesRepository.findSalesByYear();
    }



}
