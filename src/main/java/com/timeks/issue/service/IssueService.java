package com.timeks.issue.service;

import com.timeks.base.enums.IssueStatus;
import com.timeks.base.enums.IssueType;
import com.timeks.issue.model.Issue;
import com.timeks.issue.model.dto.IssueDto;
import com.timeks.issue.model.dto.IssueStatusUpdateDto;
import com.timeks.issue.model.dto.IssueUpdateDto;

import java.util.List;

public interface IssueService {

  Issue update(IssueUpdateDto dto);

  List<Issue> findAll();

  List<Issue> findAllByProjectId(Long id);

  List<Issue> findByStatuses(List<IssueStatus> issueStatuses);

  List<Issue> findByTypes(List<IssueType> issueTypes);

  Issue save(IssueDto dto);

  Issue updateClose(Long issueId, boolean closed);

  Issue updateStatus(IssueStatusUpdateDto dto);

  void delete(Long issueId);
}
