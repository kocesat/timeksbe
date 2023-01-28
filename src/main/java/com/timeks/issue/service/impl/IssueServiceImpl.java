package com.timeks.issue.service.impl;

import com.timeks.base.enums.Status;
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
    public List<Issue> findByStatus(Status status) {
        return issueRepository.findAllByStatus(status);
    }

    @Override
    public Issue save(IssueDto dto) {
        return issueRepository.save(Issue.of(dto));
    }
}
