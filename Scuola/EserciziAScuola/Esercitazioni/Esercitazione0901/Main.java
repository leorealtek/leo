package Scuola.EserciziAScuola.Esercitazioni.Esercitazione0901;

public class Main {

    private static void testQuadrilatero(Quadrilatero q, String nome) {
        System.out.println("Tipo: " + nome);

        System.out.println("Vertici iniziali:");
        stampaVertici(q);
        
        double perimetroIniziale = q.getPerimetro();
        System.out.println("Perimetro iniziale: " + perimetroIniziale);
        
        System.out.println("Traslazione: SU di 3 unità");
        q.trasla(Direzione.SU, 3);
        
        System.out.println("Vertici dopo traslazione:");
        stampaVertici(q);
        
        double perimetroFinale = q.getPerimetro();
        System.out.println("Perimetro finale: " + perimetroFinale);
        
        if (Math.abs(perimetroIniziale - perimetroFinale) < 0.0001) {
            System.out.println("Perimetro invariato!");
        } 
        else {
            System.out.println("ERRORE: Perimetro cambiato!");
        }
    }

    private static void stampaVertici(Quadrilatero q) {
        System.out.println("  A: " + q.getA());
        System.out.println("  B: " + q.getB());
        System.out.println("  C: " + q.getC());
        System.out.println("  D: " + q.getD());
    }

    public static void main(String[] args) {
        System.out.println("--- 1. QUADRILATERO GENERICO ---");

        Quadrilatero q1 = new Quadrilatero(new Punto(0, 0), new Punto(5, 0), new Punto(6, 4), new Punto(1, 4));
        testQuadrilatero(q1, "Quadrilatero Generico");
        
        System.out.println("\n--- 2. RETTANGOLO ---");

        Rettangolo r1 = new Rettangolo(new Punto(0, 0), 8, 5);
        testQuadrilatero(r1, "Rettangolo");
        System.out.println("Area: " + r1.getArea());
        
        System.out.println("\n--- 3. QUADRATO ---");

        Quadrato q2 = new Quadrato(new Punto(2, 2), 6);
        testQuadrilatero(q2, "Quadrato");
        System.out.println("Area: " + q2.getArea());
        
        System.out.println("\n--- 4. ROMBO (con diagonali) ---");

        Rombo rombo1 = new Rombo(new Punto(5, 5), 6, 8);
        testQuadrilatero(rombo1, "Rombo (diagonali)");
        
        System.out.println("\n--- 5. ROMBO (con lato e angolo) ---");

        Rombo rombo2 = new Rombo(new Punto(10, 10), 10, 60.0);
        testQuadrilatero(rombo2, "Rombo (lato e angolo)");
        
        System.out.println("\n--- 6. PARALLELOGRAMMA ---");

        Parallelogramma p1 = new Parallelogramma(new Punto(0, 0), 7, 4, 60);
        testQuadrilatero(p1, "Parallelogramma");
        
        System.out.println("\n--- 7. TRAPEZIO (senza offset) ---");

        Trapezio t1 = new Trapezio(new Punto(0, 0), 5, 10, 6);
        testQuadrilatero(t1, "Trapezio");
        System.out.println("Area: " + t1.getArea());
        
        System.out.println("\n--- 8. TRAPEZIO (con offset) ---");

        Trapezio t2 = new Trapezio(new Punto(0, 0), 5, 10, 6, 2);
        testQuadrilatero(t2, "Trapezio con offset");
        System.out.println("Area: " + t2.getArea());

    }
}