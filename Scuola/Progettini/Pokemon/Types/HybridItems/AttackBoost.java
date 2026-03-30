package Scuola.Progettini.Pokemon.Types.HybridItems;

import Scuola.Progettini.Pokemon.Exceptions.UnsupportedActionException;
import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Types.Item;

public class AttackBoost extends Item implements HybridItem {

    public AttackBoost(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }

    @Override
    public boolean condition(Pokemon p) {
        return p.getHP() < p.getHPmax() * 0.25;
    }

    @Override
    public void use(Pokemon p) throws UnsupportedActionException {
        quantity--;
        p.setAttack(p.getAttack() + 10);
    }

    @Override
    public void activate(Pokemon p) {
        if (condition(p))
            p.setAttack(p.getAttack() + 15);
    }

    @Override
    public String toString() {
        return "Attack Boost [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}