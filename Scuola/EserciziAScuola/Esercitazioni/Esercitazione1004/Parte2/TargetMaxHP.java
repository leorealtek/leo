package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte2;

import java.util.List;

public interface TargetMaxHP {
    default Nemico getBersaglio(List<Nemico> nemiciInRange, double x, double y) {
        Nemico maxHP = nemiciInRange.get(0);
        for (Nemico nemico : nemiciInRange) {
            if (nemico.getHp() > maxHP.getHp()) maxHP = nemico;
        }
        return maxHP;
    }
}
