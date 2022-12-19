package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.CosmeticDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Cosmetic")
public class Cosmetic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private float price;
    @ManyToOne
    private Receipt receipt;

    public Cosmetic() {

    }

    public static Cosmetic from(CosmeticDto cosmeticDto) {
        Cosmetic cosmetic = new Cosmetic();
        cosmetic.setName(cosmeticDto.getName());
        cosmetic.setPrice(cosmeticDto.getPrice());
        return cosmetic;
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

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
