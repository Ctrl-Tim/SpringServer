package com.salon.SpringServer.model.dto;

import com.salon.SpringServer.model.Distribution;
import com.salon.SpringServer.model.Visit;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VisitDto {
    private Long id;
    public LocalDateTime date;
    private Long distribution_id;

    public static VisitDto from(Visit visit) {
        VisitDto visitDto = new VisitDto();
        visitDto.setId(visit.getId());
        visitDto.setDate(visit.getDate());
        if (visit.getDistribution() != null) {
            visitDto.setDistribution(visit.getDistribution().getId());
        }
        else {
            visit.setDistribution(null);
        }
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

    public Long getDistribution() {
        return distribution_id;
    }

    public void setDistribution(Long distribution_id) {
        this.distribution_id = distribution_id;
    }
}
