package me.barion.capstoneprojectbarion.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.barion.capstoneprojectbarion.Entity.Store;
import me.barion.capstoneprojectbarion.dto.StoreNameDto;
import me.barion.capstoneprojectbarion.dto.StoreTypeDto;
import me.barion.capstoneprojectbarion.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
@Tag(name = "Store", description = "가게 관리 API")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @Operation(summary = "가게 이름 설정", description = "가게 이름을 설정")
    @PutMapping("/{storeId}/name")
    public String updateStoreName(@PathVariable Integer storeId,
                                  @RequestBody StoreNameDto dto) {
        // 서비스 호출하여 가게 이름 업데이트
        storeService.updateStoreName(storeId, dto.getStoreName());

        // 요청 받은 storeName을 그대로 반환
        return dto.getStoreName();
    }

    @Operation(summary = "업종 설정", description = "업종을 설정")
    @PutMapping("/{storeId}/type")
    public String updateStoreType(@PathVariable Integer storeId,
                                  @RequestBody StoreTypeDto dto) {
        // 서비스 호출하여 업종 업데이트
        storeService.updateStoreType(storeId, dto.getStoreType());

        // 요청 받은 storeType을 그대로 반환
        return dto.getStoreType();
    }


}
