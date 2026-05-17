package com.lff.api_db.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lff.api_db.domain.UserDomain;
import com.lff.api_db.service.UserService;
import com.lff.api_db.dto.*;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/users")
public class UserController {

     @Autowired
     private UserService service;

     @GetMapping
     public List<UserDomain> getAll(){
          return service.getAll();
     }

     @PostMapping
     @Transactional
     public UserDomain createUser(@RequestBody UserDomain user){ return service.createUser(user); }

     @GetMapping("{id}")
     public Optional<UserDomain> getById(@PathVariable Long id){ return service.getById(id); }

     @DeleteMapping("{id}")
     @Transactional
     public void deleteUser(@PathVariable Long id){
          service.deleteUser(id);
     }

     @PatchMapping("{id}")
     @Transactional
     public UserDomain patchUser(@PathVariable Long id, @RequestBody UserDTO dto){
          return service.patchUser(id, dto);
     }

     @PutMapping("/{id}")
     @Transactional
     public ResponseEntity<UserDomain> putUser(@PathVariable Long id, @RequestBody UserDomain user) {
          UserDomain updatedUser = service.putUser(id, user);
          return ResponseEntity.ok(updatedUser);
     }
}

