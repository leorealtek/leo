
package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class MainPolimorfismo {
    public static void main(String[] args) {
       Quadrilatero[] quadrilateri = new Quadrilatero[8];
        quadrilateri[0] = new Quadrilatero(new Punto(0, 0), new Punto(5, 0), new Punto(6, 4), new Punto(1, 4));
        quadrilateri[1] = new Rettangolo(new Punto(0, 0), 8, 5);
        quadrilateri[2] = new Quadrato(new Punto(2, 2), 6);
        quadrilateri[3] = new Rombo(new Punto(5, 5), 6, 8);
        quadrilateri[4] = new Rombo(new Punto(10, 10), 10, 60.0);
        quadrilateri[5] = new Parallelogramma(new Punto(0, 0), 7, 4, 60);
        quadrilateri[6] = new Trapezio(new Punto(0, 0), 5, 10, 6);
        quadrilateri[7] = new Trapezio(new Punto(0, 0), 5, 10, 6, 2);
        
        String[] nomi = {"Quadrilatero Generico", "Rettangolo", "Quadrato", "Rombo (diagonali)", "Rombo (lato e angolo)", "Parallelogramma", "Trapezio", "Trapezio con offset"};
        System.out.println("Array creato con " + quadrilateri.length + " quadrilateri di tipi diversi\n");
        
        for (int i = 0; i < quadrilateri.length; i++) {
            System.out.println("--- Elemento " + i + ": " + nomi[i] + " ---");
            
            double perimetroIniziale = quadrilateri[i].getPerimetro();
            System.out.println("Perimetro iniziale: " + perimetroIniziale);
            
            System.out.println("Vertici: A " + quadrilateri[i].getA() + " B " + quadrilateri[i].getB() + " C " + quadrilateri[i].getC() + " D " + quadrilateri[i].getD());
            
            quadrilateri[i].trasla(Direzione.DESTRA, 5);
            System.out.println("Dopo traslazione DESTRA di 5:");

            double perimetroFinale = quadrilateri[i].getPerimetro();
            System.out.println("Perimetro finale: " + perimetroFinale);
            
            if (Math.abs(perimetroIniziale - perimetroFinale) < 0.0001) {
                System.out.println("Perimetro invariato dopo traslazione!");
            } else {
                System.out.println("ERRORE: Perimetro cambiato!");
            }
            
            System.out.println();
        }
    }
}