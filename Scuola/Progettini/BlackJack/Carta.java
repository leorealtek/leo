package Scuola.Progettini.BlackJack;

public class Carta {
    private final String seme;
    private final char valore;

    public Carta(String seme, char valore) {
        this.seme = seme;
        this.valore = valore;
    }

    public int getPunti() {
        if (valore == '1') {
            return 11;
        }

        if (valore == 'X' || valore == 'J' || valore == 'Q' || valore == 'K') {
            return 10;
        }

        return valore - '0';
    }

    public boolean isAsso() {
        return valore == '1';
    }

    @Override
    public String toString() {
        switch (valore) {
            case '1':
                return "Asso di " + seme;
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return valore + " di " + seme;
            case 'X':
                return "Dieci di " + seme;
            case 'J':
                return "Jack di " + seme;
            case 'Q':
                return "Donna di " + seme;
            case 'K':
                return "Re di " + seme;
            default:
                return "Carta non valida";
        }
    }

    public String getSeme() {
        return seme;
    }

    public char getValore() {
        return valore;
    }
}