package bmi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class BMIController {
    private BMICalculator bmiCalculator = new BMICalculator();
    private ResourceBundle rb;

    @FXML
    private Label welcomeText;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Label weightLabel;
    @FXML
    private TextField weightField;
    @FXML
    private Label heightLabel;
    @FXML
    private TextField heightField;
    @FXML
    private Label resultLabel;
    @FXML
    private TextField resultField;
    @FXML
    private Button calculateButton;

    public void initialize() {
        setLanguage("en", "US");
    }

    public void setLanguage(String language, String country) {
        Locale locale = new Locale(language, country);
        rb = ResourceBundle.getBundle("MessagesBundle", locale);
        welcomeText.setText(rb.getString("welcomeText"));
        button1.setText(rb.getString("button1"));
        button2.setText(rb.getString("button2"));
        button3.setText(rb.getString("button3"));
        button4.setText(rb.getString("button4"));
        weightLabel.setText(rb.getString("weightLabel"));
        heightLabel.setText(rb.getString("heightLabel"));
        resultLabel.setText(rb.getString("resultLabel"));
        calculateButton.setText(rb.getString("calculateButton"));
    }


    public void onCalculateClick(ActionEvent actionEvent) {
        double weightKg = Double.parseDouble(weightField.getText());
        double heightM = Double.parseDouble(heightField.getText());
        if (heightM <= 0) {
            resultField.setText(rb.getString("errorResultLabel"));
            return;
        }
        double bmi = bmiCalculator.calculateBMI(weightKg, heightM);
        resultField.setText(String.format("%.2f", bmi));
    }

    public void onEnglishClick(ActionEvent actionEvent) {
        setLanguage("en", "US");
    }

    public void onFinnishClick(ActionEvent actionEvent) {
        setLanguage("fi", "FI");
    }

    public void onJapanClick(ActionEvent actionEvent) {
        setLanguage("ja", "JP");
    }

    public void onSwedishClick(ActionEvent actionEvent) {
        setLanguage("sv", "SE");
    }
}
