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
    private boolean updateTodo;

    public List<Todo> findAllTodo(){
        return (List<Todo>) todoRepository.findAll();
    }

    @Transactional
    public Todo saveTodo(Todo todo){
        return todoRepository.save(todo);
    }
    @Transactional
    public void addTodo (Todo todo){
        todoRepository.save(todo);

    }

    public Optional <Todo> findSingleTodo (Long id){
        return todoRepository.findById(id);

    }

    @Transactional
    public Todo updateTodo (Long id, Todo todoRequest){
        Todo updateTodo = null;
        if (updateTodo != null) {
            updateTodo.setToBeDone(todoRequest.getToBeDone());
            updateTodo.setTimeLimit(todoRequest.getTimeLimit());
            updateTodo.setStatus(todoRequest.getStatus());
            todoRepository.save(updateTodo);

            return updateTodo;

        } else {
            return null;
        }
    }

    @Transactional
    public void deleteTodo (Long id){
        todoRepository.deleteById(id);
    }



}
