package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private List<FoodItem> items;

    public ShoppingBasket() {
        this.items = new ArrayList<>();
    }

    // Getters and Setters
    public List<FoodItem> getItems() {
        return items;
    }

    public void setItems(List<FoodItem> items) {
        this.items = items;
    }

    public void addItem(FoodItem item) {
        this.items.add(item);
    }

    public void removeItem(FoodItem item) {
        this.items.remove(item);
    }

    public void updateItemQuantity(FoodItem item, int quantity) {
        for (FoodItem foodItem : items) {
            if (foodItem.equals(item)) {
                foodItem.setQuantity(quantity);
                break;
            }
        }
    }
}
