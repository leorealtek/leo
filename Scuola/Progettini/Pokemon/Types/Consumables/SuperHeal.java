package Scuola.Progettini.Pokemon.Types.Consumables;

public class SuperHeal extends HealPotion {
    public SuperHeal(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
        heal = 50;
    }
}