package com.alina.shekina.services;

import com.alina.shekina.entity.*;
import com.alina.shekina.repository.ResidenceRepository;
import com.alina.shekina.repository.RoomRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Setter(onMethod_ = @Autowired)
public class RoomService {

    private RoomRepository roomRepository;
    private ResidenceRepository residenceRepository;

    @Transactional
    public void addRoom(Integer cost, Integer numSeats, TypeRoom type){
        Room room = new Room();
        room.setDayCost(cost);
        room.setSeatsAmount(numSeats);
        room.setTypeRoom(type);
        room.setIsActive(true);
        roomRepository.save(room);
    }

    public List<Room> findFilteredRoom(Integer dayCost, Integer seatsAmount,LocalDate checkIn, LocalDate checkOut){
        List<Room> rooms = roomRepository.findAllByDayCostAndSeatsAmount(dayCost,seatsAmount);
        List<Residence> residences = residenceRepository.findAllActive();
        for (Residence residence : residences
            ) {
            if (checkIn.isAfter(residence.getArrival()) && checkIn.isBefore(residence.getDeparture()) ||
                checkOut.isAfter(residence.getArrival()) && checkOut.isBefore(residence.getDeparture()) ||
                checkIn.isBefore(residence.getArrival()) && checkOut.isAfter(residence.getDeparture())){
                rooms.remove(residence.getRoom());
            }
        }
        return rooms;
    }

    @Transactional
    public void bookRoom(User user, Long roomId, LocalDate checkIn, LocalDate checkOut){
        Residence residence = new Residence();
        residence.setStatus(Status.ACTIVE);
        residence.setArrival(checkIn);
        residence.setDeparture(checkOut);
        residence.setUser(user);
        residence.setRoom(roomRepository.findById(roomId).get());
        residenceRepository.save(residence);
    }
}
