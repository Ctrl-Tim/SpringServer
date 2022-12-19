package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.Cosmetic;
import lombok.Data;

import java.util.Objects;

@Data
public class CosmeticDto {
    private Long id;
    private String name;
    private float price;
    private PlainReceiptDto plainReceiptDto;

    public static CosmeticDto from(Cosmetic cosmetic) {
        CosmeticDto cosmeticDto = new CosmeticDto();
        cosmeticDto.setId(cosmetic.getId());
        cosmeticDto.setName(cosmetic.getName());
        cosmeticDto.setPrice(cosmetic.getPrice());
        return cosmeticDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public PlainReceiptDto getPlainReceiptDto() {
        return plainReceiptDto;
    }

    public void setPlainReceiptDto(PlainReceiptDto plainReceiptDto) {
        this.plainReceiptDto = plainReceiptDto;
    }
}
