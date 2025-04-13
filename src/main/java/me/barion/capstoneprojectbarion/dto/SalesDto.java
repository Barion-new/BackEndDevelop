package me.barion.capstoneprojectbarion.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SalesDto {
    private LocalDateTime salesDate; // 매출 날짜/시간
    private Integer totalSales; // 총 매출 금액

    public SalesDto() {
    }

    public SalesDto(LocalDateTime salesDate, Integer totalSales) {
        this.salesDate = salesDate;
        this.totalSales = totalSales;
    }
}
