package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte2;

import java.util.ArrayList;
import java.util.List;

public abstract class Torre {
    protected final String nome;
    protected int danno;
    protected double cadenzaDiFuoco;
    protected double raggio;
    protected double x, y;

    public Torre(String nome, int danno, double cadenzaDiFuoco, double raggio, double x, double y) {
        this.nome = nome;
        this.danno = danno;
        this.cadenzaDiFuoco = cadenzaDiFuoco;
        this.raggio = raggio;
        this.x = x;
        this.y = y;
    }

    public List<Nemico> getNemiciInRange(List<Nemico> tutti) {
        List<Nemico> nemiciInRange = new ArrayList<>();
        for (Nemico nemico : tutti) {
            if (nemico.distanzaDa(x,y) <= raggio) nemiciInRange.add(nemico);
        }
        return nemiciInRange;
    }

    public abstract Nemico getBersaglio(List<Nemico> nemiciInRange, double x, double y);

    public void attacca(List<Nemico> tutti) {
        List<Nemico> nemiciInRange = getNemiciInRange(tutti);
        if (nemiciInRange.isEmpty()) {
            System.out.println("[" + nome + "] : nessun nemico in range");
            return;
        }
        Nemico bersaglio = getBersaglio(nemiciInRange, x, y);
        bersaglio.subisciDanni(danno);
    }
}