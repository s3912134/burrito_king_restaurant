package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isVIP;
    private String email;
    private List<Order> orders;
    private ShoppingBasket basket;

    // Constructor
    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isVIP = false;
        this.orders = new ArrayList<>();
        this.basket = new ShoppingBasket();
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order getOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }

    public void updateOrder(Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderNumber() == updatedOrder.getOrderNumber()) {
                orders.set(i, updatedOrder);
                return;
            }
        }
    }

    public ShoppingBasket getBasket() {
        return basket;
    }

    public void setBasket(ShoppingBasket basket) {
        this.basket = basket;
    }

    public void editProfile(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (password != null && !password.isEmpty()) {
            this.password = password;
        }
    }

    public void upgradeToVIP(String email) {
        this.isVIP = true;
        this.email = email;
    }
}
