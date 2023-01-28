package com.timeks.issue.repository;

import com.timeks.base.enums.Status;
import com.timeks.issue.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAllByStatus(Status status);
}
