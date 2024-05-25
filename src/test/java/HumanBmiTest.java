import org.demo.bmi.HumanBmi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class HumanBmiTest {

	@Test
	public void calculateValue() {
		assertEquals(22.86, HumanBmi.calculate(70, 1.75).getValue(), 0.01);
	}

	@ParameterizedTest
	@MethodSource("provideParamsForStatusTest")
	public void calculateStatus(double weight, double height, HumanBmi.Status expectedStatus) {
		assertEquals(expectedStatus, HumanBmi.calculate(weight, height).getStatus());
	}

	private static Stream<Arguments> provideParamsForStatusTest() {
		return Stream.of(
				Arguments.of(70.0, 1.75, HumanBmi.Status.NORMAL),
				Arguments.of(90.0, 1.75, HumanBmi.Status.OBESE),
				Arguments.of(100.0, 1.75, HumanBmi.Status.OVERWEIGHT),
				Arguments.of(50.0, 1.75, HumanBmi.Status.UNDERWEIGHT));
	}

	@Test
	public void setHeight() {
		var bmi = HumanBmi.calculate(70, 1.75);
		var bmiWithChangedHeight = bmi.setHeight(60);

        assertNotSame(bmi, bmiWithChangedHeight);
		assertEquals(60, bmiWithChangedHeight.getHeight());
	}

	@Test
	public void setWeight() {
		var bmi = HumanBmi.calculate(70, 1.75);
		var bmiWithChangedWeight = bmi.setWeight(60);

		assertNotSame(bmi, bmiWithChangedWeight);
		assertEquals(60, bmiWithChangedWeight.getWeight());
	}

	@ParameterizedTest
	@MethodSource("provideInvalidInputs")
	public void shouldThrowException_WhenInputValuesAreNotPositive() {
		assertThrows(IllegalArgumentException.class, () ->
				HumanBmi.calculate(0, 1.75));
	}

	private static Stream<Arguments> provideInvalidInputs() {
		return Stream.of(
				Arguments.of(0, 1.75),
				Arguments.of(90.0, 0));
	}
}
