package Scuola.EserciziAScuola.Progettino;

public class Giocatore {
    protected String nome;
    protected Carta[] mazzo;
    protected Carta[] mano;
    protected Carta[] campo;
    protected int puntiVita;
    public Giocatore(String nome) {
        this.nome = nome;
        mazzo = creaMazzo();
        puntiVita = 3;
    }

    private static Carta[] creaMazzo() {
        Carta[] mazzo = new Carta[50];
        for (int i = 0; i < mazzo.length; i++) {
            mazzo[i] = new Carta();
        }
        return mazzo;
    }

}
