package com.latviangirls.todo;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    private boolean toDoToUpdate;

    public List<Todo> findAllToDo(){
        return (List<Todo>) todoRepository.findAll();
    }
    @Transactional
    public void addToDo (Todo todo){
        todoRepository.save(todo);

    }

    public Optional <Todo> findSingleToDo (Long id){
        return todoRepository.findById(id);

    }

    @Transactional
    public Todo updateToDo (Long id, Todo todo){
        Todo toDoUpdate = null;
        if (toDoUpdate != null) {
            toDoUpdate.setToBeDone(todo.getToBeDone());
            toDoUpdate.setTimeLimit(todo.getTimeLimit());
            toDoUpdate.setStatus(todo.getStatus());
            todoRepository.save(toDoUpdate);

            return toDoUpdate;

        } else {
            return null;
        }
    }

    @Transactional
    public void deleteToDo (Long id){
        todoRepository.deleteById(id);
    }


}

