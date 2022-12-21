package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.Distribution;
import com.salon.SpringServer.model.Visit;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitDto {
    private Long id;
    public LocalDateTime date;
    private Distribution distribution;

    public static VisitDto from(Visit visit) {
        VisitDto visitDto = new VisitDto();
        visitDto.setId(visit.getId());
        visitDto.setDate(visit.getDate());
        visitDto.setDistribution(visit.getDistribution());
        return visitDto;
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

    public Distribution getDistribution() {
        return distribution;
    }

    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }
}
