package Scuola.EserciziAScuola.turtle.Quadrato;

import java.awt.Color;
import java.awt.Font;

/**
 * Base per il giochino. Un quadrato si muove su una griglia
 *
 * Questo esempio mostra come:
 * - Creare una griglia 
 * - Gestire il movimento con i tasti WASD 
 * - Disegnare un quadrato che si muove di cella in cella
 * - Mostrare del testo
 * 
 * Controlli:
 * - WASD: Muovi il quadrato
 * - R: Riporta il quadrato al centro
 */
public class Quadratoz extends TurtleScreen {

    // Dimensioni della griglia
    private static final int GRID_SIZE = 15;        // Numero di celle per lato
    private static final int CELL_SIZE = 30;        // Dimensione di ogni cella in pixel
    private static final int CANVAS_SIZE = GRID_SIZE * CELL_SIZE;

    // Tartarughe per il disegno
    private Turtle quadratoWASD;     // Tartaruga per disegnare il quadrato
    private Turtle quadratoFreccette;     // Tartaruga per disegnare il quadrato
    private Turtle griglia;      // Tartaruga per disegnare la griglia
    private Turtle testo;        // Tartaruga per il testo

    // Posizione del quadrato sulla griglia (in coordinate di cella)
    private int posXWASD;
    private int posYWASD;

    private int posXFreccette;
    private int posYFreccette;

    // Direzione corrente del movimento
    private int dxWASD = 0;
    private int dyWASD = 0;

    private int dxFreccette = 0;
    private int dyFreccette = 0;

    // Contatore frame per controllare la velocita' del movimento
    private int frameCount = 0;
    private final int totalFrameCount = 600;
    private int tempoRimanente = 10;
    private String vincitore = "Blu";
    private int puntiBlu = 0;
    private int puntiRosso = 0;
    private final int punteggioVincitore = (puntiBlu > puntiRosso) ? puntiBlu : puntiRosso;

    public Quadratoz() {
        super(CANVAS_SIZE, CANVAS_SIZE);
    }

    @Override
    public void setup() {
        title("QuadratoZ");
        bgcolor(new Color(30, 30, 50));

        // Inizializza la tartaruga per il quadrato WASD
        quadratoWASD = createTurtle();
        quadratoWASD.hideTurtle();
        quadratoWASD.speed(0);

        
        // Inizializza la tartaruga per il quadrato Freccette
        quadratoFreccette = createTurtle();
        quadratoFreccette.hideTurtle();
        quadratoFreccette.speed(0);

        // Inizializza la tartaruga per la griglia
        griglia = createTurtle();
        griglia.hideTurtle();
        griglia.speed(0);

        // Inizializza la tartaruga per il testo
        testo = createTurtle();
        testo.hideTurtle();
        testo.speed(0);
        testo.penUp();

        // Posiziona il quadrato al centro della griglia
        posXWASD = 0;
        posYWASD = GRID_SIZE - 1;

        posXFreccette = GRID_SIZE - 1;
        posYFreccette = GRID_SIZE - 1;
    }

    @Override
    public void loop() {
        if (frameCount < 10) {
            // Gestisci l'input della tastiera
            handleInput();

            // Incrementa il contatore dei frame
            frameCount++;

            // Muovi il quadrato ogni 8 frame (circa 7-8 volte al secondo)... 
            // Cambia per renderlo più veloce/lento
            if (frameCount % 8 == 0) {
                muoviQuadratoWASD();
                muoviQuadratoFreccette();
            }

            // Disegna tutto ogni frame
            render();
        }
        else {
            testo.goTo(-65, 0);
            testo.write("Vince il " + vincitore + " con " + punteggioVincitore + " punti");
        }
        
    }

    /**
     * Gestisce l'input da tastiera per cambiare direzione.
     */
    private void handleInput() {
        String key = getLastKey();
        if (key == null) return;

        switch (key) {
            // Movimento verso l'alto
            case "w":
                dxWASD = 0;
                dyWASD = 1;
                break;

            // Movimento verso il basso
            case "s":
                dxWASD = 0;
                dyWASD = -1;
                break;

            // Movimento verso sinistra
            case "a":
                dxWASD = -1;
                dyWASD = 0;
                break;

            // Movimento verso destra
            case "d":
                dxWASD = 1;
                dyWASD = 0;
                break;

            // Reset: riporta al centro e ferma il movimento
            case "r":
                posXWASD = 0;
                posYWASD = GRID_SIZE - 1;
                dxWASD = 0;
                dyWASD = 0;

                posXFreccette = GRID_SIZE - 1;
                posYFreccette = GRID_SIZE - 1;
                dxFreccette = 0;
                dyFreccette = 0;
                break;

            // Movimento verso l'alto
            case "up":
                dxFreccette = 0;
                dyFreccette = 1;
                break;
        
            // Movimento verso il basso
            case "down":
                dxFreccette = 0;
                dyFreccette = -1;
                break;
        
            // Movimento verso sinistra
            case "left":
                dxFreccette = -1;
                dyFreccette = 0;
                break;
        
            // Movimento verso destra
            case "right":
                dxFreccette = 1;
                dyFreccette = 0;
                break;
            }
        }
    
