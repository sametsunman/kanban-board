package com.canban.canbanapp.repository;

import com.canban.canbanapp.model.TaskLogging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskLoggingRepository extends JpaRepository<TaskLogging, Integer> {
}
