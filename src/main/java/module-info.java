module com.edusync.otp2w2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens bmi to javafx.fxml, javafx.graphics;
    exports bmi;
}