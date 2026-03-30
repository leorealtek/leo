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
}