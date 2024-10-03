package payroll;

class CarNotFoundException extends RuntimeException {

	CarNotFoundException(Long id) {
		super("Could not find Car " + id);
	}
}
