package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import controller.UserController;

public class LoginView {

    public void render(Stage primaryStage) {
        primaryStage.setTitle("Login");

        GridPane grid = new GridPane();

        Label userLabel = new Label("Username:");
        grid.add(userLabel, 0, 0);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 1);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 1);

        Button btn = new Button("Login");
        grid.add(btn, 1, 2);

        Label messageLabel = new Label();
        grid.add(messageLabel, 1, 3);

        btn.setOnAction(e -> {
            UserController userController = new UserController();
            if (userController.login(userTextField.getText(), pwBox.getText())) {
                DashboardView dashboardView = new DashboardView();
                dashboardView.render(primaryStage);
            } else {
                messageLabel.setText("Login failed. Please try again.");
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
