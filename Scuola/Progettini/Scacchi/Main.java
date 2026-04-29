package Scuola.Progettini.Scacchi;

import Scuola.Progettini.Scacchi.Partite.Partita;

public class Main {
    public static void main(String[] args) {
        Partita p = new Partita();
        p.getMappa()[1][0].getPezzoContenuto().muovi(3, 0);
        p.getMappa()[6][1].getPezzoContenuto().muovi(4, 1);
        p.getMappa()[1][0].getPezzoContenuto().muovi(3, 0);
        p.getMappa()[3][0].getPezzoContenuto().muovi(4, 1);;
    }
}