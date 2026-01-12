package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1201;

public class ZuccamonEvolvibile extends Zuccamon{
    protected final String formaEvoluta;
    protected int esperienza;
    protected boolean evoluto;

    public ZuccamonEvolvibile(String nome, Elemento elemento, int puntiVita, int puntiAttacco, String formaEvoluta) {
        super(nome, elemento, puntiVita, puntiAttacco);
        this.formaEvoluta = formaEvoluta;
        this.esperienza = 0;
        this.evoluto = false;
    }

    public String getFormaEvoluta() {
        return formaEvoluta;
    }

    public int getEsperienza() {
        return esperienza;
    }

    public boolean isEvoluto() {
        return evoluto;
    }

    public void guadagnaEsperienza(int exp) {
        esperienza += exp;
        if (esperienza >= 50 && evoluto == false) evolvi();
    }

    private void evolvi() {
        nome = formaEvoluta;
        puntiVita += 20;
        puntiAttacco += 10;
        evoluto = true;
    }

    @Override
    public String toString() {
        return "ZuccamonEvolvibile [Nome: " + nome + ", Nome forma evoluta: " + formaEvoluta + ", Elemento: " + elemento + ", Esperienza: " + esperienza + ", Salute: " + puntiVita + ", Attacco: " + puntiAttacco + ", Stato evoluto: " + evoluto + "]";
    }

}