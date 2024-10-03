package payroll;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Car {

	private @Id @GeneratedValue Long id;
	private String marca;
	private String modelo;
	private int ano;
	private int potencia;
	private double altura;

	Car() {}

	Car(String marca, String modelo, int ano, int potencia, double altura) {

		this.marca = marca;
		this.modelo = modelo;
		this.ano = ano;
		this.potencia = potencia;
		this.altura = altura;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public Long getId() {
		return this.id;
	}

	public String getMarca() {
		return this.marca;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof Car))
			return false;
		Car Car = (Car) o;
		return Objects.equals(this.id, Car.id) && Objects.equals(this.marca, Car.marca)
				&& Objects.equals(this.modelo, Car.modelo) && Objects.equals(this.ano, Car.ano)
				&& Objects.equals(this.potencia, Car.potencia) && Objects.equals(this.altura, Car.altura);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.marca, this.modelo, this.ano, this.potencia, this.altura);
	}

	@Override
	public String toString() {
		return "Car{" + "id=" + this.id + ", marca='" + this.marca + '\'' + ", modelo='" + this.modelo + '\'' +
				", ano=" + this.ano + '\'' + ", potencia='" + this.potencia + '\'' + ", altura='" + this.altura + '\'' + '}';
	}
}
