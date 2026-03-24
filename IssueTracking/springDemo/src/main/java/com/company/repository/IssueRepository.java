package com.company.repository;

import com.company.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

    List<Issue> findByResolvedFalse();

    List<Issue> findByCreatedDateBetween(LocalDate start, LocalDate end);
}