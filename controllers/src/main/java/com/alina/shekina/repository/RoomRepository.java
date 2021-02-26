package com.alina.shekina.repository;

import com.alina.shekina.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<Room,Long> {

    @Query("select r from Room r where r.dayCost <= ?1 and r.seatsAmount >= ?2 and r.isActive = true ")
    List<Room> findAllByDayCostAndSeatsAmount(Integer dayCost, Integer seatsAmount);
}
