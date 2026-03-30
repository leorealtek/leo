package Scuola.Progettini.Pokemon.Types.HeldItems;

import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Types.HeldItem;
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
    public void use(Pokemon p) {
        quantity--;
        if (condition(p)) p.setHP(1);
    }
    
}