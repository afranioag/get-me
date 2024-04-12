package com.aag.getme.repository;

import com.aag.getme.model.Report;
import com.aag.getme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByUser(User user);
}
