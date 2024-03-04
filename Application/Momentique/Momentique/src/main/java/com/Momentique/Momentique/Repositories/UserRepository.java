package com.Momentique.Momentique.Repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.Momentique.Momentique.Models.User;

public interface UserRepository extends CrudRepository<User, UUID> {
    
}
