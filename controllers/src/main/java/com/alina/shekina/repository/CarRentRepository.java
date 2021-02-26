package com.alina.shekina.repository;

import com.alina.shekina.entity.CarRent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentRepository extends CrudRepository<CarRent,Long> {
}
