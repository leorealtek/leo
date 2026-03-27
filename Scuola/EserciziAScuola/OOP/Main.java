package Scuola.EserciziAScuola.OOP;

public class Main {
    public static void main(String[] args) {
        Prodotto[] catalogo = {
            new ProdottoAlimentare("Pasta", 1.50, 350, false),
            new Elettronico("Laptop", 999.0, 24),
            new Digitale("Antivirus", 29.99)
            };
        Magazzino.stampaInventario(catalogo);
        Magazzino.scontaTutto(catalogo, 10);
        Object[] ordini = {
            new Digitale("Ebook", 9.99),
            new Elettronico("Mouse", 30.0, 12),
            new Digitale("Gioco", 19.99)
            };
        Magazzino.stampaSpedizioni(ordini);
    }
}
