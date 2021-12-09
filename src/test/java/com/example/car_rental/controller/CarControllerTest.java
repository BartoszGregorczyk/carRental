package com.example.car_rental.controller;

import com.example.car_rental.model.Car;
import com.example.car_rental.repository.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CarRepository carRepository;


    @Test
    @Transactional
    @WithMockUser
    void shouldGetSingleCarAndReturnOrRentCar() throws Exception {
        // given
        //--------------car data-------------
        String model = "test_car";
        int year = 2000;
        String color = "test color";
        int capacity = 123;
        int horsePower = 1234;
        boolean isRented = false;
        String rentBy = "";
        //-----------------------------------

        Car tempCar = new Car();
        tempCar.setModel(model);
        tempCar.setProduction_year(year);
        tempCar.setColor(color);
        tempCar.setCapacity(capacity);
        tempCar.setHorse_power(horsePower);
        tempCar.set_rented(isRented);
        tempCar.setRent_by(rentBy);
        carRepository.save(tempCar);

        // when
        MvcResult mvcResult = mockMvc.perform(get("/cars/" + tempCar.getId()))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        // then
        Car car = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Car.class);
        assertThat(car).isNotNull();
        assertThat(car.getId()).isEqualTo(tempCar.getId());
        assertThat(car.getModel()).isEqualTo(model);
        Assertions.assertEquals(car.getProduction_year(), year);
        assertThat(car.getColor()).isEqualTo(color);
        Assertions.assertEquals(car.getCapacity(),capacity);
        Assertions.assertEquals(car.getHorse_power(), horsePower);
        Assertions.assertEquals(car.is_rented(), isRented);
        assertThat(car.getRent_by()).isEqualTo(rentBy) ;
    }


}

