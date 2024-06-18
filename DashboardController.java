package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.Window;
import view.OrderView;

public class DashboardController {

    @FXML
    public void handlePlaceOrder() {
        Window usernameField = null;
		@SuppressWarnings("null")
		Stage stage = (Stage) usernameField.getScene().getWindow();
        OrderView orderView = new OrderView();
        orderView.render(stage);
    }
}
