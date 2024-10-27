module com.example.lab2_tzi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab2_tzi to javafx.fxml;
    exports com.example.lab2_tzi;
}