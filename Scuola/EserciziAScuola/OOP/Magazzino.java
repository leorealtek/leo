package Scuola.EserciziAScuola.OOP;

public class Magazzino {
    public static void stampaInventario(Prodotto[] prodotti) {
        for (Prodotto prodotto : prodotti) {
            System.out.println(prodotto);
        }
    }

    public static void scontaTutto(Prodotto[] prodotti, int percentuale) {
        for (Prodotto prodotto : prodotti) {
            if (prodotto instanceof Scontabile s) s.applicaSconto(percentuale); 
        }
    }

    public static void stampaSpedizioni(Object[] ordini) {
        for (Object prodotto : ordini) {
            if (prodotto instanceof Spedibile s) System.out.println("Spese di spedizione: " + s.calcolaSpese() + " Corriere: " + s.corriere()); 
        }   
    }
}
