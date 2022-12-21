package com.salon.SpringServer.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "distributions")
public class Distribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "visit_id", referencedColumnName = "id")
    private Visit visit;


    public Distribution () { }
}
