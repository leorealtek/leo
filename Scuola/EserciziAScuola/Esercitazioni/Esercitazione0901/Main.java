package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Main {
    public static void main(String[] args) {
        Punto p1 = new Punto(5, 5);
        p1.trasla(Direzione.SU, 2);
        System.out.println(p1);
        p1.trasla(Direzione.DESTRA, -1);
        System.out.println(p1);
        p1.trasla(Direzione.SINISTRA, 5);
        System.out.println(p1);
    }
}
