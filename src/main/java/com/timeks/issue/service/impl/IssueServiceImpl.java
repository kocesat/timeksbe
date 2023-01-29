package com.timeks.issue.service.impl;

import com.timeks.base.enums.IssueStatus;
import com.timeks.base.enums.IssueType;
import com.timeks.issue.model.Issue;
import com.timeks.issue.model.IssueDto;
import com.timeks.issue.repository.IssueRepository;
import com.timeks.issue.service.IssueService;
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

    @Override
    public List<Issue> findAll() {
        return issueRepository.findAll();
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
        return issueRepository.save(Issue.from(dto));
    }
}
