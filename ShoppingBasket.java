package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private List<FoodItem> items;

    public ShoppingBasket() {
        this.items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        items.add(item);
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public void updateItemQuantity(FoodItem item, int quantity) {
        for (FoodItem fi : items) {
            if (fi.equals(item)) {
                fi.setQuantity(quantity);
            }
        }
    }

    public List<FoodItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

    public void clearBasket() {
        items.clear();
    }

    @Override
    public String toString() {
        return "ShoppingBasket{" +
                "items=" + items +
                '}';
    }
}

