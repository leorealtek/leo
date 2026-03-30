package Scuola.Progettini.Pokemon.Exceptions;

import Scuola.Progettini.Pokemon.Types.Item;

public class FullStackException extends Exception {
    public FullStackException(Item i) {
        super("Stack is full, can't put " + i + " in stack.");
    }
}