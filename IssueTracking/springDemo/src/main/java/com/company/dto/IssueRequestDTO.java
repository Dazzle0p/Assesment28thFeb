package com.company.dto;

import lombok.Data;

@Data
public class IssueRequestDTO {
    private String description;
    private String category;
    private Integer employeeId;
}