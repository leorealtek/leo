package Scuola.Progettini.Scacchi;

import Scuola.Progettini.Scacchi.Partite.Partita;
import Scuola.Progettini.Scacchi.Pezzi.Re;

public class Main {
    public static void main(String[] args) {
        Partita p = new Partita();
        p.getMappa()[0][6].getPezzoContenuto().muovi(2, 7);
        p.getMappa()[1][6].getPezzoContenuto().muovi(2, 6);
        p.getMappa()[0][5].getPezzoContenuto().muovi(1, 6);
        if (p.getMappa()[0][4].getPezzoContenuto() instanceof Re re) re.arrocca(false);
        p.stampaMappa();
    }
}