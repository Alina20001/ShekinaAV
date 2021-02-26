package com.alina.shekina.services;

import com.alina.shekina.entity.Car;
import com.alina.shekina.entity.CarRent;
import com.alina.shekina.entity.CarStatus;
import com.alina.shekina.entity.Residence;
import com.alina.shekina.repository.CarRentRepository;
import com.alina.shekina.repository.CarRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Setter(onMethod_ = @Autowired)
public class CarService {

    private CarRepository carRepository;
    private CarRentRepository carRentRepository;

    @Transactional
    public void addCarRent(Residence residence, Long carId){
        Optional<Car> car = carRepository.findById(carId);
        car.get().setCarStatus(CarStatus.BUSY);
        CarRent carRent = new CarRent();
        carRent.setCar(car.get());
        carRent.setResidence(residence);
        carRentRepository.save(carRent);
    }
}
