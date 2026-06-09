package com.lff.api_db.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lff.api_db.domain.TaskDomain;
import com.lff.api_db.repository.TaskRepository;

@Service
public class TaskService {

     @Autowired
     private TaskRepository repository;

     public Optional<TaskDomain> getById(Long id){
          System.out.println(String.format("[  INFO  ](Get/ID) Buscando tarefa: %d", id));
          return this.repository.findById(id);
     }

     public List<TaskDomain> getAll(){
          System.out.println("[  INFO  ](Get/All) Buscando todas as tarefas");
          return this.repository.findAll();
     }

     public TaskDomain createTask(TaskDomain task){
          System.out.println(String.format("[  INFO  ](Post) Criando tarefa: %s", task.getTitle()));
          return this.repository.save(task);
     }

     public void deleteTask(Long id){
          System.out.println(String.format("[  WARN  ](Delete) Deletando tarefa: %d", id));
          this.repository.deleteById(id);
     }

     public TaskDomain putTask(Long id, TaskDomain task){
          Optional<TaskDomain> taskOpt = this.repository.findById(id);
          
          if (taskOpt.isPresent()){
               TaskDomain taskDB = taskOpt.get();
               taskDB.setTitle(task.getTitle());
               taskDB.setDescription(task.getDescription());
               taskDB.setStatus(task.getStatus());
               taskDB.setUser(task.getUser());
               taskDB.setCreatedAt(task.getCreatedAt());
               taskDB.setUpdateAt(task.getUpdateAt());
               return this.repository.save(taskDB);
          } else {
               System.out.println(String.format("[  ERRO  ](Put/ID) Tarefa não encontrada: %d", id));
               throw new IllegalArgumentException("Tarefa não encontrada com ID: " + id);
          }
     }
}
