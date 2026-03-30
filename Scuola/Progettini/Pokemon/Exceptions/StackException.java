package Scuola.Progettini.Pokemon.Exceptions;

import Scuola.Progettini.Pokemon.Types.Item;

public class StackException extends Exception {
    public StackException(Item i) {
        super("Stack is full, can't put " + i + " in stack.");
    }
}   