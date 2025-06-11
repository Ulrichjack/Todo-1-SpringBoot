package com.hades.todo1.controller;

import com.hades.todo1.dto.ApiResponse;
import com.hades.todo1.model.Priority;
import com.hades.todo1.model.Todo;
import com.hades.todo1.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService todoService;
    public TodoController (TodoService todoService){
        this.todoService=todoService;
    }

    @GetMapping
    public ResponseEntity <ApiResponse <List <Todo>>> allTask(){

        List<Todo> task = todoService.getAllTask();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok(task,"Liste recuperee avec succes"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createTodo ( @RequestBody Todo todo){

        Todo savedTodo = todoService.createTask(todo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.create(savedTodo,"Tache ajoutee avec succes"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTodo (@PathVariable Long id){

         todoService.deleteTask(id);
         return ResponseEntity
                 .status(HttpStatus.NO_CONTENT)
                 .body(ApiResponse.noContent("Tache supprimée avec succes"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> uptdateTodo(@PathVariable Long id, @RequestBody Todo todo){

        Todo modifieTodo = todoService.uptadeTask(id, todo);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok(modifieTodo,"Tache mise a jour "));
    }

    @GetMapping("/search")

    public ResponseEntity<ApiResponse<List<Todo>>> searchTodo(
            @RequestParam(required = false) String title,
            @RequestParam(required = false)Priority priority,
            @RequestParam (required = false)Boolean complete
            ){
        List <Todo> task = todoService.searchTask(title, priority, complete);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok(task,"Recherche effectuer avec success"));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Todo>> getTaskById(@PathVariable Long id) {
        Todo task = todoService.getTaskById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.ok(task, "Tâche récupérée avec succès"));
    }
}


