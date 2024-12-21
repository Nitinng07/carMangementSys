package com.example.carManagementSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.carManagementSystem.models.Car;
import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car>{
    
    List<Car> findByCarName(String carName) ;
    List<Car> findByModel(String model);
    List<Car> findByYear(int year) ;
    
}
