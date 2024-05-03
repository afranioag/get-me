package com.aag.getme.service;

import com.aag.getme.dto.InformationDTO;
import com.aag.getme.dto.PersonDTO;
import com.aag.getme.dto.ReportPersonDTO;
import com.aag.getme.dto.ReportPersonInformationDTO;
import com.aag.getme.model.*;
import com.aag.getme.repository.ReportRepository;
import com.aag.getme.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    private static final String URL_FOTO_DEFAULT = "https://aag-s3.s3.sa-east-1.amazonaws.com/perfil1.png";
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

    @Autowired
    private S3Service service;

    @Transactional
    public ReportPersonDTO save(ReportPersonDTO reportPersonDTO) {
        User user = userService.getUserAuthenticated();

        String url = URL_FOTO_DEFAULT;

        if(!StringUtils.isAllBlank(reportPersonDTO.getPerson().getImage())) {
           url = service.uploadFile(reportPersonDTO.getPerson().getImage());

        }
        reportPersonDTO.getPerson().setImage(url);

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

    public ReportPersonDTO findId(Long id) {
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe um report com o id passado"));
        return getReportPersonDTO(report);

    }

    public List<ReportPersonDTO> findAll() {
        return reportRepository.findAll().stream().map(this::getReportPersonDTO).toList();

    }

    @Transactional(readOnly = true)
    public List<ReportPersonInformationDTO> findAllByUser() {
        long userId = 1;
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User não existe"));
        List<Report> reports = reportRepository.findAllByUser(user);
        return reports.stream().map(this::getReportPersonInformationDTO).toList();
    }

    private ReportPersonInformationDTO getReportPersonInformationDTO(Report report) {
        PersonDTO personDto = modelMapper.map(report.getPerson(), PersonDTO.class);
        LocationDetails locationDetails = modelMapper.map(report.getLastSeenLocation(), LocationDetails.class);

        List<InformationDTO> informationsDTO = report.getInformations().stream().map(information -> modelMapper.map(information, InformationDTO.class)).toList();

        ReportPersonInformationDTO reportPersonDTO = new ReportPersonInformationDTO();
        reportPersonDTO.setId(report.getId());
        reportPersonDTO.setPerson(personDto);
        reportPersonDTO.setLastSeenLocation(locationDetails);
        reportPersonDTO.setInformation(informationsDTO);
        return reportPersonDTO;
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

    @Transactional
    public void delete(long id) {
        User user = userService.getUserAuthenticated();
        Report report = reportRepository.findById(id).orElseThrow(() -> new RuntimeException("Não existe um report com o id passado"));

        if(!user.equals(report.getUser()))
            throw new RuntimeException("Report não pertence ao usuário");

        reportRepository.delete(report);

    }
}
