package Scuola.Progettini.BlackJack;

public class Carta{
    private final String seme;
    private final char valore;

    public Carta(String seme, char valore) {
        this.seme = seme;
        this.valore = valore;
    }

    public String toString() {
        switch (valore) {
            case '1':
                return "Carta: Asso di " + seme;
            case '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9':
                return "Carta: " + valore + "di " + seme;
            case 'X':
                return "Carta: Dieci di " + seme;
            case 'J':
                return "Carta: Jack di " + seme;
            case 'Q':
                return "Carta: Donna di " + seme;
            case 'K':
                return "Carta: Re di " + seme;
            default:
                return "";
        }
    }

    public String getSeme() {
        return seme;
    }

    public char getValore() {
        return valore;
    }
}