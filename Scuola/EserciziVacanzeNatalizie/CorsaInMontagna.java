package Scuola.EserciziVacanzeNatalizie;

public class CorsaInMontagna extends Corsa {
    private final int dislivello;

    public CorsaInMontagna(String nomeAtleta, float distanza, float tempoInMinuti, int dislivello) {
        super(nomeAtleta, distanza, tempoInMinuti);
        this.dislivello = dislivello;
    }

    public int getDislivello() {
        return dislivello;
    }

    @Override
    public float calcolaPunteggio() {
        return super.calcolaPunteggio() + dislivello * 2;
    }

    @Override
    public String toString() {
        return super.toString() + " [Montagna +250m]";
    }

}