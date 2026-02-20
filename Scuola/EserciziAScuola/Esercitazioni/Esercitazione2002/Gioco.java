package Scuola.EserciziAScuola.Esercitazioni.Esercitazione2002;

public class Gioco {
    private Giocatore[] giocatori;
    private int ultimoVincitore;
    private int indicePartite;

    public Gioco(int quantiGiocatori) {
        giocatori = creaGiocatori(quantiGiocatori);
        ultimoVincitore = -1;
        indicePartite = 0;
    }

    private Giocatore[] creaGiocatori(int quantiGiocatori) {
        Giocatore[] giocatori = new Giocatore[quantiGiocatori];
        for (int i = 0; i < quantiGiocatori; i++) {
            giocatori[i] = new Giocatore(6);
        }
        return giocatori;
    }

    public void round() {
        int somma = 0;
        indicePartite++;

        for (Giocatore giocatore : giocatori) {
            somma += giocatore.generaNumeroCasuale();
        }

        int indiceVincitore = somma % giocatori.length;
        Giocatore vincitore = giocatori[indiceVincitore];
        vincitore.setPartiteVinte(vincitore.getPartiteVinte() + 1);
        ultimoVincitore = indiceVincitore;
        System.out.println("Ha vinto " + vincitore.getNome() + " alla partita: " + indicePartite);
    }

    public void torneo(int quantePartite) {
        for (int i = 0; i < quantePartite; i++) {
            round();
        }
    }

    public void classifica() {
        ordinaPerPartiteVinte(giocatori);
        for (int i = 0; i < giocatori.length; i++) {
            System.out.println("Posizione in classifica: " + (i + 1) + " " +giocatori[i].toString());
        }
    }

    public static void ordinaPerPartiteVinte(Giocatore[] giocatori) {
        int n = giocatori.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (giocatori[j].getPartiteVinte() > giocatori[minIndex].getPartiteVinte()) {
                    minIndex = j;
                }
            }
            Giocatore temp = giocatori[minIndex];
            giocatori[minIndex] = giocatori[i];
            giocatori[i] = temp;
        }
    }

    public static void main(String[] args) {
        Gioco g = new Gioco(3);
        g.torneo(5);
        g.classifica();
    }

}