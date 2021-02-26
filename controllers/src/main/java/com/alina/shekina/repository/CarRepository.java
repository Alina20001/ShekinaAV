package com.alina.shekina.repository;

import com.alina.shekina.entity.Car;
import com.alina.shekina.entity.CarStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car,Long> {

    List<Car> findAllByCarStatus(CarStatus carStatus);
}
