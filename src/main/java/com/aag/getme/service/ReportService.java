package com.aag.getme.service;

import com.aag.getme.dto.PersonDto;
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
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public ReportPersonDTO save(ReportPersonDTO reportPersonDTO, long userId) {
        if(!userRepository.existsById(userId))
            throw new RuntimeException("Usuário não existe para o id Informado!");

        User user = new User();
        user.setId(userId);

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
    public ReportPersonDTO findId(Long userId, Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException(""));
        return getReportPersonDTO(report);

    }

    // Buscar dados atraves do usuario logado
    public List<ReportPersonDTO> findAll(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User não existe"));
        List<Report> reports = reportRepository.findAllByUser(user);
        return reports.stream().map(this::getReportPersonDTO).toList();

    }

    private ReportPersonDTO getReportPersonDTO(Report report) {
        PersonDto personDto = modelMapper.map(report.getPerson(), PersonDto.class);
        LocationDetails locationDetails = modelMapper.map(report.getLastSeenLocation(), LocationDetails.class);

        ReportPersonDTO reportPersonDTO = new ReportPersonDTO();
        reportPersonDTO.setId(report.getId());
        reportPersonDTO.setPerson(personDto);
        reportPersonDTO.setLastSeenLocation(locationDetails);
        return reportPersonDTO;
    }


}
