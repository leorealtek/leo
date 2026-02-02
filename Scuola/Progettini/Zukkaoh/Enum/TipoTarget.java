package Scuola.Progettini.Zukkaoh.Enum;

import java.util.Random;

public enum TipoTarget {
    ATK_FORTE, ATK_DEBOLE, DEF_FORTE, DEF_DEBOLE, HP_ALTO, HP_BASSO;

    public static TipoTarget generaTarget() {
        Random generatoreNum = new Random();
        int idx = generatoreNum.nextInt(0, TipoTarget.values().length);
        return TipoTarget.values()[idx]; 
    }
}