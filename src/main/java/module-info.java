module com.will {
    requires javafx.graphics;
    requires java.desktop;

    opens com.will to javafx.fxml;
    exports com.will;
}
