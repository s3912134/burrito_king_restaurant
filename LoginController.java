package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.DashboardView;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        UserController userController = new UserController();
        if (userController.login(username, password)) {
            Stage stage = (Stage) usernameField.getScene().getWindow();
            DashboardView dashboardView = new DashboardView();
            dashboardView.render(stage);
        } else {
            messageLabel.setText("Login failed. Please try again.");
        }
    }

    public void openRegister() {
        try {
            Stage registerStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/view/RegisterView.fxml"));
            registerStage.setTitle("Register");
            registerStage.setScene(new Scene(root));
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
