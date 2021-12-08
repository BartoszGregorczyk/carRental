package com.example.car_rental.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String model;
    private String color;
    private int capacity; // [cm^3]
    private int horse_power;
    private boolean is_rented;
    private int production_year; //only year
    private String rent_by;
}


