package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte2;

import java.util.List;

public class TorreVicino extends Torre implements TargetVicino {
    public TorreVicino(String nome, int danno, double cadenzaDiFuoco, double raggio, double x, double y) {
        super(nome, danno, cadenzaDiFuoco, raggio, x, y);
    }

    @Override
    public Nemico getBersaglio(List<Nemico> nemiciInRange, double x, double y) {
        return TargetVicino.super.getBersaglio(nemiciInRange, x, y);
    }
}