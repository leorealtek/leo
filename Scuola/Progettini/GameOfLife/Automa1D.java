package Scuola.Progettini.GameOfLife;

public class Automa1D {
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_RESET = "\u001B[0m";
    private String striscia;
    private final int regola;

    public Automa1D(int larghezza, int regola) {
        this.striscia = creaStriscia(larghezza);
        this.regola = regola;
    }

    private String creaStriscia(int larghezza) {
        String striscia = "";
        for (int i = 0; i < larghezza;i++) {
            if (i == larghezza / 2) {
                striscia += "1";
            }
            else {
                striscia += 0;
            }
        }
        return striscia;
    }

    private String calcolaStato(char sinistra, char centro, char destra) {
        String[] abbinamenti = new String[8];
        String binario = Integer.toBinaryString(regola);
        while (binario.length() < 8) binario = "0" + binario;

        for (int i = 0; i < abbinamenti.length; i++) {
            abbinamenti[7 - i] = String.valueOf(binario.charAt(i));
        }
        String stato = "" + sinistra + centro + destra;
        return abbinamenti[Integer.parseInt(stato, 2)];
    }

    public void nextStato() {
        striscia = "0" + striscia + "0";
        String nuovoStato = "";
        for (int i = 1; i < striscia.length() - 1; i++) {
            nuovoStato += calcolaStato(striscia.charAt(i - 1), striscia.charAt(i), striscia.charAt(i + 1));
        }
        striscia = nuovoStato;
    }

    public void stampaStriscia() {
        for (int i = 0; i < striscia.length(); i++) {
            if (striscia.charAt(i) == '0') System.out.print(ANSI_PURPLE_BACKGROUND + " " + ANSI_RESET);
            else System.out.print(ANSI_WHITE_BACKGROUND + " " + ANSI_RESET);
        }
        System.out.println();
    }
    
    public void eseguiStati(int quanti) {
        for (int i = 0; i < quanti; i++) {
            stampaStriscia();
            nextStato();
        }
        stampaStriscia();
    }

    public static void main(String[] args) {
        Automa1D a = new Automa1D(100, 90);
        a.eseguiStati(100);
    }
}