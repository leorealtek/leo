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
public class ZImageDemo {
    // Le seguenti sono costanti statiche utili per il programma
    private static final String IMAGE_PATH = "resources/post_card.jpg";  // Il percorso dell'immagine.
    private static final int FRAME_RED = 255;  // Componente rossa della cornice
    private static final int FRAME_GREEN = 105;  // Componente verde della cornice
    private static final int FRAME_BLUE = 180;  // componente blu della cornice
    private static final int FRAME_THICKNESS = 10;  // Spessore della cornice
    private static final int STRISCIA_THICKNESS = 10;  // Spessore della striscia

    // Attributi dell'oggetto ZImageDemo
    private ZImage image;  // La ZImage che rappresente l'immagine caricata da disco

    public ZImageDemo() {  // Costruttore
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
        int[][] originalImageRed = this.image.getImageRedChannel();  // Componente rossa immagine originale
        int[][] originalImageGreen = this.image.getImageGreenChannel();  // Componente verde immagine originale
        int[][] originalImageBlue = this.image.getImageBlueChannel();  // Componente blu immagine originale
        int[][] newFramedImageRed = new int[row][col];  // Componente blu dell'immagine da costruire
        int[][] newFramedImageGreen = new int[row][col];  // Componente verde dell'immagine da costruire
        int[][] newFramedImageBlue = new int[row][col];  // Componente blu dell'immagine da costruire

        for (int i = 0; i < row; i++) {  // Per ogni riga dell'immagine
            for (int j = 0; j < col; j++) {  // Per ogni colonna dell'immagine
                if (this.checkFrameZone(i,j,row,col) || this.checkStrisciaVerticale(j, col) || this.checkStrisciaOrrizontale(i, row)) {  // Si controlla se si è in un'area "cornice", se sì
                    // Si configurano i colori della cornice
                    newFramedImageRed[i][j] = ZImageDemo.FRAME_RED;
                    newFramedImageGreen[i][j] = ZImageDemo.FRAME_GREEN;
                    newFramedImageBlue[i][j] = ZImageDemo.FRAME_BLUE;
                } else {  // Se no
                    // Si ricopia l'immagine originale
                    newFramedImageRed[i][j] = originalImageRed[i][j];
                    newFramedImageGreen[i][j] = originalImageGreen[i][j];
                    newFramedImageBlue[i][j] = originalImageBlue[i][j];
                }
            }
        }

        // Si crea la nuova ZImage e si restituisce
        return new ZImage(newFramedImageRed, newFramedImageGreen, newFramedImageBlue, row, col);
    }

    private String buildImagePath() {  // Si crea il path completo dell'immagine. N.B. Funziona solo su Windows
        return ("" + Objects.requireNonNull(ZImageDemo.class.getResource(ZImageDemo.IMAGE_PATH))).substring(6);
    }

    private boolean checkFrameZone(int r, int c, int r_tot, int c_tot) {  // Si controlla se si è nella zona cornice
        return ((r < ZImageDemo.FRAME_THICKNESS || r > r_tot - ZImageDemo.FRAME_THICKNESS) ||  // cornice alta e bassa
                (r >= ZImageDemo.FRAME_THICKNESS && r <= r_tot - ZImageDemo.FRAME_THICKNESS && // cornice laterale
                        (c < ZImageDemo.FRAME_THICKNESS || c > c_tot - ZImageDemo.FRAME_THICKNESS)));
    }

    private boolean checkStrisciaVerticale(int c, int c_tot) {
        return (c_tot / 2 + (STRISCIA_THICKNESS / 2) > c && c_tot / 2 - (STRISCIA_THICKNESS / 2) < c);
    }

    private boolean checkStrisciaOrrizontale(int r, int r_tot) {
        return (r_tot / 2 + (STRISCIA_THICKNESS / 2) > r && r_tot / 2 - (STRISCIA_THICKNESS / 2) < r);
    }

    private void display() {  // Si mostra la ZImage originale. N.B. Metodo non necessario, creato per leggibilità
        this.image.draw();
    }

    /*
     Metodo main, prende il controllo del programma
     */
    public static void main(String[] args) {
        ZImageDemo zd = new ZImageDemo();  // Si crea un oggetto ZImageDemo

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