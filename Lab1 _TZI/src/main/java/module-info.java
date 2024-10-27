module com.example.tzi {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tzi to javafx.fxml;
    exports com.example.tzi;
}