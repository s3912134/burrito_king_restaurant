package controller;

import model.FoodItem;
import model.Order;
import model.User;
import util.DatabaseUtil;

import java.util.List;

public class OrderController {
    public void placeOrder(User user, List<FoodItem> items, String placedTime) {
        Order order = new Order(placedTime);
        for (FoodItem item : items) {
            order.addFoodItem(item);
        }
        order.calculateTotalPrice();
        user.addOrder(order);
        DatabaseUtil.saveOrder(user, order);
    }

    public void collectOrder(User user, int orderNumber, String collectedTime) {
        Order order = user.getOrder(orderNumber);
        if (order != null && "placed".equals(order.getStatus())) {
            order.setCollectedTime(collectedTime);
            order.setStatus("collected");
            user.updateOrder(order);
            DatabaseUtil.saveOrder(user, order);
        }
    }

    public void cancelOrder(User user, int orderNumber) {
        Order order = user.getOrder(orderNumber);
        if (order != null && "placed".equals(order.getStatus())) {
            order.setStatus("cancelled");
            user.updateOrder(order);
            DatabaseUtil.saveOrder(user, order);
        }
    }

    public void exportOrders(User user, List<Order> ordersToExport, String fileName) {
        DatabaseUtil.exportOrdersToFile(user, ordersToExport, fileName);
    }
}
