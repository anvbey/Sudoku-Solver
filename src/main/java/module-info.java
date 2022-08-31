module com.example.javasudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javasudoku to javafx.fxml;
    exports com.example.javasudoku;
}