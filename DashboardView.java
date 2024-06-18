package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {

    public void render(Stage primaryStage) {
        primaryStage.setTitle("Dashboard");

        VBox vbox = new VBox();

        Label welcomeLabel = new Label("Welcome to your Dashboard");
        vbox.getChildren().add(welcomeLabel);

        Button orderButton = new Button("Place Order");
        orderButton.setOnAction(e -> {
            OrderView orderView = new OrderView();
            orderView.render(primaryStage);
        });
        vbox.getChildren().add(orderButton);

        Button viewOrdersButton = new Button("View Orders");
        vbox.getChildren().add(viewOrdersButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
