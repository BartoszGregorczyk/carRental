package com.example.car_rental.service;

import com.example.car_rental.model.Car;
import com.example.car_rental.repository.CarRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@Service
public class CarService {

    private static final int PAGE_SIZE = 10;
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars(int page, Sort.Direction sort){
        return carRepository.findAllCars(
                PageRequest.of(page, PAGE_SIZE,
                        Sort.by(sort,"id")
                )
        );
    }

    public Car getSingleCar(long id) {
        return carRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Car returnCar(long id) {
        Car temporaryCar = carRepository.findById(id).orElseThrow();
        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (temporaryCar.is_rented() && Objects.equals(temporaryCar.getRent_by(), userName)) {
            temporaryCar.set_rented(false);
            temporaryCar.setRent_by("");
        }

        //further instructions if the car is not rented...
        return temporaryCar;
    }

    @Transactional
    public Car rentCar(long id) {
       Car temporaryCar = carRepository.findById(id).orElseThrow();
       if (!temporaryCar.is_rented()) {
           temporaryCar.set_rented(true);
           String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           temporaryCar.setRent_by(userName);
       }
           //further instructions if the car is rented...
        return temporaryCar;
    }

    public Car addCar(Car car) {
        car.set_rented(false);
        car.setRent_by("");
        return carRepository.save(car);
    }

    @Transactional
    public Car editCar(Car car) {
        Car carEdited = carRepository.findById(car.getId()).orElseThrow();

        if (!car.getColor().isEmpty())
            carEdited.setColor(car.getColor());

        if(car.getCapacity() != 0)
            carEdited.setCapacity(car.getCapacity());

        if (car.getHorse_power() != 0)
            carEdited.setHorse_power(car.getHorse_power());

        if (car.getProduction_year() != 0)
            carEdited.setProduction_year(car.getProduction_year());

        return carEdited;
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }


}


