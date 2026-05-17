package com.lff.api_db.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {

     @Id
     @GeneratedValue(strategy=GenerationType.AUTO)
     private Long id;

     private String firstName;

     private String lastName;

     private String username;

     private String email;
     
     private Boolean active;

}
