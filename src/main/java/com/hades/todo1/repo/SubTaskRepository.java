package com.hades.todo1.repo;

import com.hades.todo1.model.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {
    List<SubTask> findByParentTaskId(Long todoId);
}
