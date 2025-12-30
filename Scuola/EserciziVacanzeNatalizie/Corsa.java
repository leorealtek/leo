package Scuola.EserciziVacanzeNatalizie;

public class Corsa {
    private final int ID;
    private final String nomeAtleta;
    private final float distanza;
    private final float tempoInMinuti;
    private static int contatoreID = 1;

    public Corsa(String nomeAtleta, float distanza, float tempoInMinuti) {
        ID = contatoreID++;
        this.nomeAtleta = nomeAtleta;
        this.distanza = distanza;
        this.tempoInMinuti = tempoInMinuti;
    }

    public int getID() {
        return ID;
    }

    public String getNomeAtleta() {
        return nomeAtleta;
    }

    public float getDistanza() {
        return distanza;
    }

    public float getTempoInMinuti() {
        return tempoInMinuti;
    }

    public static int getContatoreID() {
        return contatoreID;
    }

    public float calcolaPunteggio() {
        return distanza * 10;
    }

    public float calcolaRitmo() {
        return tempoInMinuti / distanza;
    }

    @Override
    public String toString() {
        return "[ID: " + ID + "] " + nomeAtleta + " - " + distanza + " km in " + tempoInMinuti+ " min (Punteggio: " + calcolaPunteggio() + ")";
    }
    
}