package view;

import model.User;
import model.Order;

import java.util.List;

public class DashboardView {
    public void displayUserInfo(User user) {
        // Display user information
        System.out.println("User: " + user.getFirstName() + " " + user.getLastName());
    }

    public void displayActiveOrders(List<Order> orders) {
        // Display active orders
        System.out.println("Active Orders:");
        for (Order order : orders) {
            if ("placed".equals(order.getStatus())) {
                System.out.println("Order #" + order.getOrderNumber() + ": " + order.getTotalPrice() + ", " + order.getStatus());
            }
        }
    }
}
