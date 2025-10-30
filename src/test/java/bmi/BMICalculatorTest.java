package bmi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BMICalculatorTest {
    private BMICalculator bmiCalculator;

    @BeforeEach
    void setUp() {
        bmiCalculator = new BMICalculator();
    }

    @Test
    void calculateBMITest() {
        double weight = 70;
        double height = 1.75;
        double expectedBMI = 22.86; // Expected BMI value rounded to two decimal places
        double actualBMI = bmiCalculator.calculateBMI(weight, height);
        assertEquals(expectedBMI, Math.round(actualBMI * 100.0) / 100.0);
    }

    @Test
    void calculateBMIWithZeroHeightThrowsException() {
        double weight = 70;
        double height = 0;
        assertThrows(IllegalArgumentException.class, () -> {
            bmiCalculator.calculateBMI(weight, height);
        });
    }
}