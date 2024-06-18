package controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Order;
import model.User;
import model.FoodItem;
import util.DatabaseUtil;

import java.util.List;

public class OrderController {

    @FXML
    private Stage stage;

    @FXML
    public void handleSubmitOrder() {
        // Logic to handle submitting an order
        // This can involve interacting with the model and updating the view
    }

    public void placeOrder(User user, List<FoodItem> items, String placedTime) {
        // Create a new order
        Order order = new Order(DatabaseUtil.generateOrderNumber(), items, placedTime, "Placed");

        // Calculate the total price
        order.calculateTotalPrice();

        // Add the order to the user's order list
        user.addOrder(order);

        // Save the order to the database
        DatabaseUtil.saveOrder(order);
    }

    public void collectOrder(User user, int orderNumber, String collectedTime) {
        // Find the order by order number
        Order order = user.getOrders().stream()
                .filter(o -> o.getOrderNumber() == orderNumber)
                .findFirst()
                .orElse(null);

        if (order != null) {
            // Update order status and collected time
            order.setStatus("Collected");
            order.setCollectedTime(collectedTime);

            // Save the updated order to the database
            DatabaseUtil.updateOrder(order);
        }
    }

    public void cancelOrder(User user, int orderNumber) {
        // Find the order by order number
        Order order = user.getOrders().stream()
                .filter(o -> o.getOrderNumber() == orderNumber)
                .findFirst()
                .orElse(null);

        if (order != null) {
            // Update order status
            order.setStatus("Cancelled");

            // Save the updated order to the database
            DatabaseUtil.updateOrder(order);
        }
    }

    public void exportOrders(User user, List<Order> ordersToExport, String fileName) {
        // Logic to export the orders to a file (e.g., CSV, PDF)
        // This can involve using a utility class for file operations
        // Example:
        // FileUtil.exportOrdersToFile(ordersToExport, fileName);
    }
}
