package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte1.Exception;

public class FormatoVotoStringaErrato extends RuntimeException {

    public FormatoVotoStringaErrato() {
        super("Formato invalido");
    }

    public FormatoVotoStringaErrato(String message) {
        super(message);
    }
    
}
