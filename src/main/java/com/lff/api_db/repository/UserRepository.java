package com.lff.api_db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lff.api_db.domain.UserDomain;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long>{

     public List<UserDomain> findByEmail(String email);

     List<UserDomain> findByActive(Boolean active);

     List<UserDomain> findByFirstName(String firstName);

     List<UserDomain> findByLastName (String lastName);

}