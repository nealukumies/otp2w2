package bmi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BMIController {
    private BMICalculator bmiCalculator = new BMICalculator();

    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField resultField;

    public void onCalculateClick(ActionEvent actionEvent) {
        double weightKg = Double.parseDouble(weightField.getText());
        double heightM = Double.parseDouble(heightField.getText());
        if (heightM <= 0) {
            resultField.setText("Height must be greater than zero.");
            return;
        }
        double bmi = bmiCalculator.calculateBMI(weightKg, heightM);
        resultField.setText(String.format("Your BMI is: %.2f", bmi));
    }

}
