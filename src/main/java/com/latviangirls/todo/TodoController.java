package com.latviangirls.todo;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TodoController {

    @Autowired
    public TodoService todoService;

    @CrossOrigin
    @PostMapping("/todo")
    public Todo createToDo (@RequestBody() Todo toDoRequest){
        Todo todo = new Todo();
        todo.setToBeDone(toDoRequest.getToBeDone());
        todo.setTimeLimit(toDoRequest.getTimeLimit());
        todo.setStatus(toDoRequest.getStatus());
        todoService.addToDo(todo);
        return  todo;
    }

    @CrossOrigin
    @GetMapping("/todo")
    public List getAllToDo(List allToDo) {
        return allToDo;
    }

    @CrossOrigin
    @GetMapping("/todo/{id}")
    public ResponseEntity <Todo> getToDo(@PathVariable Long id){
        Optional <Todo> todo = todoService.findSingleToDo(id);

        if (todo.isPresent()) {
            return ResponseEntity.ok(todo.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PutMapping("/todo/{id}")

    public ResponseEntity<Todo>updateToDo (@PathVariable Long id, @RequestBody Todo toDoRequest){
        Todo updatedToDo = todoService.updateToDo(id, toDoRequest);
        if (updatedToDo !=null) {
            return ResponseEntity.ok(updatedToDo);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin
    @DeleteMapping("/todo/{id}")
    public void deleteToDo (@PathVariable Long id){
        todoService.deleteToDo(id);
    }



}

