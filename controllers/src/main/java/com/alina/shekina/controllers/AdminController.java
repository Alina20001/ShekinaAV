package com.alina.shekina.controllers;

import com.alina.shekina.entity.*;
import com.alina.shekina.repository.*;
import com.alina.shekina.services.DBFileService;
import com.alina.shekina.services.RoomService;
import com.alina.shekina.services.ServicesService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
@Setter(onMethod_ = @Autowired)
public class AdminController extends RoomController {

    private CarRepository carRepository;
    private ServicesService servicesService;
    private RoomService roomService;
    private TypeRoomRepository typeRoomRepository;
    private DBFileRepository fileRepository;
    private DBFileService dbFileService;
    private FacilitiesRepository facilitiesRepository;
    private RoomRepository roomRepository;

    @GetMapping("/addEntity")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addEntity(){
        return "admin/addEntity";
    }

    @PostMapping("/addCar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCar(@RequestParam String mark, @RequestParam Integer price){
        Car car = new Car();
        car.setMark(mark);
        car.setPrice(price);
        car.setCarStatus(CarStatus.FREE);
        carRepository.save(car);
        return "redirect:/admin/addEntity";
    }

    @PostMapping(value = "/addService")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addService(@RequestParam String name, @RequestParam Integer price){
        servicesService.addService(name,price);
        return "redirect:/admin/addEntity";
    }

    @PostMapping("/addRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addRoom(@RequestParam Integer costDay,@RequestParam Integer numSeats, @RequestParam Long typeId){
        Optional<TypeRoom> typeRoom = typeRoomRepository.findById(typeId);
        roomService.addRoom(costDay, numSeats, typeRoom.get());
        return "redirect:/admin/addEntity";
    }

    @PostMapping("/addTypeRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addTypeRoom(@RequestParam String name, @RequestParam MultipartFile[] picture) throws Exception {
        if(!typeRoomRepository.existsByTypeRoom(name)){
            TypeRoom typeRoom = new TypeRoom();
            typeRoom.setTypeRoom(name);
            Set<DBFile> files = new HashSet<>();
            for (MultipartFile pic: picture
                 ) {
                DBFile file = new DBFile();
                file.setData(pic.getBytes());
                file.setFileType(pic.getContentType());
                file.setFileName(pic.getOriginalFilename());
                dbFileService.save(file);
                files.add(file);
            }
            typeRoom.setPictures(files);
            typeRoomRepository.save(typeRoom);

        } else {
            throw new Exception("Такой тип помещения уже создан");
        }
        return "admin/addEntity";
    }

    @PostMapping("/addFacility")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addFacility(@RequestParam String name){
        Facilities facilities = new Facilities();
        facilities.setFacility(name);
        facilitiesRepository.save(facilities);
        return "admin/addEntity";
    }

    @PostMapping("/bindFacilityAndType")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String bindFacilities(@RequestParam Long fId,@RequestParam Long tId) throws Exception {
        Optional<Facilities> facilities = facilitiesRepository.findById(fId);
        Optional<TypeRoom> typeRoom = typeRoomRepository.findById(tId);
        if(facilities.isPresent() && typeRoom.isPresent()){
            facilities.get().getTypeRoomSet().add(typeRoom.get());
            typeRoom.get().getFacilitiesSet().add(facilities.get());
            facilitiesRepository.save(facilities.get());
            typeRoomRepository.save(typeRoom.get());
        } else {
            throw new Exception("Не найдено");
        }
        return "admin/addEntity";
    }

    @PostMapping("/deleteRoom")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteRoom(@RequestParam Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        room.get().setIsActive(false);
        roomRepository.save(room.get());
        return "redirect:/hotel";
    }
}

