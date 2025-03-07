package de.ait.javalessons.controller;

import de.ait.javalessons.model.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j//аннотация Lombok для автоматического создания логгера
@RestController//аннотация Spring, указывающая, что этот класс явл. REST-контроллером
@RequestMapping("/cars")//базовый путь для всех методов в этом контроллере
public class RestApiCarController {
    private List<Car> carList = new ArrayList<>();

    //1 этап:добавим конструктор, чтобы в лист попадало то, что мы хотим добавить
    public RestApiCarController() {
        carList.addAll(
                List.of(
                        new Car("1", "BMW M1"),
                        new Car("2", "Audi A8"),
                        new Car("3", "Kia Spartage"),
                        new Car("4", "Volvo960")
                ));
    }

    //2 этап: добавим правило, по которому к нам по сети может постучать кто-то,
    // кто хочет получить какую-то информацию (кто, сколько раз ...)
//@RequestMapping(value = "/cars", method = RequestMethod.GET)
    //т.е. в аннотации @RequestMapping мы указали, что метод должен быть GET,
    // и если ты идешь по "/cars",
// то контроллер знает, что по этому запросу надо выдать список автомобилей

    //Или можно использовать более короткую аннотацию:
    @GetMapping
    //Iterable<Car> getCars() {
    List<Car> getCars() {
        return carList;
    }

    //теперь мы хотим получить информацию только об одном автомобиле по его id
    @GetMapping("/{id}")
    Optional<Car> getCarById(@PathVariable String id) {
        for (Car car : carList) {
            if (car.getId().equals(id)) {
                log.info("Car found with id " + id);
                return Optional.of(car);
            }
        }
        log.info("Car not found with id " + id);
        return Optional.empty();
    }

    //добавление автомобиля в список
    @PostMapping
    Car postCar(@RequestBody Car car) {
        carList.add(car);
        return car;
    }

    //изменение данных автомобиля с переданным в запросе конкретным id
    @PutMapping("/{id}")
    ResponseEntity<Car> putCar(@PathVariable String id, @RequestBody Car car) {
        int index = -1;
        for (Car carToFind : carList) {
            if (carToFind.getId().equals(id)) {
                index = carList.indexOf(carToFind);
                carList.set(index, car);
            }
        }
        return (index == -1) ?
                new ResponseEntity<>(postCar(car), HttpStatus.CREATED) :
                new ResponseEntity<>(car, HttpStatus.OK);

    }

    //удаление
    @DeleteMapping("/{id}")
    void deleteCar(@PathVariable String id) {
        carList.removeIf(car -> car.getId().equals(id));
    }

}
