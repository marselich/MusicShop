package ru.kalievmars.musicshop;

public class Order {
    private String buyerName;
    private String orderName;
    private double price;
    private int quantity;

    public String getBuyerName() {
        return buyerName;
    }

    public String getOrderName() {
        return orderName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Order(String buyerName, String orderName, double price, int quantity) {
        this.buyerName = buyerName;
        this.orderName = orderName;
        this.price = price;
        this.quantity = quantity;
    }

    public double getOrderPrice() {
        if(quantity == 0) {
            return 0;
        }
        return price / quantity;
    }

    @Override
    public String toString() {
        return "Name: " + buyerName + "\n" +
                "Order: " + orderName + "\n" +
                "Price: " + getOrderPrice() + " $\n" +
                "Quantity: " + quantity + "\n" +
                "General order: " + price + " $\n";
    }
}
