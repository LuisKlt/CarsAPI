package com.example.demo;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CarController {

  private final CarRepository repository;

  CarController(CarRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/cars")
  List<Car> all() {
    return repository.findAll();
  }
  // end::get-aggregate-root[]

  @PostMapping("/cars")
  Car newEmployee(@RequestBody Car newCar) {
    return repository.save(newCar);
  }

  // Single item
  
  @GetMapping("/cars/{id}")
  Car one(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new CarNotFoundException(id));
  }

  @PutMapping("/cars/{id}")
  Car replaceCar(@RequestBody Car newCar, @PathVariable Long id) {
    
    return repository.findById(id)
      .map(cars -> {
        cars.setModel(newCar.getModel());
        cars.setPower(newCar.getPower());
        return repository.save(cars);
      })
      .orElseGet(() -> {
        newCar.setId(id);
        return repository.save(newCar);
      });
  }

  @DeleteMapping("/cars/{id}")
  void deleteCar(@PathVariable Long id) {
    repository.deleteById(id);
  }
}