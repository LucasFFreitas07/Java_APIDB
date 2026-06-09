package com.lff.api_db.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDomain {

     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     private Long id;

     @Column(nullable=false)
     private String title;

     @Column(columnDefinition="TEXT")
     private String description;
     
     @Enumerated(EnumType.STRING)
     @Column(nullable=false)
     private TaskStatus status;

     @ManyToOne(fetch=FetchType.LAZY)
     @JoinColumn(name="user_id", nullable=false)
     @JsonBackReference
     private UserDomain user;

     @CreationTimestamp
     @Column(nullable=false, updatable=true)
     private LocalDateTime createdAt;

     @UpdateTimestamp
     private LocalDateTime updateAt;

}
