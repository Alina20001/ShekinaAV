package com.alina.shekina.repository;

import com.alina.shekina.entity.Facilities;
import com.alina.shekina.entity.TypeRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilitiesRepository extends CrudRepository<Facilities,Long> {

    List<Facilities> findAllByTypeRoomSetContains(TypeRoom typeRoom);
}
