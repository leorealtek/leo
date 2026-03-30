package Scuola.Progettini.Pokemon.Exceptions;

public class NegativeHpException extends RuntimeException {
    public NegativeHpException(int hp) {
        super("HP can't be negative: " + hp);
    }
}
