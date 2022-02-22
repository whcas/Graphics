module com.will {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.will to javafx.fxml;
    exports com.will;
}
