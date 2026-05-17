package com.lff.api_db.dto;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDTO(
     Optional<String> firstName, 
     Optional<String> lastName, 
     Optional<String> username, 
     Optional<String> email, 
     Optional<Boolean> active
) {}