    /**
     * Muove il quadrato nella direzione corrente.
     * Il quadrato si ferma ai bordi della griglia.
     */
    private void muoviQuadratoWASD() {
        // Calcola la nuova posizione
        int nuovaX = posXWASD + dxWASD;
        int nuovaY = posYWASD + dyWASD;

        // Controlla i bordi della griglia
        if (nuovaX >= 0 && nuovaX < GRID_SIZE) {
            posXWASD = nuovaX;
        }
        if (nuovaY >= 0 && nuovaY < GRID_SIZE) {
            posYWASD = nuovaY;
        }
    }

    private void muoviQuadratoFreccette() {
        // Calcola la nuova posizione
        int nuovaX = posXFreccette + dxFreccette;
        int nuovaY = posYFreccette + dyFreccette;

        // Controlla i bordi della griglia
        if (nuovaX >= 0 && nuovaX < GRID_SIZE) {
            posXFreccette = nuovaX;
        }
        if (nuovaY >= 0 && nuovaY < GRID_SIZE) {
            posYFreccette = nuovaY;
        }
    }

    private void render() {
        // Disegna la griglia di sfondo
        disegnaGriglia();

        // Disegna il quadrato "evidenziato"
        disegnaQuadratoWASD();
        disegnaQuadratoFreccette();

        // Disegna le istruzioni
        disegnaTesto();
    }

    /**
     * Disegna una griglia leggera di sfondo.
     */
    private void disegnaGriglia() {
        griglia.setPenColor(new Color(60, 60, 80));
        griglia.setPenSize(1);

        // Linee verticali
        for (int i = 0; i <= GRID_SIZE; i++) {
            double x = i * CELL_SIZE - CANVAS_SIZE / 2.0;
            griglia.penUp();
            griglia.goTo(x, -CANVAS_SIZE / 2.0);
            griglia.penDown();
            griglia.goTo(x, CANVAS_SIZE / 2.0);
        }

        // Linee orizzontali
        for (int i = 0; i <= GRID_SIZE; i++) {
            double y = i * CELL_SIZE - CANVAS_SIZE / 2.0;
            griglia.penUp();
            griglia.goTo(-CANVAS_SIZE / 2.0, y);
            griglia.penDown();
            griglia.goTo(CANVAS_SIZE / 2.0, y);
        }
    }

    /**
     * Disegna il quadrato nella posizione corrente.
     */
    private void disegnaQuadratoWASD() {
        // Converti le coordinate della griglia in coordinate del canvas
        double xWASD = posXWASD * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double yWASD = posYWASD * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;

        // Imposta i colori
        quadratoWASD.setPenColor(Color.WHITE);
        quadratoWASD.setFillColor(new Color(100, 150, 255));

        // Posiziona la tartaruga nell'angolo in basso a sinistra della cella
        quadratoWASD.penUp();
        quadratoWASD.goTo(xWASD - CELL_SIZE / 2.0 + 2, yWASD - CELL_SIZE / 2.0 + 2);
        quadratoWASD.penDown();

        // Disegna il quadrato con riempimento
        quadratoWASD.beginFill();
        for (int i = 0; i < 4; i++) {
            quadratoWASD.forward(CELL_SIZE - 4);
            quadratoWASD.left(90);
        }
        quadratoWASD.endFill();
    }

    private void disegnaQuadratoFreccette() {
        // Converti le coordinate della griglia in coordinate del canvas
        double xFreccette = posXFreccette * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;
        double yFreccette = posYFreccette * CELL_SIZE - CANVAS_SIZE / 2.0 + CELL_SIZE / 2.0;

        // Imposta i colori
        quadratoFreccette.setPenColor(Color.WHITE);
        quadratoFreccette.setFillColor(new Color(255, 0, 0));

        // Posiziona la tartaruga nell'angolo in basso a sinistra della cella
        quadratoFreccette.penUp();
        quadratoFreccette.goTo(xFreccette - CELL_SIZE / 2.0 + 2, yFreccette - CELL_SIZE / 2.0 + 2);
        quadratoFreccette.penDown();

        // Disegna il quadrato con riempimento
        quadratoFreccette.beginFill();
        for (int i = 0; i < 4; i++) {
            quadratoFreccette.forward(CELL_SIZE - 4);
            quadratoFreccette.left(90);
        }
        quadratoFreccette.endFill();
    }

    private void disegnaTesto() {
        testo.setPenColor(Color.WHITE); // Usiamo una tartaruga apposita

        // Creiamo un "Font" da usare per scrivere
        Font f = new Font("Arial", Font.PLAIN, 15);
        // Istruzioni in alto

        // Posizione corrente in basso
        testo.goTo(-CANVAS_SIZE / 2.0, -CANVAS_SIZE / 2.0 + 8);
        String posizioneBlu = "Posizione Blu: ( "+ posXWASD + " " + posYWASD + ")";
        testo.write(posizioneBlu, "", f);

        testo.goTo(50, -CANVAS_SIZE / 2.0 + 8);
        String posizioneRosso = "Posizione Rosso: ( "+ posXFreccette + " " + posYFreccette + ")";
        testo.write(posizioneRosso, "", f);
        
        testo.goTo(-65, CANVAS_SIZE / 2.0 - 15);
        String counterTempo = "Tempo rimanente: " + (1 + (totalFrameCount - frameCount) / 60);
        testo.write(counterTempo, "", f);

    }

    public static void main(String[] args) {
        TurtleScreen app = new Quadratoz();
        app.run();
    }
}