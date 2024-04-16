package com.aag.getme.service;

import com.aag.getme.dto.PersonDTO;
import com.aag.getme.dto.ReportPersonDTO;
import com.aag.getme.model.LocationDetails;
import com.aag.getme.model.Person;
import com.aag.getme.model.Report;
import com.aag.getme.model.User;
import com.aag.getme.repository.ReportRepository;
import com.aag.getme.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ReportPersonDTO save(ReportPersonDTO reportPersonDTO) {
        User user = userService.getUserAuthenticated();

        Person person = personService.create(reportPersonDTO.getPerson());
        person.setUser(user);

        LocationDetails locationDetails = modelMapper.map(reportPersonDTO.getLastSeenLocation(), LocationDetails.class);

        Report report = new Report();
        report.setLastSeenLocation(locationDetails);
        report.setPerson(person);
        report.setUser(user);
        reportRepository.save(report);

        reportPersonDTO.setId(report.getId());
        reportPersonDTO.getPerson().setId(person.getId());
        return reportPersonDTO;
    }

    //Preciso validar o usuario logado se é o dono do report
    public ReportPersonDTO findId(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return getReportPersonDTO(report);

    }

    public List<ReportPersonDTO> findAll() {
        return reportRepository.findAll().stream().map(this::getReportPersonDTO).toList();

    }

    private ReportPersonDTO getReportPersonDTO(Report report) {
        PersonDTO personDto = modelMapper.map(report.getPerson(), PersonDTO.class);
        LocationDetails locationDetails = modelMapper.map(report.getLastSeenLocation(), LocationDetails.class);

        ReportPersonDTO reportPersonDTO = new ReportPersonDTO();
        reportPersonDTO.setId(report.getId());
        reportPersonDTO.setPerson(personDto);
        reportPersonDTO.setLastSeenLocation(locationDetails);
        return reportPersonDTO;
    }

    public List<ReportPersonDTO> findAllUser() {
        long userId = 1;
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User não existe"));
        List<Report> reports = reportRepository.findAllByUser(user);
        return reports.stream().map(this::getReportPersonDTO).toList();
    }
}
