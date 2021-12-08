package com.example.car_rental.repository;

import com.example.car_rental.model.Car;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long > {

    @Query("Select c from Car c")
    List<Car> findAllCars(Pageable page);

}
