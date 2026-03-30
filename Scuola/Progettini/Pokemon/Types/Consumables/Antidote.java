package Scuola.Progettini.Pokemon.Types.Consumables;

import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Other.Status;
import Scuola.Progettini.Pokemon.Types.Consumable;
import Scuola.Progettini.Pokemon.Types.Item;

public class Antidote extends Item implements Consumable{

    public Antidote(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }

    @Override
    public void use(Pokemon p) {
        quantity--;
        p.setStatus(Status.Normal);
    }
    
}
