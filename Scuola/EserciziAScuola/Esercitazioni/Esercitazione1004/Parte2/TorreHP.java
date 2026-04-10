package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte2;

import java.util.List;

public class TorreHP extends Torre implements TargetMaxHP {
    public TorreHP(String nome, int danno, double cadenzaDiFuoco, double raggio, double x, double y) {
        super(nome, danno, cadenzaDiFuoco, raggio, x, y);
    }

    @Override
    public Nemico getBersaglio(List<Nemico> nemiciInRange, double x, double y) {
        return TargetMaxHP.super.getBersaglio(nemiciInRange, x, y);
    }
}