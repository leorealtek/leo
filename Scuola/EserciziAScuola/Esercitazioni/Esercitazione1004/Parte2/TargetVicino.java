package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte2;

import java.util.List;

public interface TargetVicino {
    default Nemico getBersaglio(List<Nemico> nemiciInRange, double x, double y) {
        Nemico distMinore = nemiciInRange.get(0);
        for (Nemico nemico : nemiciInRange) {
            if (nemico.distanzaDa(x, y) < distMinore.distanzaDa(x, y)) distMinore = nemico;
        }
        return distMinore;
    }
}
