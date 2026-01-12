package Scuola.EserciziAScuola.Esercitazioni.Esercitazione1201;

public class Main {
    public static void main(String[] args) {
        Zuccamon zuccamon1 = new Zuccamon("Pikachu", Elemento.ELETTRO, 90, 15);
        ZuccamonEvolvibile zuccamon2 = new ZuccamonEvolvibile("Squirtle", Elemento.ACQUA, 80, 10, "Squirtle evoluto");

        System.out.println(zuccamon1);
        System.out.println(zuccamon2);

        zuccamon1.attacca(zuccamon2);
        zuccamon2.attacca(zuccamon1);

        System.out.println(zuccamon1);
        System.out.println(zuccamon2);

        zuccamon2.guadagnaEsperienza(50);

        System.out.println(zuccamon2);
        
    }
}