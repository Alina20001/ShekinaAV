package com.alina.shekina.repository;

import com.alina.shekina.entity.TypeRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRoomRepository extends CrudRepository<TypeRoom,Long> {

    boolean existsByTypeRoom(String typeRoom);
}
