package com.example.demo;

import java.util.Objects;

 import jakarta.persistence.Entity; // for Spring Boot 3
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Car {

  private @Id @GeneratedValue Long id;
  private String model;
  private String power;

  Car() {}

  Car(String model, String power) {

    this.model = model;
    this.power = power;
  }

  public Long getId() {
    return this.id;
  }

  public String getModel() {
    return this.model;
  }

  public String getPower() {
    return this.power;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public void setPower(String power) {
    this.power = power;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o)
      return true;
    if (!(o instanceof Car))
      return false;
      Car car = (Car) o;
    return Objects.equals(this.id, car.id) && Objects.equals(this.model, car.model)
        && Objects.equals(this.power, car.power);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.model, this.power);
  }

  @Override
  public String toString() {
    return "Car{" + "id=" + this.id + ", model='" + this.model + '\'' + ", power='" + this.power + '\'' + '}';
  }
}