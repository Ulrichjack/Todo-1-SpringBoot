package com.hades.todo1.service;

import com.hades.todo1.model.SubTask;
import com.hades.todo1.model.Todo;
import com.hades.todo1.repo.SubTaskRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubTaskService {
    private final SubTaskRepository subTaskRepository;
    private final TodoService todoService;

    public SubTaskService(SubTaskRepository subTaskRepository, TodoService todoService) {
        this.subTaskRepository = subTaskRepository;
        this.todoService = todoService;
    }

    public SubTask createSubTask(Long todoId, SubTask subTask) {
        Todo parent = todoService.getTaskById(todoId);
        subTask.setParentTask(parent);
        subTask.setCreatedAt(LocalDateTime.now());
        return subTaskRepository.save(subTask);
    }

    public List<SubTask> getSubTasksForTodo(Long todoId) {
        return subTaskRepository.findByParentTaskId(todoId);
    }

    public SubTask updateSubTask(Long id, SubTask subTask) {
        SubTask existing = subTaskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sous-tâche non trouvée"));

        existing.setTitle(subTask.getTitle());
        existing.setDescription(subTask.getDescription());
        existing.setComplete(subTask.isComplete());
        existing.setUpdatedAt(LocalDateTime.now());

        return subTaskRepository.save(existing);
    }
}
