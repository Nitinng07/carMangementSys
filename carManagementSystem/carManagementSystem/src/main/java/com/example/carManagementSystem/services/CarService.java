package com.example.carManagementSystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.carManagementSystem.models.Car;
import com.example.carManagementSystem.repositories.CarRepository;
import com.example.carManagementSystem.specification.CarSpecification;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository ;

    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll() ;
    }

    public List<Car> getCarsByName(String carName) {
        return carRepository.findByCarName(carName) ;
    }

    public List<Car> getCarsByModel(String model){
        return carRepository.findByModel(model) ;
    }

    public List<Car> getCarsByYear(int year) {
        return carRepository.findByYear(year);
    }

    public Page<Car> searchCars(String searchTerm, Pageable pageable) {
        Specification<Car> spec = CarSpecification.searchByTerm(searchTerm);
        return carRepository.findAll(spec, pageable);
    }
    
    public Car updateCar(Long id,Car updatedCar){
        Optional<Car> existingCar = carRepository.findById(id) ;
        if(existingCar.isPresent()){
            Car car = existingCar.get() ;
            car.setCarName(updatedCar.getCarName());
            car.setModel(updatedCar.getModel());
            car.setYear(updatedCar.getYear());
            car.setPrice(updatedCar.getPrice());
            car.setColor(updatedCar.getColor());
            car.setFuelType(updatedCar.getFuelType());

            return carRepository.save(car);
        }else {
            throw new RuntimeException("Car not found with ID" + id) ;
        }
     }

     public void deleteCar(Long id){
        carRepository.deleteById(id) ;
        }
}
