package com.aag.getme.controller;

import com.aag.getme.dto.InformationDTO;
import com.aag.getme.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/informations")
public class InformationController {

    @Autowired
    private InformationService informationService;

    @PostMapping(value = "/reports/{reportId}/v1")
    public ResponseEntity<InformationDTO> create(@PathVariable long reportId, @RequestBody InformationDTO information) {
        InformationDTO informationDTO = informationService.save(information, reportId);
        return ResponseEntity.status(HttpStatus.CREATED).body(informationDTO);

    }

    @PutMapping(value = "/{id}/v1")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody InformationDTO information) {
        informationService.update(id, information);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}
