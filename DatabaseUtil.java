package util;

import model.Order;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class DatabaseUtil {
    // Simulating a database with in-memory storage
    private static final Map<Integer, Order> orderDatabase = new HashMap<>();
    private static int currentOrderNumber = 1000; // Starting order number

   
    public static void saveOrder(Order order) {
        orderDatabase.put(order.getOrderNumber(), order);
    }

    
    public static void updateOrder(Order order) {
        orderDatabase.put(order.getOrderNumber(), order);
    }

   
    public static Order getOrder(int orderNumber) {
        return orderDatabase.get(orderNumber);
    }

    
    public static int generateOrderNumber() {
        return currentOrderNumber++;
    }

	public static void saveUser(User user) {
	
	}

	public static User loadUser(String username) {
		
		return null;
	}
}
