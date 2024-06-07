package view;

import model.FoodItem;

import java.util.List;

public class OrderView {
    public void renderOrderForm() {
        
        System.out.println("Rendering order form...");
    }

    public void displayOrderDetails(List<FoodItem> items, double totalPrice) {
       
        System.out.println("Order Details:");
        for (FoodItem item : items) {
            System.out.println(item.getName() + ": $" + item.getPrice());
        }
        System.out.println("Total Price: $" + totalPrice);
    }
}
