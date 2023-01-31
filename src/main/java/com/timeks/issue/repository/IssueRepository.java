package com.timeks.issue.repository;

import com.timeks.base.enums.IssueStatus;
import com.timeks.base.enums.IssueType;
import com.timeks.issue.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

    @Override
    @Query("select i from Issue i left join fetch i.project ")
    List<Issue> findAll();

    @Query("select i from Issue i left join fetch i.project " +
            "where i.project.id = :id")
    List<Issue> findAllByProjectId(Long id);
    List<Issue> findAllByIssueStatusIn(List<IssueStatus> issueStatuses);

    List<Issue> findAllByIssueTypeIn(List<IssueType> issueTypes);
}
