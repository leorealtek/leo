package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1201;

public enum Elemento {
    FUOCO, ACQUA, ERBA, ELETTRO, NORMALE;

    public double calcolaEfficacia(Elemento difensore) {
        if (this.equals(FUOCO)) {
            if (difensore.equals(ACQUA)) return 0.5d;
            if (difensore.equals(ERBA)) return 2.0d;
        }
        else if (this.equals(ACQUA)) {
            if (difensore.equals(FUOCO)) return 2.0d;
            if (difensore.equals(ERBA)) return 0.5d;
        }
        else if (this.equals(ERBA)) {
            if (difensore.equals(FUOCO)) return 0.5d;
            if (difensore.equals(ERBA)) return 2.0d;
        }
        else if (this.equals(ELETTRO)) {
            if (difensore.equals(ACQUA)) return 2.0d;
            if (difensore.equals(ERBA)) return 0.5d;
        }
        return 1.0d;
    }
}