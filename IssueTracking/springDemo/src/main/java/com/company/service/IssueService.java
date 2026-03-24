package com.company.service;

import com.company.dto.IssueRequestDTO;
import com.company.dto.IssueResponseDTO;

import java.util.List;

public interface IssueService {

    IssueResponseDTO raiseIssue(IssueRequestDTO dto);

    List<IssueResponseDTO> getUnresolved();

    List<IssueResponseDTO> getThisMonth();

    List<String> getEmployeeNamesThisMonth();

    List<IssueResponseDTO> getAllIssues();
}