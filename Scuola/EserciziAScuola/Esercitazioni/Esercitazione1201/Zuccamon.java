package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1201;

public class Zuccamon {
    protected String nome;
    protected final Elemento elemento;
    protected int puntiVita;
    protected int puntiAttacco;

    public Zuccamon(String nome, Elemento elemento, int puntiVita, int puntiAttacco) {
        this.nome = nome;
        this.elemento = elemento;
        this.puntiVita = puntiVita;
        this.puntiAttacco = puntiAttacco;
    }

    public String getNome() {
        return nome;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public int getPuntiVita() {
        return puntiVita;
    }

    public int getPuntiAttacco() {
        return puntiAttacco;
    }

    public int riceviDanno(int danno) {
        puntiVita -= danno;
        return puntiVita;
    }

    public void attacca(Zuccamon difensore) {
        int efficacia = (int) elemento.calcolaEfficacia(difensore.getElemento());
        int danno = (puntiAttacco * efficacia);
        difensore.riceviDanno(danno);
        if (efficacia > 1) System.out.println("È superefficace!");
        if (efficacia < 1) System.out.println("Non è molto efficace...");
    }

    public boolean isVivo() {
        return (puntiVita > 0) ? true : false;
    }

    @Override
    public String toString() {
        return "Zuccamon [Nome: " + nome + " Elemento: " + elemento + " Salute: " + puntiVita + " Attacco: "+ puntiAttacco + "]";
    }

}