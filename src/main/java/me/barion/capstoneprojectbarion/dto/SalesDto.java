package me.barion.capstoneprojectbarion.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SalesDto {
    private LocalDateTime salesDate; // 또는 date
    private Integer totalSales;
    private Integer totalCost;
    private Integer profit;
}
