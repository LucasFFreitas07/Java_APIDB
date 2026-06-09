package com.lff.api_db.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lff.api_db.domain.TaskDomain;
import com.lff.api_db.domain.UserDomain;
import com.lff.api_db.dto.TaskRequestDTO;
import com.lff.api_db.repository.UserRepository;
import com.lff.api_db.service.TaskService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

     private final TaskService service;
     private final UserRepository userRepository;

     public TaskController (TaskService service, UserRepository userRepository){
          this.service = service;
          this.userRepository = userRepository;
     }

     @GetMapping
     public List<TaskDomain> getAll(){
          return service.getAll();
     }

     @GetMapping("/{id}")
     public TaskDomain getById(@PathVariable Long id) {
          return service.getById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task não encontrada"));
     }
   
     @PostMapping("{id}")
     public ResponseEntity<TaskDomain> create(
          @PathVariable("id") Long userId,
          @RequestBody @Valid TaskRequestDTO dto) {

          UserDomain user = userRepository.findById(userId)
               .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

          TaskDomain task = new TaskDomain();
          task.setTitle(dto.getTitle());
          task.setDescription(dto.getDescription());
          task.setStatus(dto.getStatus());
          task.setUser(user);
              
          return ResponseEntity.ok(service.createTask(task));
     }

     @PutMapping("{id}")
     @Transactional
     public ResponseEntity<TaskDomain> putTask(@PathVariable Long id, @RequestBody TaskDomain task) {
          TaskDomain updatedTask = service.putTask(id, task);
          return ResponseEntity.ok(updatedTask);
     }

     @DeleteMapping("{id}")
     @Transactional
     public void deleteTask(@PathVariable Long id){ service.deleteTask(id); }
}
