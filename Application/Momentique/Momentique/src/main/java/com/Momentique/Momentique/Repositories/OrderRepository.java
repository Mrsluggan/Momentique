package com.Momentique.Momentique.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.Momentique.Momentique.Models.Orders;


import jakarta.transaction.Transactional;

public interface OrderRepository extends CrudRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.temporaryUUID = ?1")
    Orders  searchOrderByTU(UUID TU);

    @Transactional
    @Modifying
    @Query ("UPDATE Orders o SET o.temporaryUUID = ?1 WHERE o.temporaryUUID = ?2")
    int eraseTemporaryUUID(UUID updatedUuid, UUID uuid);
}