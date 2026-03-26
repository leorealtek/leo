package Scuola.Progettini.GameOfLife.LangtonAnt;

public class LangtonAnt {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private final int larghezza;
    private Cella[][] mappa;
    private Direzione direzione;
    private int posizioneRiga;
    private int posizioneColonna;

    public LangtonAnt(int larghezza) {
        mappa = creaMappa(larghezza);
        posizioneRiga = larghezza / 2;
        posizioneColonna = larghezza / 2;
        mappa[posizioneRiga][posizioneColonna].ant = true;
        direzione = Direzione.SINISTRA;
        this.larghezza = larghezza;
    }

    private Cella[][] creaMappa(int larghezza){
        Cella[][] mappa = new Cella[larghezza][larghezza];
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                mappa[i][j] = new Cella();
            }
        }
        return mappa;
    }

    private void stampaMappa() {
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                if (mappa[i][j].viva) {
                    if (mappa[i][j].ant) {
                        System.out.print(ANSI_GREEN_BACKGROUND + "A" + ANSI_RESET);   
                    }
                    else {
                        System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
                    }
                } 
                else {
                    if (mappa[i][j].ant) {
                        System.out.print(ANSI_WHITE_BACKGROUND + "A" + ANSI_RESET);
                    }
                    else {
                        System.out.print(ANSI_WHITE_BACKGROUND + " " + ANSI_RESET);
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void round() {
        if (mappa[posizioneRiga][posizioneColonna].viva) {
            direzione = direzione.cambiaDirezioneAntiOrario();
        }
        else {
            direzione = direzione.cambiaDirezioneOrario();
        }

        mappa[posizioneRiga][posizioneColonna].cambiaAnt();
        mappa[posizioneRiga][posizioneColonna].cambiaStato();

        switch (direzione) {
            case SU:
                posizioneRiga--;
                break;
            case DESTRA:
                posizioneColonna++;
                break;
            case GIU:
                posizioneRiga++;
                break;
            default:
                posizioneColonna--;
                break;
        }

        spostaFormica();
    }

    private boolean spostaFormica() {
        if (posizioneRiga >= mappa.length || posizioneColonna >= mappa[0].length || posizioneRiga < 0 || posizioneColonna < 0) return false;
        mappa[posizioneRiga][posizioneColonna].ant = true;
        return true;

        /*
        try {
            mappa[posizioneRiga][posizioneColonna].ant = true;
        } catch (Exception e) {
            return false;
        }
        return true;
        */
    }
    
    public void eseguiNRound(int Nround) {
        stampaMappa();
        for (int i = 0; i < Nround; i++) {
            if (!spostaFormica()) {
                System.out.println("La formica è uscita dai bordi al round " + i);
                break;
            }
            round();
        }
        stampaMappa();

    }
    public static void main(String[] args) {
        LangtonAnt a = new LangtonAnt(100);
        a.eseguiNRound(12000);
    }
}
