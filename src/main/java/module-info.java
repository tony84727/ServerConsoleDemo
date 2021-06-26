module serverconsole {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.github.tony84727.serverconsole to javafx.fxml;
    exports com.github.tony84727.serverconsole;
}