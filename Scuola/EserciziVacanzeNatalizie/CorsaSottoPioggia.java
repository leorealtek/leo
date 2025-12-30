package Scuola.EserciziVacanzeNatalizie;

public class CorsaSottoPioggia extends Corsa {
    private final int intensitaPioggia;

    public CorsaSottoPioggia(String nomeAtleta, float distanza, float tempoInMinuti, int intensitaPioggia) {
        super(nomeAtleta, distanza, tempoInMinuti);
        this.intensitaPioggia = intensitaPioggia;
    }

    public int getIntensitaPioggia() {
        return intensitaPioggia;
    }

    @Override
    public float calcolaPunteggio() {
        if (intensitaPioggia > 5) return super.calcolaPunteggio() * 1.5f;
        else return super.calcolaPunteggio();
    }

    @Override
    public String toString() {
        return super.toString() + " [Pioggia intensità " + intensitaPioggia + "]";
    }
}