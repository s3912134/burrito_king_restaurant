module A2 {
    // Module declarations
    requires javafx.controls;
    requires javafx.fxml;
    
    opens view to javafx.fxml;
    opens controller to javafx.fxml;
    opens model to javafx.base;
    
    exports main;
    exports controller;
    exports view;
    exports model;
}
