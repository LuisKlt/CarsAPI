package payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// tag::hateoas-imports[]
// end::hateoas-imports[]

@RestController
class CarController {

	private final CarRepository repository;

	CarController(CarRepository repository) {
		this.repository = repository;
	}

	// Aggregate root

	// tag::get-aggregate-root[]
	@GetMapping("/Cars")
	CollectionModel<EntityModel<Car>> all() {

		List<EntityModel<Car>> Cars = repository.findAll().stream()
				.map(Car -> EntityModel.of(Car,
						linkTo(methodOn(CarController.class).one(Car.getId())).withSelfRel(),
						linkTo(methodOn(CarController.class).all()).withRel("Cars")))
				.collect(Collectors.toList());

		return CollectionModel.of(Cars, linkTo(methodOn(CarController.class).all()).withSelfRel());
	}
	// end::get-aggregate-root[]

	@PostMapping("/Cars")
	Car newCar(@RequestBody Car newCar) {
		return repository.save(newCar);
	}

	// Single item

	// tag::get-single-item[]
	@GetMapping("/Cars/{id}")
	EntityModel<Car> one(@PathVariable Long id) {

		Car Car = repository.findById(id) //
				.orElseThrow(() -> new CarNotFoundException(id));

		return EntityModel.of(Car, //
				linkTo(methodOn(CarController.class).one(id)).withSelfRel(),
				linkTo(methodOn(CarController.class).all()).withRel("Cars"));
	}
	// end::get-single-item[]

	@PutMapping("/Cars/{id}")
	Car replaceCar(@RequestBody Car newCar, @PathVariable Long id) {

		return repository.findById(id) //
				.map(Car -> {
					Car.setMarca(newCar.getMarca());
					Car.setModelo(newCar.getModelo());
					Car.setAno(newCar.getAno());
					Car.setPotencia(newCar.getPotencia());
					Car.setAltura(newCar.getAltura());
					return repository.save(Car);
				}) //
				.orElseGet(() -> {
					return repository.save(newCar);
				});
	}

	@DeleteMapping("/Cars/{id}")
	void deleteCar(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
