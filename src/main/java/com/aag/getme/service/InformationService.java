package com.aag.getme.service;

import com.aag.getme.dto.InformationDTO;
import com.aag.getme.exceptions.ModelNotFoundException;
import com.aag.getme.model.Information;
import com.aag.getme.model.Report;
import com.aag.getme.repository.InformationRepository;
import com.aag.getme.repository.ReportRepository;
import com.aag.getme.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InformationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Transactional
    public InformationDTO save(InformationDTO informationDTO, Long reportId) {
        if(!reportRepository.existsById(reportId))
            throw new ModelNotFoundException("Não existe um report com id: " + reportId);

        Information information = modelMapper.map(informationDTO, Information.class);

        Report report = new Report();
        report.setId(reportId);
        information.setReport(report);
        information = informationRepository.save(information);

        InformationDTO dto = modelMapper.map(information, InformationDTO.class);
        dto.setReportId(reportId);
        dto.setId(information.getId());
        return dto;
    }

    public void update(long id, InformationDTO informationRequest) {
        Information information = informationRepository.findById(id).orElseThrow(() -> new ModelNotFoundException("Não existe uma informação com id: " + id));
        System.out.println(information.getReport().getId());

    }


}
