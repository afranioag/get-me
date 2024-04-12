package com.aag.getme.controller;

import com.aag.getme.dto.ReportPersonDTO;
import com.aag.getme.model.Report;
import com.aag.getme.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping(value = "/users/{id}")
    public ResponseEntity<ReportPersonDTO> save(@PathVariable long id, @RequestBody ReportPersonDTO reportPersonDTO) {

        ReportPersonDTO report = reportService.save(reportPersonDTO, id);
               URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(report.getId())
                .toUri();

        return ResponseEntity.created(uri).body(report);
    }

    @GetMapping(value = "/{id}/users/{userId}")
    public ResponseEntity<ReportPersonDTO> findId(@PathVariable long id, @PathVariable long userId) {
        return ResponseEntity.ok().body(reportService.findId(userId, id));

    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<List<ReportPersonDTO>> findAll(@PathVariable long userId) {
        return ResponseEntity.ok().body(reportService.findAll(userId));

    }
}
