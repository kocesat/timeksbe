package com.timeks.issue.service;

import com.timeks.base.enums.Status;
import com.timeks.issue.model.Issue;
import com.timeks.issue.model.IssueDto;

import java.util.List;

public interface IssueService {

    List<Issue> findAll();

    List<Issue> findByStatus(Status status);

    Issue save(IssueDto dto);
}
