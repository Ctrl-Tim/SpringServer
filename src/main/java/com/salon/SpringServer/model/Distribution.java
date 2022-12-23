package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.DistributionDto;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "distributions")
public class Distribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "visit_id", nullable=true)
    private Visit visit;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "distribution", cascade = CascadeType.ALL)
    private List<DistributionDetail> cosmetics = new ArrayList<>();

    public Distribution () { }

    public static Distribution from(DistributionDto distributionDto) {
        Distribution distribution = new Distribution();
        distribution.setCosmetics(distributionDto.getCosmetics().stream().map(DistributionDetail::from).collect(Collectors.toList()));
        return distribution;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }

    public List<DistributionDetail> getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(List<DistributionDetail> cosmetics) {
        this.cosmetics = cosmetics;
    }

    public void addCosmeticDistribution(DistributionDetail distributionDetail) {
        cosmetics.add(distributionDetail);
    }

    public void removeCosmeticDistribution(DistributionDetail distributionDetail) { cosmetics.remove(distributionDetail); }

    public void distributionBindingVisit(Distribution distribution, Visit visit) {distribution.visit = visit; }

    public void distributionDecouplingVisit() { this.visit = null; }
}
