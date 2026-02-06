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
        mappa[posizioneRiga][posizioneColonna] = Cella.antMorta;
        direzione = Direzione.SINISTRA;
        this.larghezza = larghezza;
    }

    private Cella[][] creaMappa(int larghezza){
        Cella[][] mappa = new Cella[larghezza][larghezza];
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                mappa[i][j] = Cella.morta;
            }
        }
        return mappa;
    }

    public void stampaMappa() {
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                switch (mappa[i][j]) {
                    case viva -> System.out.print(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
                    case antMorta -> System.out.print(ANSI_WHITE_BACKGROUND + "A" + ANSI_RESET);
                    case antViva -> System.out.print(ANSI_RED_BACKGROUND + "A" + ANSI_RESET);
                    default -> System.out.print(ANSI_WHITE_BACKGROUND + " " + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void round() {
        switch (direzione) {
            case SU:
                if (mappa[posizioneRiga + 1][posizioneColonna].equals(Cella.viva)) {
                    direzione = direzione.cambiaDirezioneAntiOrario();
                }
                else {
                    direzione = direzione.cambiaDirezioneOrario();
                }
                break;
            case DESTRA:
                if (mappa[posizioneRiga][posizioneColonna + 1].equals(Cella.viva)) {
                    direzione = direzione.cambiaDirezioneAntiOrario();
                }
                else {
                    direzione = direzione.cambiaDirezioneOrario();
                }
            case GIU:
                if (mappa[posizioneRiga - 1][posizioneColonna].equals(Cella.viva)) {
                    direzione = direzione.cambiaDirezioneAntiOrario();
                }
                else {
                    direzione = direzione.cambiaDirezioneOrario();
                }
            default:
                if (mappa[posizioneRiga][posizioneColonna - 1].equals(Cella.viva)) {
                    direzione = direzione.cambiaDirezioneAntiOrario();
                }
                else {
                    direzione = direzione.cambiaDirezioneOrario();
                }
        }

        mappa[posizioneRiga][posizioneColonna] = mappa[posizioneRiga][posizioneColonna].cambiaCella();

        switch (direzione) {
            case SU:
                mappa[++posizioneRiga][posizioneColonna] = Cella.antViva;
                break;
            case DESTRA:
                mappa[posizioneRiga][++posizioneColonna] = Cella.antViva;
                break;
            case GIU:
                mappa[--posizioneRiga][posizioneColonna] = Cella.antViva;
                break;
            default:
                mappa[posizioneRiga][--posizioneColonna] = Cella.antViva;
                break;
        }
    }
    
    public void eseguiNround(int Nround) {
        stampaMappa();
        for (int i = 0; i < Nround; i++) {
            round();
            stampaMappa();
        }
    }
    public static void main(String[] args) {
        LangtonAnt a = new LangtonAnt(16);
        a.eseguiNround(9);
    }
}
