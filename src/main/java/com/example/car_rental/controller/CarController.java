package com.example.car_rental.controller;

import com.example.car_rental.model.Car;
import com.example.car_rental.service.CarService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    public List<Car> getCars(@RequestParam(required = false) Integer page, Sort.Direction sort){
        int pageNumber = page!= null && page >= 0 ? page : 0;
        Sort.Direction sortDirection = sort != null ? sort: Sort.Direction.ASC;
        return carService.getCars(pageNumber, sortDirection);
    }

    @GetMapping("/cars/{id}")
    public Car getSingleCar(@PathVariable long id){
        return  carService.getSingleCar(id);
    }

    @PostMapping("/cars")
    public Car addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @PutMapping("/cars")
    public Car editCar(@RequestBody Car car){
        return carService.editCar(car);
    }

    @PutMapping("/cars/{id}")
    public Car rentCar(@PathVariable long id){
        return carService.rentCar(id);
    }

    @PutMapping("/cars/return/{id}")
    public Car returnCar(@PathVariable long id){
        return carService.returnCar(id);
    }

    @DeleteMapping("/cars/{id}")
    public void deleteCar(@PathVariable long id) {
        carService.deleteCar(id);
    }

}
