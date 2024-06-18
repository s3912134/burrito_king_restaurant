package model;

import java.util.List;

public class Order {
    private int orderNumber;
    private List<FoodItem> foodItems;
    private double totalPrice;
    private String status;
    private String placedTime;
    private String collectedTime;

    public Order(int orderNumber, List<FoodItem> foodItems, String placedTime, String status) {
        this.orderNumber = orderNumber;
        this.foodItems = foodItems;
        this.placedTime = placedTime;
        this.status = status;
        this.totalPrice = calculateTotalPrice();
    }

    // Getters and Setters
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlacedTime() {
        return placedTime;
    }

    public void setPlacedTime(String placedTime) {
        this.placedTime = placedTime;
    }

    public String getCollectedTime() {
        return collectedTime;
    }

    public void setCollectedTime(String collectedTime) {
        this.collectedTime = collectedTime;
    }

    public void addFoodItem(FoodItem item) {
        this.foodItems.add(item);
        this.totalPrice = calculateTotalPrice();
    }

    public double calculateTotalPrice() {
        return foodItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }
}
