package main;

import controller.UserController;
import controller.OrderController;
import model.Order;
import model.User;
import view.LoginView;
import view.DashboardView;
import view.OrderView;
import util.DatabaseUtil;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Connect to the database
            DatabaseUtil.connect();

            // Initialize views
            LoginView loginView = new LoginView();
            DashboardView dashboardView = new DashboardView();
            OrderView orderView = new OrderView();

            // Initialize controllers
            UserController userController = new UserController();
            OrderController orderController = new OrderController();

            // Application workflow example
            loginView.renderLoginForm();

            // Simulate user login
            String username = "testUser";
            String password = "password123";
            boolean isLoggedIn = userController.login(username, password);

            if (isLoggedIn) {
                User user = DatabaseUtil.loadUser(username);
                dashboardView.displayUserInfo(user);
                dashboardView.displayActiveOrders(user.getOrders());

                // Simulate placing an order
                orderView.renderOrderForm();
                // Add items to the shopping basket and place the order
                if (user.getBasket().getItems().size() > 0) {
                    orderController.placeOrder(user, user.getBasket().getItems(), "2024-06-01 12:00");

                    // Display all orders
                    dashboardView.displayActiveOrders(user.getOrders());

                    // Simulate collecting an order
                    int orderNumber = 1; // Example order number
                    orderController.collectOrder(user, orderNumber, "2024-06-01 13:00");

                    // Simulate exporting orders
                    List<Order> ordersToExport = user.getOrders();
                    if (!ordersToExport.isEmpty()) {
                        orderController.exportOrders(user, ordersToExport, "orders.csv");
                    }
                } else {
                    System.out.println("No items in the shopping basket. Order not placed.");
                }

            } else {
                loginView.showErrorMessage("Invalid username or password.");
            }

        } finally {
            // Disconnect from the database
            DatabaseUtil.disconnect();
        }
    }
}
