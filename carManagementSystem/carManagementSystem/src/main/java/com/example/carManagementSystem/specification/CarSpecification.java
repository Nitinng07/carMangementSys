package com.example.carManagementSystem.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.carManagementSystem.models.Car;

public class CarSpecification {
    public static Specification<Car> searchByTerm(String searchTerm) {
        return (root, query, builder) -> builder.or(
            builder.like(builder.lower(root.get("carName")), "%" + searchTerm.toLowerCase() + "%"),
            builder.like(builder.lower(root.get("model")), "%" + searchTerm.toLowerCase() + "%"),
            builder.like(builder.lower(root.get("fuelType")), "%" + searchTerm.toLowerCase() + "%"),
            builder.like(builder.lower(root.get("color")), "%" + searchTerm.toLowerCase() + "%")
        );
    }
}
