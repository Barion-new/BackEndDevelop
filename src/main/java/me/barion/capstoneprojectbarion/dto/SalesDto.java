package me.barion.capstoneprojectbarion.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SalesDto {

    private LocalDateTime salesDate;
    private int totalSales;

}