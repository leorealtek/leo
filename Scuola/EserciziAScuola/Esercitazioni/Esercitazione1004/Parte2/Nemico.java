package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1004.Parte2;

public class Nemico {
    private final String nome;
    private int hp;
    private double x, y;
    public Nemico(String nome, int hp, double x, double y) {
        this.nome = nome;
        this.hp = hp;
        this.x = x;
        this.y = y;
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void subisciDanni(int danni) {
        this.hp = Math.max(0, hp-danni);
    }

    public double distanzaDa(double torreX, double torreY) {
        double dx = x - torreX;
        double dy = y - torreY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return nome + " (HP: " + hp + ")";
    }
}