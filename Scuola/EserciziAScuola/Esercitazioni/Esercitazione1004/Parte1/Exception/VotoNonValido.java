package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte1.Exception;

public class VotoNonValido extends RuntimeException {

    public VotoNonValido() {
        super("Voto non valido.");
    }

    public VotoNonValido(String message) {
        super(message);
    }
}