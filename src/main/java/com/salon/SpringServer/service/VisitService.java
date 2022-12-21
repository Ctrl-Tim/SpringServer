package com.salon.SpringServer.service;

import com.salon.SpringServer.model.Visit;
import com.salon.SpringServer.model.exception.CosmeticNotFoundException;
import com.salon.SpringServer.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    @Autowired
    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Transactional
    public Visit addVisit(Visit visit) {
        return visitRepository.save(visit);
    }

    public List<Visit> getVisits() {
        return StreamSupport
                .stream(visitRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Visit getVisit(Long id) {
        return visitRepository.findById(id).orElseThrow(() ->
                new CosmeticNotFoundException(id));
    }
}
