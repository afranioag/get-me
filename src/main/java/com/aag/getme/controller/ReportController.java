package com.aag.getme.controller;

import com.aag.getme.dto.ReportPersonDTO;
import com.aag.getme.dto.ReportPersonInformationDTO;
import com.aag.getme.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(value = "/users")
    public ResponseEntity<ReportPersonDTO> save(@RequestBody ReportPersonDTO reportPersonDTO) {
        ReportPersonDTO report = reportService.save(reportPersonDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(report);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/users")
    public ResponseEntity<List<ReportPersonInformationDTO>> findAllByUser() {
        return ResponseEntity.ok().body(reportService.findAllByUser());

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(value = "/{reportId}/users")
    public ResponseEntity<Void> delete(@PathVariable long reportId) {
        reportService.delete(reportId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    public ResponseEntity<List<ReportPersonDTO>> findAll() {
        return ResponseEntity.ok().body(reportService.findAll());

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReportPersonDTO> findId(@PathVariable long id) {
        return ResponseEntity.ok().body(reportService.findId(id));

    }


}
