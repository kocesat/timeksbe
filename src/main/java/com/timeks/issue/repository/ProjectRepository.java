package com.timeks.issue.repository;

import com.timeks.issue.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  @Query("delete from Project p where p.id = :id")
  @Modifying
  void deleteById(Long id);
}
