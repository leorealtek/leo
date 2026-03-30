package Scuola.Progettini.Pokemon.Types.HeldItems;

import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Types.Item;

public class Focalnastro extends Item implements HeldItem {

    public Focalnastro(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }
    
    @Override
    public boolean condition(Pokemon p) {
        return p.getHP() < 0;
    }

    @Override
    public void activate(Pokemon p) {
        p.setHP(1);
        quantity--;
        removeTo(p);
    }

    @Override
    public String toString() {
        return "Focalnastro [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}