package com.hades.todo1.service;

import com.hades.todo1.exception.RessourceNotFoundException;
import com.hades.todo1.model.Priority;
import com.hades.todo1.model.Todo;
import com.hades.todo1.repo.TodoRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class TodoService  {

    private TodoRepository todoRepository;
    public  TodoService (TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    public Todo createTask (Todo todo){


        todo.setDateCreated(LocalDateTime.now());
       // todo.setLastUptated(LocalDateTime.now());
        return todoRepository.save(todo);
    }

    public List<Todo> getAllTask() {
        return todoRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate nonRecurrenteEtPasComplete = cb.and(
                    cb.equal(root.get("reccurent"), false),
                    cb.equal(root.get("complete"), false)
            );

            Predicate recurrenteEncoreValide = cb.and(
                    cb.equal(root.get("reccurent"), true),
                    cb.greaterThanOrEqualTo(root.get("dateDefinitive"), LocalDateTime.now())
            );

            predicates.add(cb.or(nonRecurrenteEtPasComplete, recurrenteEncoreValide));

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }


    public void deleteTask(Long id){

        if (!todoRepository.existsById(id)){
            throw new RessourceNotFoundException("La tache avec l'id " + id + "n'existe pas ");
        }
        todoRepository.deleteById(id);

    }

    public Todo uptadeTask(Long id , Todo task){

        Todo taskExist = todoRepository.findById(id)
                .orElseThrow(()-> new RessourceNotFoundException("La tache avec l'id " + id + "n'existe pas elle pourras pas etre modifier"));

        //copie des champs modifiable
        taskExist.setTitle(task.getTitle());
        taskExist.setDescription(task.getDescription());
        taskExist.setComplete(task.isComplete());
        taskExist.setLastUptated(LocalDateTime.now());
        taskExist.setPriority(task.getPriority());


        //lorsque la date est completer on affiche la date
        if(task.isComplete() && taskExist.getDateDefinitive() == null){
            taskExist.setDateDefinitive(LocalDateTime.now());
        }

        return todoRepository.save(taskExist);
    }

    public List <Todo> searchTask(String title, Priority priority, Boolean complete){
        return todoRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(title != null && !title.isEmpty()){
                predicates.add(cb.like(cb.lower(root.get("title")),"%" + title.toLowerCase() + "%"));
            }
            if (priority != null ){
                predicates.add(cb.equal(root.get("priority"),priority));
            }
            if (complete != null){
                predicates.add( cb.equal(root.get("complete"),complete));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public Todo getTaskById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("La tâche avec l'id " + id + " n'existe pas"));
    }


}
