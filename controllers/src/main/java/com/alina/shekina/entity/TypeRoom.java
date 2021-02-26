package com.alina.shekina.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class TypeRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "typeRoom")
    private String typeRoom;

    @ManyToMany
    private Set<Facilities> facilitiesSet;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<DBFile> pictures;
}
