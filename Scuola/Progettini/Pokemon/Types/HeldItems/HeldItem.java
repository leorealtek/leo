package Scuola.Progettini.Pokemon.Types.HeldItems;

import Scuola.Progettini.Pokemon.Other.Pokemon;

public interface HeldItem {
    public default void giveTo(Pokemon p) {
        p.setHeldItem(this);
    }

    public default void removeTo(Pokemon p) {
        p.setHeldItem(null);
    }

    public boolean condition(Pokemon p);
    public void activate(Pokemon p);
}