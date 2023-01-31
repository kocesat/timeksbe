package com.timeks.issue.service.impl;

import com.timeks.base.enums.IssueStatus;
import com.timeks.base.enums.IssueType;
import com.timeks.issue.model.Issue;
import com.timeks.issue.model.dto.IssueDto;
import com.timeks.issue.repository.IssueRepository;
import com.timeks.issue.repository.ProjectRepository;
import com.timeks.issue.service.IssueService;
import com.timeks.issue.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final ProjectService projectService;

    @Override
    public List<Issue> findAll() {
        return issueRepository.findAll();
    }

    @Override
    public List<Issue> findAllByProjectId(Long id) {
        return issueRepository.findAllByProjectId(id);
    }

    @Override
    public List<Issue> findByStatuses(List<IssueStatus> issueStatuses) {
        return issueRepository.findAllByIssueStatusIn(issueStatuses);
    }

    @Override
    public List<Issue> findByTypes(List<IssueType> issueTypes) {
        return issueRepository.findAllByIssueTypeIn(issueTypes);
    }

    @Override
    public Issue save(IssueDto dto) {
        final var issue = Issue.from(dto);
        if (dto.getProjectId() != null) {
            final var project = projectService.findById(dto.getProjectId());
            issue.setProject(project);
        }
        return issueRepository.save(issue);
    }
}
