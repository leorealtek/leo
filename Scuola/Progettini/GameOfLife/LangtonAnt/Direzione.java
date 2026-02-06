package Scuola.Progettini.GameOfLife.LangtonAnt;

public enum Direzione {
    SU, DESTRA, GIU, SINISTRA;

    public Direzione cambiaDirezioneOrario() {
        switch (this) {
            case SU:
                return DESTRA;
            case DESTRA:
                return GIU;
            case GIU:
                return SINISTRA;
            default:
                return SU;
        }
    }

    public Direzione cambiaDirezioneAntiOrario() {
        switch (this) {
            case SU:
                return SINISTRA;
            case DESTRA:
                return SU;
            case GIU:
                return DESTRA;
            default:
                return GIU;
        }
    }
}