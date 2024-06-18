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

    public User(String username, String password, String firstName, String lastName, boolean isVIP, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isVIP = isVIP;
        this.email = email;
        this.orders = new ArrayList<>();
        this.basket = new ShoppingBasket();
    }

    public User(String username2, String password2, String firstName2, String lastName2, String email2) {
		
	}

	// Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public ShoppingBasket getBasket() {
        return basket;
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void editProfile(String firstName, String lastName, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setPassword(password);
    }

    public void upgradeToVIP(String email) {
        setVIP(true);
        setEmail(email);
    }
}
