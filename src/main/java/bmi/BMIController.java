package bmi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
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
    @FXML
    private Label timeLabel;
    @FXML
    private Label errorLabel;

    private HashMap<String, String> supportedLanguages = new HashMap<>() {{
        put("en", "US");
        put("fr", "FR");
        put("ur", "PK");
        put("vi", "VN");
    }};

    public void initialize() {
        setLanguage("en", "US");
    }

    public boolean isSupportedLanguage(String language) {
        if (supportedLanguages.containsKey(language)) {
            return true;
        }
        return false;
    }

    public void setLanguage(String language, String country) {
        if (!isSupportedLanguage(language)) {
            errorLabel.setText("unsupportedLanguage");
            return;
        }
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
        if (errorLabel != null) {
            errorLabel.setText(rb.getString("errorResultLabel"));
        }
        updateTimeForLocate(language, country);
    }


    public void onCalculateClick(ActionEvent actionEvent) {
        errorLabel.setVisible(false);
        resultField.setText("");
        if (weightField.getText().isEmpty() || heightField.getText().isEmpty()) {
            errorLabel.setVisible(true);
            errorLabel.setText(rb.getString("errorResultLabel"));
            return;
        }
        try {
            double weightKg = Double.parseDouble(weightField.getText().replace(",", "."));
            double heightM = Double.parseDouble(heightField.getText().replace(",", "."));
            if (heightM <= 0 || weightKg <= 0) {
                errorLabel.setVisible(true);
                errorLabel.setText(rb.getString("errorResultLabel"));
                return;
            }
            if (heightM > 4) {
                errorLabel.setVisible(true);
                errorLabel.setText(rb.getString("errorResultLabel"));
                return;
            }
            double bmi = bmiCalculator.calculateBMI(weightKg, heightM);
            resultField.setText(String.format("%.2f", bmi));
        } catch (NumberFormatException e) {
            errorLabel.setVisible(true);
            errorLabel.setText(rb.getString("errorResultLabel"));
        }
    }

    public void onEnglishClick(ActionEvent actionEvent) {
        setLanguage("en", "US");
    }

    public void onFrenchClick(ActionEvent actionEvent) {
        setLanguage("fr", "FR");
    }

    public void onUrduClick(ActionEvent actionEvent) {
        setLanguage("ur", "PK");
    }

    public void onVietnameseClick(ActionEvent actionEvent) {
        setLanguage("vi", "VN");
    }

    public void updateTimeForLocate(String language, String country) {
        String zoneId;
        switch (country) {
            case "US":
                zoneId = "America/New_York";
                break;
            case "FR":
                zoneId = "Europe/Paris";
                break;
            case "PK":
                zoneId = "Asia/Karachi";
                break;

            case "VN":
                zoneId = "Asia/Ho_Chi_Minh";
                break;
            default:
                zoneId = "UTC";
        }
        ZonedDateTime zoneDate = ZonedDateTime.now(ZoneId.of(zoneId));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        String formattedTime = zoneDate.format(formatter);
        timeLabel.setText(formattedTime);
    }

}
