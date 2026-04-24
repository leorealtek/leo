package Scuola.Progettini.Scacchi.Util;

public class Casella {

    private Pezzo pezzoContenuto;

    public Casella(Pezzo pezzoContenuto) {
        this.pezzoContenuto = pezzoContenuto;
    }

    public Casella() {}

    public Pezzo getPezzoContenuto() {
        return pezzoContenuto;
    }

    public void inserisciPezzo(Pezzo pezzoContenuto) {
        this.pezzoContenuto = pezzoContenuto;
    }

    public boolean isVuota() {
        return pezzoContenuto == null;
    }
}