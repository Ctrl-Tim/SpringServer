package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.DistributionDetailDto;
import com.salon.SpringServer.model.dto.ReceiptDetailDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table(name = "distribution_details")
public class DistributionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cosmetic_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Cosmetic cosmetic;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "distribution_id")
    private Distribution distribution;

    private int count;

    public DistributionDetail() {}

    public static DistributionDetail from(DistributionDetailDto ddd) {
        DistributionDetail dd = new DistributionDetail();
        dd.setId(ddd.getId());
        dd.setCount(ddd.getCount());
        return dd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cosmetic getCosmetic() {
        return cosmetic;
    }

    public void setCosmetic(Cosmetic cosmetic) {
        this.cosmetic = cosmetic;
    }

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
