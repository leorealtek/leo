package Scuola.Progettini.GameOfLife;

public class AutomaPersonalizzato extends Automa1D {
    public AutomaPersonalizzato(int larghezza) {
        super(larghezza, 0);
    }

    @Override
    protected String calcolaStato(char sinistra, char centro, char destra) {
        if (sinistra == '1' && centro == '1' && destra == '1') return "0";
        else if (sinistra == '1' || centro == '1' || destra == '0') return "1";
        else if (sinistra == '1' || centro == '0' && destra == '1') return "1";
        else if (sinistra == '1' && centro == '0' || destra == '0') return "1";
        else if (sinistra == '1' && centro == '0' && destra == '0') return "1";
        else if (sinistra == '0' && centro == '1' && destra == '1') return "1";
        else if (sinistra == '0' || centro == '1' && destra == '0') return "0";
        else if (sinistra == '0' && centro == '0' && destra == '1') return "0";
        else return "1";
    }
}
