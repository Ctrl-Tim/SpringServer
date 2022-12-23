package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.Distribution;
import com.salon.SpringServer.model.Receipt;
import com.salon.SpringServer.model.Visit;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DistributionDto {
    private Long id;
    private LocalDateTime visit;
    private List<DistributionDetailDto> cosmetics = new ArrayList<>();

    public static DistributionDto from(Distribution distribution) {
        DistributionDto distributionDto = new DistributionDto();
        distributionDto.setId(distribution.getId());
        distributionDto.setCosmetics(distribution.getCosmetics().stream().map(DistributionDetailDto::from).collect(Collectors.toList()));
        if (distribution.getVisit() != null) {
            distributionDto.setVisit(distribution.getVisit().getDate());
        }
        else {
            distributionDto.setVisit(null);
        }
        return distributionDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getVisit() {
        return visit;
    }

    public void setVisit(LocalDateTime date) {
        this.visit = date;
    }

    public List<DistributionDetailDto> getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(List<DistributionDetailDto> cosmetics) {
        this.cosmetics = cosmetics;
    }
}
