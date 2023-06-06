package com.latviangirls.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/*import ch.qos.logback.core.model.Model;*/
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping
public class TodoController {

    @Autowired
    public TodoService todoService;

    /*@GetMapping("/profile/todo")
    public String displayTodoPage(){
        return "userPage";
    }*/

   @GetMapping("/profile/todo")
    public String todoList(Model model){
       model.addAttribute("todo", todoService.findAllTodo());
        return "todoPage";
    }
    @CrossOrigin
    @GetMapping("/profile/todo/addTodo")
    public String createTodo (Model model){
        Todo todo = new Todo();
        model.addAttribute("todos", todo);
        return  "add_todo";
    }
    @CrossOrigin
    @PostMapping("/todo")
    public String saveTodo(Todo todo){
        todoService.saveTodo(todo);
        return "redirect:/profile/todo/allTodo";
    }
    @CrossOrigin
    @GetMapping("profile/todo/allTodo")
    public String getAllTodo() {
        return "todo/allTodo";
    }
    @CrossOrigin
    @GetMapping("profile/todo/{id}")
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
    public String updateTodo (@PathVariable Long id, @RequestBody Todo todoRequest){
        Todo updatedTodo = todoService.updateTodo(id, todoRequest);
        if (updatedTodo !=null) {
            return String.valueOf(ResponseEntity.ok(updatedTodo));
        }else{
            return String.valueOf(ResponseEntity.notFound().build());
        }
    }


  /*  @GetMapping("/todo/updateTodo")
    public String updateTodoPage(@PathVariable Long id, Model model){
        model.addAttribute("todos", todoService.updateTodo());
        return "update_todo";
    }*/

    @CrossOrigin
    @DeleteMapping("profile/todo/{id}")
    public String deleteTodo (@PathVariable Long id){
        todoService.deleteTodo(id);
        return "redirect:/profile/todo";
    }


}
