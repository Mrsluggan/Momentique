package com.Momentique.Momentique.Repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import com.Momentique.Momentique.Models.Orders;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT o FROM Orders o WHERE o.temporaryUUID = ?1")
    List<Orders>  searchOrderByTU(UUID TU);

    @Transactional
    @Modifying
    @Query ("UPDATE Orders o SET o.temporaryUUID = ?1 WHERE o.temporaryUUID = ?2")
    int eraseTemporaryUUID(UUID updatedUuid, UUID uuid);

    // Orders findTopByUserUuidOrderByOrderIdDesc(UUID userUuid);
    Orders findTopByOrderByOrderIdDesc();


}