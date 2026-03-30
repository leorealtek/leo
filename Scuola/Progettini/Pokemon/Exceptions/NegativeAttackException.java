package Scuola.Progettini.Pokemon.Exceptions;

public class NegativeAttackException extends RuntimeException{
    public NegativeAttackException(int attack) {
        super("Attack can't be negative: " + attack);
    }
}
