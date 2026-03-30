package Scuola.Progettini.Pokemon.Types.Consumables;

import Scuola.Progettini.Pokemon.Exceptions.UnsupportedActionException;
import Scuola.Progettini.Pokemon.Other.Pokemon;

public interface Consumable {
    public void use(Pokemon p) throws UnsupportedActionException;
}