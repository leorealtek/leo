package Scuola.Progettini.Pokemon.Types.Consumables;

import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Types.Consumable;
import Scuola.Progettini.Pokemon.Types.Item;

public class HealPotion extends Item implements Consumable {
    protected int heal = 20;
    public HealPotion(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }
    
    @Override
    public void use(Pokemon p) {
        p.setHP(p.getHP() + heal);
        quantity--;
    }

    public int getHeal() {
        return heal;
    }
    
}