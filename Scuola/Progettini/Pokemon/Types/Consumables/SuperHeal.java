package Scuola.Progettini.Pokemon.Types.Consumables;

public class SuperHeal extends HealPotion {
    public SuperHeal(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
        heal = 50;
    }

    @Override
    public String toString() {
        return "Super Heal Potion [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}