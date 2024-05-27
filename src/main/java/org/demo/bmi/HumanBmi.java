package org.demo.bmi;

import java.util.Objects;

/**
 * The class is used for Human Body Mass Index (BMI) calculation
 */
public final class HumanBmi {
	private final double weight;
	private final double height;
	private final double value;
	private Status status;

	public static HumanBmi calculate(double weight, double height) {
		return new HumanBmi(weight, height);
	}

	private HumanBmi(double weight, double height) {
		checkPositivityOf(weight, height);
		this.weight = weight;
		this.height = height;
		this.value = calculateValue();
	}

	private static void checkPositivityOf(double ...args) {
		for (double arg: args) {
			if (arg <= 0) {
				throw new IllegalArgumentException("Ensure that all provided arguments are positive");
			}
		}
	}

	private double calculateValue() {
		return weight / (height * height);
	}

	public double getValue() {
		return value;
	}

	public double getWeight() {
		return weight;
	}

	public double getHeight() {
		return height;
	}

	public HumanBmi setHeight(double height) {
		return new HumanBmi(this.weight, height);
	}

	public HumanBmi setWeight(double weight) {
		return new HumanBmi(weight, this.height);
	}

	public Status getStatus() {
		if (status == null) {
			status = identifyStatus();
		}

		return status;
	}

	private Status identifyStatus() {
		if (value >= 18.5 && value < 25) {
			return Status.NORMAL;
		}

		if (value >= 25 && value < 30) {
			return Status.OBESE;
		}

		if (value >= 30) {
			return Status.OVERWEIGHT;
		}

		return Status.UNDERWEIGHT;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		HumanBmi humanBMI = (HumanBmi) o;
		return Double.compare(weight, humanBMI.weight) == 0 && Double.compare(height, humanBMI.height) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(weight, height);
	}

	@Override
	public String toString() {
		return "HumanBMI{" +
				"weight=" + weight +
				", height=" + height +
				", value=" + value +
				", status=" + getStatus() +
				'}';
	}

	public enum Status {
		UNDERWEIGHT, NORMAL, OBESE, OVERWEIGHT;
	}
}
