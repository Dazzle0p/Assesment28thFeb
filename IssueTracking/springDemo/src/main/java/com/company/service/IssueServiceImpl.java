package com.company.service;

import com.company.dto.*;
import com.company.model.*;
import com.company.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IssueServiceImpl implements IssueService {

    private static final Logger log = LoggerFactory.getLogger(IssueServiceImpl.class);

    private final IssueRepository issueRepo;
    private final EmployeeRepository empRepo;

    public IssueServiceImpl(IssueRepository issueRepo, EmployeeRepository empRepo) {
        this.issueRepo = issueRepo;
        this.empRepo = empRepo;
    }

    @Override
    public IssueResponseDTO raiseIssue(IssueRequestDTO dto) {

        log.info("Raising issue for employee ID: {}", dto.getEmployeeId());

        Employee emp = empRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Issue issue = new Issue();
        issue.setDescription(dto.getDescription());
        issue.setCategory(IssueCategory.valueOf(dto.getCategory()));
        issue.setResolved(false);
        issue.setCreatedDate(LocalDate.now());
        issue.setEmployee(emp);

        Issue saved = issueRepo.save(issue);

        return mapToDTO(saved);
    }

    @Override
    public List<IssueResponseDTO> getUnresolved() {
        log.info("Fetching unresolved issues");

        return issueRepo.findByResolvedFalse()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<IssueResponseDTO> getThisMonth() {

        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = LocalDate.now();

        return issueRepo.findByCreatedDateBetween(start, end)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEmployeeNamesThisMonth() {

        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = LocalDate.now();

        return issueRepo.findByCreatedDateBetween(start, end)
                .stream()
                .map(i -> i.getEmployee().getFirstName())
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<IssueResponseDTO> getAllIssues() {

        log.info("Fetching all issues");

        return issueRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private IssueResponseDTO mapToDTO(Issue issue) {
        IssueResponseDTO dto = new IssueResponseDTO();
        dto.setId(issue.getId());
        dto.setDescription(issue.getDescription());
        dto.setCategory(issue.getCategory().name());
        dto.setResolved(issue.isResolved());
        dto.setEmployeeName(issue.getEmployee().getFirstName());
        return dto;
    }
}