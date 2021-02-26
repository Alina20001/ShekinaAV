package com.alina.shekina.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Facilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_facility")
    private String facility;

    @ManyToMany
    private Set<TypeRoom> typeRoomSet;
}
