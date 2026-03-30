package Scuola.Progettini.Pokemon.Types;

public abstract class Item {
    protected final String name;
    protected final String description;
    protected int quantity;
    protected final int stack;

    public Item(String name, String description, int quantity, int stack) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.stack = stack;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getStack() {
        return stack;
    }

    @Override
    public String toString() {
        return "Item [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}