module com.mycompany.game777 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.game777 to javafx.fxml;
    exports com.mycompany.game777;
}
