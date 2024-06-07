package model;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int orderCounter = 0;
    private int orderNumber;
    private List<FoodItem> foodItems;
    private double totalPrice;
    private String status; // placed, collected, cancelled
    private String placedTime;
    private String collectedTime;

    public Order(String placedTime) {
        this.orderNumber = ++orderCounter;
        this.foodItems = new ArrayList<>();
        this.totalPrice = 0.0;
        this.status = "placed";
        this.placedTime = placedTime;
        this.collectedTime = null;
    }

    public void addFoodItem(FoodItem item) {
        foodItems.add(item);
        totalPrice += item.getPrice() * item.getQuantity();
    }

    public void calculateTotalPrice() {
        totalPrice = foodItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    

    public String getStatus() {
        return status;
    }

    public String getPlacedTime() {
        return placedTime;
    }

    public String getCollectedTime() {
        return collectedTime;
    }
    
    public void setOrderNumber(int orderNumber2) {
    	return;
    }
    
    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCollectedTime(String collectedTime) {
        this.collectedTime = collectedTime;
    }

    public boolean canBeCollected(String collectTime) {
        // Implement logic to check if the order can be collected based on the preparation time
        return true; // Placeholder
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", foodItems=" + foodItems +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", placedTime='" + placedTime + '\'' +
                ", collectedTime='" + collectedTime + '\'' +
                '}';
    }



	
	}

