package Scuola.EserciziAScuola.Ricerca;

import java.util.Random;

public class EggGame {
    private final int pianoCritico;
    private final int numeroPiani;
    private int contatoreNlanci;
    private boolean soluzioneRivelata;

    public EggGame(int numeroPiani) {
        this.numeroPiani = numeroPiani;
        Random random = new Random();
        this.pianoCritico = random.nextInt(numeroPiani + 1);
        this.contatoreNlanci = 0;
        this.soluzioneRivelata = false;
    }

    public String lanciaUovo(int piano) {
        if(piano > numeroPiani || piano < 0 || soluzioneRivelata)
            return "ERRORE";
        contatoreNlanci++;

        if (piano <= pianoCritico)
            return "SALVO";
        return "SPLAT";
    }

    public int getContatoreNlanci() {
        return contatoreNlanci;
    }

    public void resetContatore() {
        contatoreNlanci = 0;
    }   

    public int getPianoCritico() {
        soluzioneRivelata = true;
        return pianoCritico;
    }
}