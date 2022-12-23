package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.DistributionDetail;
import com.salon.SpringServer.model.Receipt;
import com.salon.SpringServer.model.ReceiptDetail;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DistributionDetailDto {
    private Long id;
    private String cosmeticName;
    private int count;

    public static DistributionDetailDto from(DistributionDetail dd) {
        DistributionDetailDto rdd = new DistributionDetailDto();
        rdd.setId(dd.getCosmetic().getId());
        rdd.setCosmeticName(dd.getCosmetic().getName());
        rdd.setCount(dd.getCount());
        return rdd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCosmeticName() {
        return cosmeticName;
    }

    public void setCosmeticName(String cosmeticName) {
        this.cosmeticName = cosmeticName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
