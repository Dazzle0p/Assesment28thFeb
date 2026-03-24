package com.company.controller;

import com.company.dto.*;
import com.company.service.IssueService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @GetMapping
    public List<IssueResponseDTO> getAllIssues() {
        return service.getAllIssues();
    }

    @PostMapping
    public IssueResponseDTO raise(@RequestBody IssueRequestDTO dto) {
        return service.raiseIssue(dto);
    }

    @GetMapping("/unresolved")
    public List<IssueResponseDTO> unresolved() {
        return service.getUnresolved();
    }

    @GetMapping("/this-month")
    public List<IssueResponseDTO> thisMonth() {
        return service.getThisMonth();
    }

    @GetMapping("/employees-this-month")
    public List<String> employeesThisMonth() {
        return service.getEmployeeNamesThisMonth();
    }
}