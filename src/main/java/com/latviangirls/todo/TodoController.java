package com.latviangirls.todo;

import org.springframework.ui.Model;

/*import ch.qos.logback.core.model.Model;*/
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    @Autowired
    public TodoService todoService;

    /*@GetMapping("/profile")
    public String displayTodoPage(){
        return "todoPage";
    }*/

   @GetMapping("/")
    public String todoList(Model model){
       model.addAttribute("todo", todoService.findAllTodo());
        return "userPage";
    }

    @CrossOrigin
    @PostMapping("/todo/addTodo")
    public String createTodo (@RequestBody() Todo todoRequest){
        Todo todo = new Todo();
        todo.setToBeDone(todoRequest.getToBeDone());
        todo.setTimeLimit(todoRequest.getTimeLimit());
        todo.setStatus(todoRequest.getStatus());
        todoService.addTodo(todo);
        return  "redirect:profile/todo";
    }

    @CrossOrigin
    @GetMapping("/todo")
    public String getAllTodo(List allTodo) {
        return "allTodo";
    }

    @CrossOrigin
    @GetMapping("/todo/{id}")
    public ResponseEntity <Todo> getTodo(@PathVariable Long id){
        Optional <Todo> todo = todoService.findSingleTodo(id);

        if (todo.isPresent()) {
            return ResponseEntity.ok(todo.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin
    @PutMapping("/todo/{id}")
    public ResponseEntity<Todo>updateTodo (@PathVariable Long id, @RequestBody Todo todoRequest){
        Todo updatedTodo = todoService.updateTodo(id, todoRequest);
        if (updatedTodo !=null) {
            return ResponseEntity.ok(updatedTodo);
        }else{
            return  ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin
    @DeleteMapping("/todo/{id}")
    public void deleteTodo (@PathVariable Long id){
        todoService.deleteTodo(id);
    }
}
