package com.alina.shekina.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_seats")
    private Integer seatsAmount;

    @Column(name = "cost_day")
    private Integer dayCost;

    @ManyToOne(cascade = ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "type")
    private TypeRoom typeRoom;

    @Column(name = "status")
    private Boolean isActive;
}
