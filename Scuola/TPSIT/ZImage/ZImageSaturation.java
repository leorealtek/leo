package Scuola.TPSIT.ZImage;

import java.util.Objects;
import java.util.Scanner;


public class ZImageSaturation {
        // Le seguenti sono costanti statiche utili per il programma
    private static final String IMAGE_PATH = "resources/post_card.jpg";  // Il percorso dell'immagine.

    // Attributi dell'oggetto ZImageDemo
    private ZImage image;  // La ZImage che rappresente l'immagine caricata da disco
    private final double percentuale;

    public ZImageSaturation(double percentuale) {  // Costruttore
        this.image = new ZImage(this.buildImagePath());  // Si crea la ZImage a partire dal path che si deve costruire
        this.percentuale = percentuale;
        if (!this.image.isLoaded()) {  // Se il caricamento non è andato a buon fine
            System.err.println("L'immagine non è stata caricata");  // Si stampa un errore
            System.exit(1);  // Si esce
        }
        this.display();  // Si mostra l'immagine
    }

    public ZImage buildFramedImage() {  // Questo metodo crea una ZImage, non da immagine ma con le componenti RGB
        int[] dim = this.image.getImageWidthAndHeight();  // Si ottiene la dimensione dell'immagine caricata da file
        int row = dim[0];  // Righe
        int col = dim[1];  // Colonne
        int[][] originalRed = this.image.getImageRedChannel();  // Componente rossa immagine originale
        int[][] originalGreen = this.image.getImageGreenChannel(); // Componente verde immagine originale
        int[][] originalBlue = this.image.getImageBlueChannel();  // Componente blu immagine originale
        int[][] newRed = new int[row][col];  // Componente blu dell'immagine da costruire
        int[][] newGreen = new int[row][col];  // Componente verde dell'immagine da costruire
        int[][] newBlue = new int[row][col];  // Componente blu dell'immagine da costruire

        for (int i = 0; i < row; i++) {  // Per ogni riga dell'immagine
            for (int j = 0; j < col; j++) {  // Per ogni colonna dell'immagine
                // Si configurano i colori della cornice
                newRed[i][j] = calcolaRGB(originalRed[i][j], originalGreen[i][j], originalBlue[i][j])[0];
                newGreen[i][j] = calcolaRGB(originalRed[i][j], originalGreen[i][j], originalBlue[i][j])[1];
                newBlue[i][j] = calcolaRGB(originalRed[i][j], originalGreen[i][j], originalBlue[i][j])[2];
            }
        }

        // Si crea la nuova ZImage e si restituisce
        return new ZImage(newRed, newGreen, newBlue, row, col);
    }

    private String buildImagePath() {  // Si crea il path completo dell'immagine. N.B. Funziona solo su Windows
        return ("" + Objects.requireNonNull(ZImageSaturation.class.getResource(ZImageSaturation.IMAGE_PATH))).substring(6);
    }

    private void display() {  // Si mostra la ZImage originale. N.B. Metodo non necessario, creato per leggibilità
        this.image.draw();
    }

    private int[] calcolaRGB(int red, int green, int blue) {
        int[] result = new int[3];
        int newRed = red;
        int newGreen = green;
        int newBlue = blue;

        int media = (red + green + blue) / 3;
        int compMax = Math.max(red, Math.max(green, blue));
        int compMin = Math.min(red, Math.min(green, blue));
        int scostamentoMax = (int) (Math.abs(compMax - media) * (percentuale / 100));
        int scostamentoMin = (int) (Math.abs(compMin - media) * (percentuale / 100));

        if (compMax == red) newRed = (newRed + scostamentoMax > 255) ? 255 : newRed + scostamentoMax;
        if (compMax == green) newGreen = (newGreen + scostamentoMax > 255) ? 255 : newGreen + scostamentoMax;
        if (compMax == blue) newRed = (newBlue + scostamentoMax > 255) ? 255 : newBlue + scostamentoMax;

        if (compMin == red) newRed = Math.abs(newRed - scostamentoMin);
        if (compMin == green) newGreen = Math.abs(newGreen - scostamentoMin);
        if (compMin == blue) newBlue = Math.abs(newBlue - scostamentoMin);

        result[0] = newRed;
        result[1] = newGreen;
        result[2] = newBlue;

        return result;
    }

    /*
     Metodo main, prende il controllo del programma
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Dimmi una percentuale tra -100 e 100%: ");
        double percentuale = s.nextDouble();

        ZImageSaturation zd = new ZImageSaturation(percentuale);  // Si crea un oggetto ZImageDemo

        ZImage framed;  // Si crea un'altra ZImage
        if ((framed = zd.buildFramedImage()).isLoaded()) {  // Se la creazione dell'immagine con cornice va a buon fine
            framed.draw();  //  Si visualizza la nuova immagine.
        }
        //  --- ATTENZIONE ---
        // ZImage visualizza le immagini sempre nella posizione (100,100) dello schermo a partire dall'angolo
        // in alto a sinistra che ha coordinate (0,0). Se si visualizzano più immagini, saranno tutte sovrapposte
        // e sarà necessario spostare le finestre per visualizzarle tutte.
    }
}