package com.alina.shekina.entity;

import com.google.common.primitives.Bytes;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Entity
@Getter
@Setter
public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_arrival")
    private LocalDate arrival;

    @Column(name = "date_departure")
    private LocalDate departure;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToMany
    private Set<Services> servicesSet;

    @OneToMany(mappedBy = "residence")
    private Set<CarRent> carRentSet;

    @Column(name = "actual_checkOut")
    private LocalDate actualCheckOut;

    public void addService(Services services){
        this.servicesSet.add(services);
    }

    public long getDays(){
        return ChronoUnit.DAYS.between(arrival,departure);
    }

    public Long getAmount(){
        int servicesCost = 0;
        for (Services service: this.servicesSet
             ) {
            servicesCost+= service.getPrice() * this.getDays();
        }
        int carCost = 0;
        for (CarRent car: carRentSet
             ) {
            carCost += car.getCar().getPrice() * this.getDays();
        }
        return carCost+servicesCost + this.room.getDayCost()*this.getDays();
    }
}
