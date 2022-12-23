package com.salon.SpringServer.model;

import com.salon.SpringServer.model.dto.VisitDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "visits")
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    public LocalDateTime date;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "visit", optional=true)
    private Distribution distribution;

    public Visit() { }

    public static Visit from(VisitDto visitDto) {
        Visit visit = new Visit();
        visit.setDate(visitDto.getDate());
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
