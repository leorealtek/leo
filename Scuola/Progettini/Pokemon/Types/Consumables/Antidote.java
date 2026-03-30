package Scuola.Progettini.Pokemon.Types.Consumables;

import Scuola.Progettini.Pokemon.Exceptions.UnsupportedActionException;
import Scuola.Progettini.Pokemon.Other.Pokemon;
import Scuola.Progettini.Pokemon.Other.Status;
import Scuola.Progettini.Pokemon.Types.Item;

public class Antidote extends Item implements Consumable{

    public Antidote(String name, String description, int quantity, int stack) {
        super(name, description, quantity, stack);
    }

    @Override
    public void use(Pokemon p) throws UnsupportedActionException {
        if (p.getStatus() != Status.Poisoned)
            throw new UnsupportedActionException("Can't use " + name + ": " + p.getName() + " is not poisoned.");
        quantity--;
        p.setStatus(Status.Normal);
    }

    public String toString() {
        return "Antidote [Name: " + name + " Description: " + description + " Quantity: " + quantity + " Stack: " + stack + "]";
    }
}