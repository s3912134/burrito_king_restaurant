package util;

import model.User;
import model.Order;
import model.FoodItem;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

public class DatabaseUtil {
    private static Connection connection;

    public static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:restaurant.db");
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTables() throws SQLException {
        Statement stmt = connection.createStatement();

        String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                "username TEXT PRIMARY KEY," +
                "password TEXT NOT NULL," +
                "first_name TEXT NOT NULL," +
                "last_name TEXT NOT NULL," +
                "is_vip BOOLEAN NOT NULL," +
                "email TEXT" +
                ")";
        stmt.execute(createUserTable);

        String createOrderTable = "CREATE TABLE IF NOT EXISTS orders (" +
                "order_number INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL," +
                "placed_time TEXT NOT NULL," +
                "collected_time TEXT," +
                "status TEXT NOT NULL," +
                "total_price REAL NOT NULL," +
                "FOREIGN KEY(username) REFERENCES users(username)" +
                ")";
        stmt.execute(createOrderTable);

        String createFoodItemTable = "CREATE TABLE IF NOT EXISTS food_items (" +
                "order_number INTEGER," +
                "item_name TEXT NOT NULL," +
                "quantity INTEGER NOT NULL," +
                "price REAL NOT NULL," +
                "FOREIGN KEY(order_number) REFERENCES orders(order_number)" +
                ")";
        stmt.execute(createFoodItemTable);

        stmt.close();
    }

    public static void saveUser(User user) {
        try {
            String query = "INSERT INTO users (username, password, first_name, last_name, is_vip, email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstName());
            pstmt.setString(4, user.getLastName());
            pstmt.setBoolean(5, user.isVIP());
            pstmt.setString(6, user.getEmail());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static User loadUser(String username) {
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String password = rs.getString("password");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                boolean isVIP = rs.getBoolean("is_vip");
                String email = rs.getString("email");
                User user = new User(username, password, firstName, lastName);
                user.setVIP(isVIP);
                user.setEmail(email);
                user.getOrders().addAll(loadUserOrders(username));
                rs.close();
                pstmt.close();
                return user;
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<Order> loadUserOrders(String username) {
        List<Order> orders = new ArrayList<>();
        try {
            String query = "SELECT * FROM orders WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int orderNumber = rs.getInt("order_number");
                String placedTime = rs.getString("placed_time");
                String collectedTime = rs.getString("collected_time");
                String status = rs.getString("status");
                @SuppressWarnings("unused")
				double totalPrice = rs.getDouble("total_price");
                Order order = new Order(placedTime);
                order.setOrderNumber(orderNumber);
                order.setCollectedTime(collectedTime);
                order.setStatus(status);
                order.getTotalPrice();
                order.getFoodItems().addAll(loadFoodItems(orderNumber));
                orders.add(order);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private static List<FoodItem> loadFoodItems(int orderNumber) {
        List<FoodItem> foodItems = new ArrayList<>();
        try {
            String query = "SELECT * FROM food_items WHERE order_number = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, orderNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String itemName = rs.getString("item_name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                FoodItem item = new FoodItem(itemName, quantity, price);
                foodItems.add(item);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodItems;
    }

    public static void updateUser(User user) {
        try {
            String query = "UPDATE users SET password = ?, first_name = ?, last_name = ?, is_vip = ?, email = ? WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setBoolean(4, user.isVIP());
            pstmt.setString(5, user.getEmail());
            pstmt.setString(6, user.getUsername());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveOrder(User user, Order order) {
        try {
            String query = "INSERT INTO orders (username, placed_time, status, total_price) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, order.getPlacedTime());
            pstmt.setString(3, order.getStatus());
            pstmt.setDouble(4, order.getTotalPrice());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int orderNumber = rs.getInt(1);
                order.setOrderNumber(orderNumber);
                saveFoodItems(orderNumber, order.getFoodItems());
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void saveFoodItems(int orderNumber, List<FoodItem> foodItems) {
        try {
            String query = "INSERT INTO food_items (order_number, item_name, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            for (FoodItem item : foodItems) {
                pstmt.setInt(1, orderNumber);
                pstmt.setString(2, item.getName());
                pstmt.setInt(3, item.getQuantity());
                pstmt.setDouble(4, item.getPrice());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateOrder(User user, Order order) {
        try {
            String query = "UPDATE orders SET collected_time = ?, status = ?, total_price = ? WHERE order_number = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, order.getCollectedTime());
            pstmt.setString(2, order.getStatus());
            pstmt.setDouble(3, order.getTotalPrice());
            pstmt.setInt(4, order.getOrderNumber());
            pstmt.executeUpdate();
            pstmt.close();

            // Delete old food items
            deleteFoodItems(order.getOrderNumber());
            // Save updated food items
            saveFoodItems(order.getOrderNumber(), order.getFoodItems());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void deleteFoodItems(int orderNumber) {
        try {
            String query = "DELETE FROM food_items WHERE order_number = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, orderNumber);
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void exportOrdersToFile(User user, List<Order> ordersToExport, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Order Number,Placed Time,Collected Time,Status,Total Price\n");
            for (Order order : ordersToExport) {
                writer.append(String.format("%d,%s,%s,%s,%.2f\n",
                        order.getOrderNumber(),
                        order.getPlacedTime(),
                        order.getCollectedTime() != null ? order.getCollectedTime() : "",
                        order.getStatus(),
                        order.getTotalPrice()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
