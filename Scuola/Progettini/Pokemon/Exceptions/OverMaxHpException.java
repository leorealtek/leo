package Scuola.Progettini.Pokemon.Exceptions;

public class OverMaxHpException extends RuntimeException {
    public OverMaxHpException(int hp, int hpMax) {
        super("HP can't exceed max HP (" + hpMax + "): " + hp);
    }
}
