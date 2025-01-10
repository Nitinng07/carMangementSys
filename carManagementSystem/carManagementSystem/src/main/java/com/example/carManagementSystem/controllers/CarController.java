package com.example.carManagementSystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carManagementSystem.models.Car;
import com.example.carManagementSystem.repositories.CarRepository;
import com.example.carManagementSystem.services.CarService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarService carService ;

    private final CarRepository carRepository ;

    public CarController(CarRepository carRepository){
        this.carRepository = carRepository ;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carRepository.findAll() ;
    }
    
    @PostMapping
    public Car addCar(@RequestBody Car car) {        
        return carRepository.save(car) ;
    }

    @GetMapping("/filterByName")
    public List<Car> getMethodName(@RequestParam String name) {
        return carService.getCarsByName(name);
    }
    
    @GetMapping("/filterByModel")
    public List<Car> getCarsByModel(@RequestParam String model) {
        return carService.getCarsByModel(model);
    }

    @GetMapping("/filterByYear")
    public List<Car> getCarsByYear(@RequestParam int year) {
        return carService.getCarsByYear(year);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(
            @RequestParam(name = "searchTerm") String searchTerm,
            Pageable pageable) {
                Page<Car> cars = carService.searchCars(searchTerm, pageable);
                return new ResponseEntity<>(cars.getContent(), HttpStatus.OK);
            }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carService.updateCar(id, updatedCar);
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return "Car with ID " + id + " has been deleted.";
    }
}

