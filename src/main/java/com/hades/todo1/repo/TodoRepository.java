package com.hades.todo1.repo;

import com.hades.todo1.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TodoRepository extends JpaRepository<Todo,Long> , JpaSpecificationExecutor<Todo> {
}
