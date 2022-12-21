package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.VisitDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public LocalDateTime date;

    @OneToOne(mappedBy = "visit")
    private Distribution distribution;

    public Visit() { }

    public static Visit from(VisitDto visitDto) {
        Visit visit = new Visit();
        visit.setDate(visitDto.getDate());
        visit.setDistribution(visitDto.getDistribution());
        return visit;
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
