package Scuola.Progettino;

import java.util.Random;

public enum EffettoTech {
    PESCA_CARTA("Pesca una carta con un'abilità specifica casuale"),
    SCUDO("Riduce il danno del prossimo attacco dell'80%"),
    CAMBIA_STATISTICHE("Cambia casualmente in modo positivo una statistica della carta");

    private final String descrizione;
    private boolean isUsato = false;
    private boolean scudoAttivo = false;

    EffettoTech(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public boolean isUsato() {
        return isUsato;
    }

    public boolean isScudoAttivo() {
        return scudoAttivo;
    }

    public static EffettoTech generaEffettoTech() {
        Random generatoreNum = new Random();
        int idx = generatoreNum.nextInt(0, EffettoTech.values().length);
        return EffettoTech.values()[idx];
    }

    public void setUsato(boolean usato) {
        isUsato = usato;
    }

    public void usaEffettoPescaCarta(Giocatore giocatore) {
        System.out.println("Effetto Tech: " + this);
        Abilita abilitaDaPescare = Abilita.getRandomAbilita();
        Carta cartaPescata = giocatore.pescaCartaConAbilita(abilitaDaPescare);
        if (cartaPescata != null) {
            System.out.println("Hai pescato la carta: " + cartaPescata.getNome() + 
                              " con l'abilità: " + abilitaDaPescare);
        } else {
            System.out.println("Nessuna carta con l'abilità " + abilitaDaPescare + " trovata nel mazzo!");
        }
    }

    public void usaEffettoCambiaStatistiche(ZuccanTech carta) {
        System.out.println("Effetto Tech: " + this);
        int statisticaDaMigliorare = new Random().nextInt(0, 3);

        String statisticaNome = switch (statisticaDaMigliorare) {
            case 0 -> "Punti Vita";
            case 1 -> "Punti Attacco";
            default -> "Punti Difesa";
        };

        int puntiMiglioramento = new Random().nextInt(10, 21);

        switch (statisticaDaMigliorare) {
            case 0 -> carta.setPuntiVita(carta.getPuntiVita() + puntiMiglioramento);
            case 1 -> carta.setPuntiAttacco(carta.getPuntiAttacco() + puntiMiglioramento);
            default -> carta.setPuntiDifesa(carta.getPuntiDifesa() + puntiMiglioramento);
        }
        System.out.println("Statistica migliorata: " + statisticaNome + " di " + puntiMiglioramento);
    }

    public void usaEffettoScudo(ZuccanTech carta) {
        System.out.println("Effetto Tech: " + this);
        scudoAttivo = true;
        System.out.println("Scudo Tech attivato! Il danno del prossimo attacco ricevuto sarà ridotto dell'80%.");
    }

    public int applicaScudo(int dannoOriginale) {
        if (scudoAttivo) {
            int dannoRidotto = (int) (dannoOriginale * 0.2); 
            if (dannoRidotto < 1) dannoRidotto = 1; 
            scudoAttivo = false; 
            System.out.println(">>> SCUDO TECH CONSUMATO! Danno ridotto da " + dannoOriginale + 
                             " a " + dannoRidotto + " <<<");
            return dannoRidotto;
        }
        return dannoOriginale;
    }

    public void resetScudo() {
        scudoAttivo = false;
    }

}