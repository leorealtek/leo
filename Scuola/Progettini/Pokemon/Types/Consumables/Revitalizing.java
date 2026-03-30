package Scuola.Progettini.Pokemon.Types.Consumables;

import Scuola.Progettini.Pokemon.Exceptions.UnsupportedActionException;
import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Other.Status;
import Scuola.Progettini.Pokemon.Types.Item;

public class Revitalizing extends Item implements Consumable{

    public Revitalizing(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }
    
    @Override
    public void use(Pokemon p) throws UnsupportedActionException {
        if (p.getStatus() != Status.KO)
            throw new UnsupportedActionException("Can't use " + name + ": " + p.getName() + " is not KO.");
        p.setStatus(Status.Normal);
        p.setHP(p.getHPmax() / 2);
    }

    @Override
    public String toString() {
        return "Revitalizing [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}