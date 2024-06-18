package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private Label messageLabel;

    public void handleRegister() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();

        UserController userController = new UserController();
        userController.register(username, password, firstName, lastName, email);

        messageLabel.setText("Registration successful! Please login.");

        // Optionally, close the registration window and return to login
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();
    }
}
