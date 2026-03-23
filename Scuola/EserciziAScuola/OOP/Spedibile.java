package Scuola.EserciziAScuola.OOP;

public interface Spedibile {
    public double calcolaSpese();
    public default String corriere() {
        return "GLS";
    }
}