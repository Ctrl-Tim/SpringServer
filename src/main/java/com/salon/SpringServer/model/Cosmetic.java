package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.CosmeticDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "cosmetics")
public class Cosmetic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private float price;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cosmetic")
    private List<ReceiptDetail> receiptDetails = new ArrayList<>();

    public Cosmetic() { }

    public static Cosmetic from(CosmeticDto cosmeticDto) {
        Cosmetic cosmetic = new Cosmetic();
        cosmetic.setName(cosmeticDto.getName());
        cosmetic.setPrice(cosmeticDto.getPrice());
        return cosmetic;
    }

    public void addReceipt(ReceiptDetail receipt) {
        this.receiptDetails.add(receipt);
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

    public List<ReceiptDetail> getReceiptDetails() { return receiptDetails; }

    public void setReceiptDetails(List<ReceiptDetail> receipts) {
        this.receiptDetails = receipts;
    }

    public void addCosmeticReceipt(ReceiptDetail cosmeticReceipt) { this.receiptDetails.add(cosmeticReceipt); }
}
