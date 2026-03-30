package Scuola.Progettini.Pokemon.Types.HeldItems;

import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Types.Item;

public class Baccacitrus extends Item implements HeldItem {
    protected final int heal = 30;
    public Baccacitrus(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }
    
    @Override
    public boolean condition(Pokemon p) {
        return p.getHP() < p.getHPmax() * 0.5;
    }

    @Override
    public void activate(Pokemon p) {
        if (condition(p)) {
            p.setHP(Math.min(p.getHP() + 30, p.getHPmax()));
            removeTo(p);
        }
    }

    @Override
    public String toString() {
        return "Baccacitrus [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}