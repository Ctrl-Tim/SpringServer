package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.Cosmetic;
import com.salon.SpringServer.model.Receipt;
import com.salon.SpringServer.model.ReceiptDetail;
import lombok.Data;

@Data
public class ReceiptDetailDto {
    private Long id;
    private String cosmeticName;
    private int count;
    private float subtotal;

    public static ReceiptDetailDto from(ReceiptDetail rd) {
        ReceiptDetailDto rdd = new ReceiptDetailDto();
        rdd.setId(rd.getId());
        rdd.setCosmeticName(rd.getCosmetic().getName());
        rdd.setCount(rd.getCount());
        rdd.setSubtotal(rd.getCosmetic().getPrice()*rd.getCount());
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

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
}
