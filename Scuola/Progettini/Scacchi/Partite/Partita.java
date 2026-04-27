package Scuola.Progettini.Scacchi.Partite;

import Scuola.Progettini.Scacchi.Pezzi.Alfiere;
import Scuola.Progettini.Scacchi.Pezzi.Cavallo;
import Scuola.Progettini.Scacchi.Pezzi.Pedone;
import Scuola.Progettini.Scacchi.Pezzi.Re;
import Scuola.Progettini.Scacchi.Pezzi.Regina;
import Scuola.Progettini.Scacchi.Pezzi.Torre;
import Scuola.Progettini.Scacchi.Util.Casella;

public class Partita {
    private Casella[][] mappa;

    public Partita() {
        Casella[][] mappa = new Casella[8][8];
        mappa[0][0] = new Casella(new Torre('t', 0, 0, this.mappa));
        mappa[0][1] = new Casella(new Cavallo('c', 0, 1, this.mappa));
        mappa[0][2] = new Casella(new Alfiere('a', 0, 2, this.mappa));
        mappa[0][3] = new Casella(new Regina('d', 0, 3, this.mappa));
        mappa[0][4] = new Casella(new Re('r', 0, 4, this.mappa));
        mappa[0][5] = new Casella(new Alfiere('a', 0, 5, this.mappa));
        mappa[0][6] = new Casella(new Cavallo('c', 0, 6, this.mappa));
        mappa[0][7] = new Casella(new Torre('t', 0, 7, this.mappa));
        for (int i = 0; i < mappa.length; i++) {
            mappa[1][i] = new Casella(new Pedone('p', 1, i, mappa));
            mappa[6][i] = new Casella(new Pedone('P', 6, i, mappa));
            for (int j = 2; j < 6; j++) {
                mappa[j][i] = new Casella();
            }            
        }
        mappa[7][0] = new Casella(new Torre('T', 7, 0, this.mappa));
        mappa[7][1] = new Casella(new Cavallo('C', 7, 1, this.mappa));
        mappa[7][2] = new Casella(new Alfiere('A', 7, 2, this.mappa));
        mappa[7][3] = new Casella(new Re('R', 7, 3, this.mappa));
        mappa[7][4] = new Casella(new Regina('D', 7, 4, this.mappa));
        mappa[7][5] = new Casella(new Alfiere('A', 7, 5, this.mappa));
        mappa[7][6] = new Casella(new Cavallo('C', 7, 6, this.mappa));
        mappa[7][7] = new Casella(new Torre('T', 7, 7, this.mappa));
        this.mappa = mappa;
    }

    public void stampaMappa() {
        for (Casella[] caselle : mappa) {
            for (Casella casella : caselle) {
                if (casella.getPezzoContenuto() != null) {
                    System.out.print(casella.getPezzoContenuto().getNome() + " ");
                }
                else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Partita p = new Partita();
        p.stampaMappa();
        p.mappa[1][3].getPezzoContenuto().mostraMossePossibili();
    }
}