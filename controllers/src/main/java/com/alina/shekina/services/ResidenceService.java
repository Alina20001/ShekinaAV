package com.alina.shekina.services;

import com.alina.shekina.entity.CarStatus;
import com.alina.shekina.entity.Residence;
import com.alina.shekina.entity.Status;
import com.alina.shekina.userDetails.UserDetailsImpl;
import com.alina.shekina.entity.User;
import com.alina.shekina.repository.ResidenceRepository;
import com.alina.shekina.repository.ServicesRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Setter(onMethod_ = @Autowired)
public class ResidenceService {

    private ResidenceRepository residenceRepository;
    private ServicesRepository servicesRepository;

    public Residence findActiveResidence(User user) throws Exception {
        Optional<Residence> byUser = residenceRepository.findActiveByUser(user);
        if (byUser.isPresent()){
            return byUser.get();
        }
        throw new Exception("residence not found");
    }

    @Transactional
    public void addService(Long serviceId, User user){
        Optional<Residence> residence = residenceRepository.findActiveByUser(user);
        if (residence.isPresent()){
            residence.get().addService(servicesRepository.findById(serviceId).get());
            residenceRepository.save(residence.get());
        }
    }

    @Transactional
    public void checkOut(UserDetailsImpl userDetails){
        Optional<Residence> residence = residenceRepository.findActiveByUser(userDetails.getUser());
        if (residence.isPresent()){
            residence.get().getCarRentSet().forEach(carRent -> carRent.getCar().setCarStatus(CarStatus.FREE));
            residence.get().setStatus(Status.EXPIRED);
            residence.get().setActualCheckOut(LocalDate.now());
            residenceRepository.save(residence.get());
        }

    }
}
