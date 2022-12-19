package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.ReceiptDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public LocalDateTime date;

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "cosmetic_id")
    private List<Cosmetic> cosmetics = new ArrayList<>();

    public void addCosmetic(Cosmetic cosmetic) {
        cosmetics.add(cosmetic);
    }

    public void removeCosmetic(Cosmetic cosmetic){
        cosmetics.remove(cosmetic);
    }

    public static Receipt from(ReceiptDto receiptDto) {
        Receipt receipt = new Receipt();
        receipt.setDate(receiptDto.getDate());
        return receipt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Cosmetic> getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(List<Cosmetic> cosmetics) {
        this.cosmetics = cosmetics;
    }
}
