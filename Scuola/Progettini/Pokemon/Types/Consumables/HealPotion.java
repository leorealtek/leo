package Scuola.Progettini.Pokemon.Types.Consumables;

import Scuola.Progettini.Pokemon.Exceptions.UnsupportedActionException;
import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Types.Item;

public class HealPotion extends Item implements Consumable {
    protected int heal = 20;
    public HealPotion(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }
    
    @Override
    public void use(Pokemon p) throws UnsupportedActionException {
        if (p.getHP() == p.getHPmax())
            throw new UnsupportedActionException("Can't use " + name + ": " + p.getName() + " already has full HP.");
        p.setHP(Math.min(p.getHP() + heal, p.getHPmax()));
    }

    public int getHeal() {
        return heal;
    }

    @Override
    public String toString() {
        return "Heal Potion [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}