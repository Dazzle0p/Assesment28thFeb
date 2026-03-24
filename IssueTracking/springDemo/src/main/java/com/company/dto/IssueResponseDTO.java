package com.company.dto;

import lombok.Data;

@Data
public class IssueResponseDTO {
    private Integer id;
    private String description;
    private String category;
    private boolean resolved;
    private String employeeName;
}