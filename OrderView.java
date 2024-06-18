package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrderView {

    public void render(Stage primaryStage) {
        primaryStage.setTitle("Place Order");

        VBox vbox = new VBox();

        Label orderLabel = new Label("Order Details");
        vbox.getChildren().add(orderLabel);


        Button submitButton = new Button("Submit Order");
        vbox.getChildren().add(submitButton);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
