module com.mycompany.connect4project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;

    opens com.mycompany.connect4project to javafx.fxml;
    exports com.mycompany.connect4project;
}
