package bmi;

public class BMICalculator {
    public double calculateBMI(double weightKg, double heightM) {
        if (heightM <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        return weightKg / (heightM * heightM);
    }
}
