package com.salon.SpringServer.controller;

import com.salon.SpringServer.model.Visit;
import com.salon.SpringServer.model.dto.ReceiptDto;
import com.salon.SpringServer.model.dto.VisitDto;
import com.salon.SpringServer.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/visits")
public class VisitController {
    private final VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<VisitDto> addVisit(@RequestBody final VisitDto visitDto) {
        Visit visit = visitService.addVisit(Visit.from(visitDto));
        return new ResponseEntity<>(VisitDto.from(visit), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VisitDto>> getVisits() {
        List<Visit> visits = visitService.getVisits();
        List<VisitDto> visitsDto = visits.stream().map(VisitDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(visitsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<VisitDto> getVisit(@PathVariable final Long id) {
        Visit visit = visitService.getVisit(id);
        return new ResponseEntity<>(VisitDto.from(visit), HttpStatus.OK);
    }
}
