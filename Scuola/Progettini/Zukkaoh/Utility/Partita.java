package Scuola.Progettini.Zukkaoh.Utility;

public class Partita {
    private final Giocatore giocatore1;
    private final Giocatore giocatore2;
    private int turno;
    private boolean primoTurno;

    public Partita(String nomeGiocatore1, String nomeGiocatore2) {
        giocatore1 = new Giocatore(nomeGiocatore1);
        giocatore2 = new Giocatore(nomeGiocatore2);
        turno = 0;
        primoTurno = true;
    }

    public void gioca() {
        System.out.println("   ZU-KKA-OH! - INIZIO PARTITA");
        System.out.println(giocatore1.getNome() + " VS " + giocatore2.getNome());
        
        System.out.println(">>> STATO INIZIALE <<<");
        System.out.println("\n" + giocatore1.getNome() + ":");
        giocatore1.mostraMano();
        System.out.println("\n" + giocatore2.getNome() + ":");
        giocatore2.mostraMano();
        Giocatore[] giocatori = {giocatore1, giocatore2};
        
        while (!isFinita()) {
            turno();
            turno++;
        }

        for (Giocatore giocatore : giocatori) {
            System.out.println("\n--- STATISTICHE FINALI DI " + giocatore.getNome() + " ---");
            System.out.println("Carte distrutte: " + giocatore.getCarteDistrutte());
            System.out.println("Danno inflitto: " + giocatore.getDannoInflitto());
        }
        
        Giocatore vincitore = getVincitore();
        System.out.println("   PARTITA TERMINATA!");
        System.out.println("   VINCITORE: " + vincitore.getNome());
        if (vincitore.controllaExodia()) {
            System.out.println("   EXODIA È STATO EVOCATO! \n" + "  " + vincitore.getNome() + "    VINCE AUTOMATICAMENTE!");
        }
    }

    public void turno() {
        Giocatore giocatoreCorrente = (turno % 2 == 0) ? giocatore1 : giocatore2;
        Giocatore avversario = (turno % 2 == 0) ? giocatore2 : giocatore1;
        
        System.out.println("\nTURNO " + (turno + 1) + " - " + giocatoreCorrente.getNome());
        
        System.out.println("\n--- FASE DI EVOCAZIONE ---");
        giocatoreCorrente.evocaCarta();
        
        Utility.ordinaManoPerHP(giocatoreCorrente.getMano());
        
        if (!primoTurno) {
            giocatoreCorrente.faseBattaglia(avversario);
        } else {
            System.out.println("\n--- FASE BATTAGLIA ---");
            System.out.println(giocatoreCorrente.getNome() + " salta la fase battaglia (primo turno)");
            primoTurno = false;
        }
        
        System.out.println("\n--- FASE FINALE ---");
        giocatoreCorrente.pescaCarta();
        
        Utility.ordinaManoPerHP(giocatoreCorrente.getMano());
        
        Utility.ordinaCampoPerPuntiTotali(giocatoreCorrente.getCampo());
        
        System.out.println("\n--- STATO ALLA FINE DEL TURNO ---");
        System.out.println(giocatoreCorrente.getNome() + " - Punti Vita: " + giocatoreCorrente.getPuntiVita() + "/3");
        giocatoreCorrente.mostraMano();
        giocatoreCorrente.mostraCampo();
        System.out.println();
        System.out.println(avversario.getNome() + " - Punti Vita: " + avversario.getPuntiVita() + "/3");
        avversario.mostraCampo();
    }

    public boolean isFinita() {
        return giocatore1.isSconfitto() || giocatore2.isSconfitto() || 
               giocatore1.haPerso() || giocatore2.haPerso();
    }

    public Giocatore getVincitore() {
        if (giocatore1.isSconfitto() || giocatore1.haPerso()) {
            return giocatore2;
        } else {
            return giocatore1;
        }
    }
}