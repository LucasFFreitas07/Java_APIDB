package com.lff.api_db.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lff.api_db.domain.UserDomain;
import com.lff.api_db.dto.UserDTO;
import com.lff.api_db.repository.UserRepository;

@Service
public class UserService {

     @Autowired
     private UserRepository repository;

     public Optional<UserDomain> getById(Long id){
          System.out.println(String.format("[  INFO  ](Get/ID) Buscando usuário: %d", id));
          return this.repository.findById(id);
     }

     public List<UserDomain> getAll(){
          System.out.println("[  INFO  ](Get/All) Buscando todos os usuários");
          return this.repository.findAll();
     }

     public void deleteUser(Long id){
          System.out.println(String.format("[  WARN  ](Delete) Deletando usuário: %d", id));
          this.repository.deleteById(id);
     }

     public UserDomain createUser(UserDomain user){
          System.out.println(String.format("[  INFO  ](Post) Criando usuário: %s", user.getUsername()));
          return this.repository.save(user);
     }

     public UserDomain putUser(Long id, UserDomain user) {
          Optional<UserDomain> userOpt = this.repository.findById(id);
          
          if (userOpt.isPresent()) {
               UserDomain userDB = userOpt.get();
               userDB.setFirstName(user.getFirstName());
               userDB.setLastName(user.getLastName());
               userDB.setUsername(user.getUsername());
               userDB.setEmail(user.getEmail());
               userDB.setActive(user.getActive());
               System.out.println(String.format("[  INFO  ](Put/ID) Atualizando usuário completo: %d", id));
               return this.repository.save(userDB);
          } else {
               System.out.println(String.format("[  ERRO  ](Put/ID) Usuário não encontrado: %d", id));
               throw new IllegalArgumentException("Usuário não encontrado com ID: " + id);
          }

     }

     public UserDomain patchUser(Long id, UserDTO dto) {
          Optional<UserDomain> userOpt = this.repository.findById(id);
          
          if (userOpt.isPresent()) {
               UserDomain userDB = userOpt.get();
               
               dto.firstName().ifPresent(userDB::setFirstName);
               dto.lastName().ifPresent(userDB::setLastName);
               dto.username().ifPresent(userDB::setUsername);
               dto.email().ifPresent(userDB::setEmail);
               dto.active().ifPresent(userDB::setActive);
               System.out.println(String.format("[  INFO  ](Patch/ID) Atualizando usuário: %d", id));
               return this.repository.save(userDB);
          } else {
               System.out.println(String.format("[  ERRO  ](Patch/ID) Usuário não encontrado: %d", id));
               return null;
               }
          }
}
