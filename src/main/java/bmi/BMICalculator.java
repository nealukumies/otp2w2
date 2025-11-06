package bmi;

public class BMICalculator {
    public double calculateBMI(double weightKg, double heightCm) {
        if (heightCm <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero.");
        }
        double heightM = heightCm / 100.0;
        double bmi = weightKg / (heightM * heightM);
        System.out.println("BMI calculated: " + bmi);
        return bmi;
    }
}
