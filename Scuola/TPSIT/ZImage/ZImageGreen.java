package Scuola.TPSIT.ZImage;

import java.util.Objects;

/*
 La classe ZImageDemo è una classe contenente il metodo main che consente di visualizzare due immagini:
  - La prima in versione originale così come caricata dal disco;
  - La seconda con una modifica riguardante una "cornice" posta attorno all'immagine.
 La gestione e visualizzazione delle immagini sono fatte mediante la classe zuccante.ZImage, di cui questo
 programma è una demo.

 Autore: Prof. A. Zennaro - I.T.S. "C. Zuccante" - Venezia-Mestre.
 */

public class ZImageGreen {
    // Le seguenti sono costanti statiche utili per il programma
    private static final String IMAGE_PATH = "resources/gatto.jpg";  // Il percorso dell'immagine.

    // Attributi dell'oggetto ZImageDemo
    private ZImage image;  // La ZImage che rappresente l'immagine caricata da disco

    public ZImageGreen() {  // Costruttore
        this.image = new ZImage(this.buildImagePath());  // Si crea la ZImage a partire dal path che si deve costruire
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
        int[][] originalImageRed = this.image.getImageBlueChannel();  // Componente rossa immagine originale
        int[][] originalImageGreen = new int[this.image.getImageWidthAndHeight()[0]][this.image.getImageWidthAndHeight()[1]]; // Componente verde immagine originale
        int[][] originalImageBlue = this.image.getImageRedChannel();  // Componente blu immagine originale

        // Si crea la nuova ZImage e si restituisce
        return new ZImage(originalImageRed, originalImageGreen, originalImageBlue, row, col);
    }

    private String buildImagePath() {  // Si crea il path completo dell'immagine. N.B. Funziona solo su Windows
        return ("" + Objects.requireNonNull(ZImageGreen.class.getResource(ZImageGreen.IMAGE_PATH))).substring(6);
    }

    private void display() {  // Si mostra la ZImage originale. N.B. Metodo non necessario, creato per leggibilità
        this.image.draw();
    }

    /*
     Metodo main, prende il controllo del programma
     */
    public static void main(String[] args) {
        ZImageGreen zd = new ZImageGreen();  // Si crea un oggetto ZImageDemo

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

// 2026-02-25