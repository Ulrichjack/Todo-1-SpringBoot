package com.hades.todo1.controller;

import com.hades.todo1.dto.ApiResponse;
import com.hades.todo1.model.SubTask;
import com.hades.todo1.service.SubTaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo/{todoId}/subtasks")
public class SubTaskController {
    private final SubTaskService subTaskService;

    public SubTaskController(SubTaskService subTaskService) {
        this.subTaskService = subTaskService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SubTask>> createSubTask(
            @PathVariable Long todoId,
            @RequestBody SubTask subTask) {
        SubTask created = subTaskService.createSubTask(todoId, subTask);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.create(created, "Sous-tâche créée avec succès"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SubTask>>> getSubTasks(
            @PathVariable Long todoId) {
        List<SubTask> subTasks = subTaskService.getSubTasksForTodo(todoId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok(subTasks, "Sous-tâches récupérées"));
    }
}
