package com.lff.api_db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lff.api_db.domain.TaskDomain;
import com.lff.api_db.domain.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<TaskDomain, Long>{
     List<TaskDomain> findByUserId(Long userId);
     List<TaskDomain> findByUserIdAndStatus(Long id, TaskStatus status);
}
