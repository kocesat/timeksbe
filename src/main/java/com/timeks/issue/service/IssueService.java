package com.timeks.issue.service;

import com.timeks.base.enums.IssueStatus;
import com.timeks.base.enums.IssueType;
import com.timeks.issue.model.Issue;
import com.timeks.issue.model.IssueDto;

import java.util.List;

public interface IssueService {

    List<Issue> findAll();

    List<Issue> findByStatuses(List<IssueStatus> issueStatuses);

    List<Issue> findByTypes(List<IssueType> issueTypes);

    Issue save(IssueDto dto);
}
