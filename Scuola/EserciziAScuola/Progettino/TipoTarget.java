package Scuola.EserciziAScuola.Progettino;

import java.util.Random;

public enum TipoTarget {
    ATK_FORTE, ATK_DEBOLE, DEF_FORTE, DEF_DEBOLE, HP_ALTO, HP_BASSO;

    public static TipoTarget generaTarget() {
        Random generatoreNum = new Random();
        int idx = generatoreNum.nextInt(0, TipoTarget.values().length - 1);
        return TipoTarget.values()[idx];
    }
}
