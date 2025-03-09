package de.ait.javalessons.controller;

import de.ait.javalessons.model.*;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.CREATED;

//class RestApiCarControllerTest {
//
//    private RestApiCarController restApiCarController;
//
//    @org.junit.jupiter.api.BeforeEach
//    void setUp() {
//        restApiCarController = new RestApiCarController();//перед каждым тестом
//        // будет заново пересоздаваться restApiCarController
//    }

   // @org.junit.jupiter.api.Test
/*
    void getCarsReturnDefaltCars() {
        //Iterable<Car> resultCarsIterable = restApiCarController.getCars();
        List<Car> resultCars = restApiCarController.getCars();
//        List<Car> resultCars = new ArrayList<>();
//        resultCarsIterable.forEach(resultCars::add);
//        assertEquals(4, resultCars.size());
//        assertEquals("BMW M1", resultCars.get(0).getName());
        assertEquals(4, resultCars.size());
        assertEquals("BMW M1", resultCars.get(0).getName());
    }

    @org.junit.jupiter.api.Test
    void getCarByIdWasFound() {
        Optional<Car> result = restApiCarController.getCarById("1");
        assertTrue(result.isPresent());
        assertEquals("BMW M1", result.get().getName());
    }

    @org.junit.jupiter.api.Test
    void getCarByIdWasNotFound() {
        Optional<Car> result = restApiCarController.getCarById("10");
        assertFalse(result.isPresent());
    }

    @org.junit.jupiter.api.Test
    void postCarAddsNewCar() {
        Car carToAdd = new Car("5", "Tesla Model 1");
        Car result = restApiCarController.postCar(carToAdd);
        assertEquals("Tesla Model 1", result.getName());
        assertEquals("5", result.getId());

        // Iterable<Car> resultCarsIterable = restApiCarController.getCars();
        List<Car> resultCars = restApiCarController.getCars();
//        List<Car> resultCars = new ArrayList<>();
//        resultCarsIterable.forEach(resultCars::add);
        assertEquals(5, resultCars.size());
    }

    @org.junit.jupiter.api.Test
    void putCarUpdateCarInfo() {
        Car carToAdd = new Car("1", "Tesla Model 1");
        ResponseEntity<Car> responseEntityResult = restApiCarController.putCar("1", carToAdd);
      //   assertEquals(ResponseEntity.ok().body(carToAdd), responseEntityResult);
        assertEquals(ResponseEntity.ok(carToAdd), responseEntityResult);
        assertEquals("Tesla Model 1", responseEntityResult.getBody().getName());

    }
    @org.junit.jupiter.api.Test
    void putCarAddCarInfo() {
        Car carToAdd = new Car("10", "Tesla Model 10");
        ResponseEntity<Car> responseEntityResult = restApiCarController.putCar("10", carToAdd);
        // assertEquals(ResponseEntity.ok().body(carToAdd), responseEntityResult);
        assertEquals(CREATED , responseEntityResult.getStatusCode());
        assertEquals(201 , responseEntityResult.getStatusCode().value());
        assertEquals("Tesla Model 10", responseEntityResult.getBody().getName());

    }

//    @org.junit.jupiter.api.Test
//    void testPutCarUpdateExistingCar() {
//        // Arrange
//        Car updatedCar = new Car("1", "BMW M3");
//        // Act
//        ResponseEntity<Car> response = restApiCarController.putCar("1", updatedCar);
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode(), "Статус должен быть 200 OK");
//        assertEquals("BMW M3", restApiCarController.getCars().get(0).getName(), "Название машины должно обновиться");
//    }
//
//    @org.junit.jupiter.api.Test
//    void testPutCarAddNewCar() {
//        // Arrange
//        Car car10 = new Car("10", "Tesla Model S");
//        // Act
//        ResponseEntity<Car> response = restApiCarController.putCar("10", car10);
//        // Assert
//        assertEquals(HttpStatus.CREATED, response.getStatusCode(), "Статус должен быть 201 CREATED");
//        assertTrue(restApiCarController.getCars().stream().anyMatch(car -> car.getId().equals("10")),
//                "Машина с id=10 должна быть добавлена");
//    }

    @org.junit.jupiter.api.Test
    void deleteCar() {
        //Iterable<Car> resultCarsIterable=restApiController.getCars();
        List<Car> resultCars = restApiCarController.getCars();
//        List<Car> resultCars = new ArrayList<>();
//        resultCarsIterable.forEach(resultCars::add);
        assertEquals(4, resultCars.size());

        restApiCarController.deleteCar("1");

        //  Iterable<Car> resultCarsIterableDeletedCar = restApiCarController.getCars();
        //  List <Car> resultCarsDeletedCarNoSuccess = restApiCarController.getCars();
//        resultCars=new ArrayList<>();
//        resultCarsIterableDeletedCar.forEach(resultCars::add);
        assertEquals(3, resultCars.size());

        assertEquals("Audi A8", resultCars.get(0).getName());

        restApiCarController.deleteCar("10");
        assertEquals(3, resultCars.size());

    }
}*/
