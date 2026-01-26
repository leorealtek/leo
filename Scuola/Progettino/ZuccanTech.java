package Scuola.Progettino;

public class ZuccanTech extends Carta {

    protected final EffettoTech effettoTech;

    public ZuccanTech() {
        super();
        this.isZuccanTech = true;
        if (Math.random() < 0.5d) {
            effettoTech = EffettoTech.generaEffettoTech();
        } else {
            effettoTech = null;
        }
    }

    public EffettoTech getEffettoTech() {
        return effettoTech;
    }

    public void usaEffettoTech(Giocatore giocatore, Carta cartaAvversaria) {
        if (effettoTech != null && !effettoTech.isUsato()) {
            switch (effettoTech) {
                case PESCA_CARTA -> effettoTech.usaEffettoPescaCarta(giocatore);
                case SCUDO -> effettoTech.usaEffettoScudo(this);
                case CAMBIA_STATISTICHE -> effettoTech.usaEffettoCambiaStatistiche(this);
            }
            effettoTech.setUsato(true);
        }
    }

    @Override
    public String toString() {
        return nome + " [ZUCCAN-TECH] - HP: " + puntiVita + ", ATK: " + puntiAttacco + 
               ", DEF: " + puntiDifesa + ", Abilità: " + abilita + 
               (effettoTech != null ? ", Effetto Tech: " + effettoTech.getDescrizione() : "");
    }
}