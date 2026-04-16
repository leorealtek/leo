package Scuola.TPSIT.ZImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * La classe <i>ZImage</i> &egrave; utilizzata per manipolare e visualizzare
 * un'immagine a partire da:
 * <ul>
 *     <li>Le sue dimensioni (larghezza x altezza)</li>
 *     <li>Il canale di colore <strong>rosso</strong></li>
 *     <li>Il canale di colore <strong>verde</strong></li>
 *     <li>Il canale di colore <strong>blu</strong></li>
 * </ul>
 *
 * <p>
 * L'immagine può essere caricata da un file (preferibilmente in formato JPEG)
 * oppure generata a partire dai tre canali di colore, specificando una
 * dimensione.
 *
 * Le <i>ZImage</i> possono gestire immagini con dimensioni minime di
 * <pre>1 x 1</pre> fino a <pre>1024 x 768</pre>.<br />
 * Si prega di notare, comunque, che immagini troppo piccole potrebbero essere
 * visualizzate in modo anomalo all'interno della finestra generata e,
 * pertanto, si consiglia di non scendere sotto la dimensione di 100 x 100
 * pixel.
 * <p>
 * Le immagini gestite attraverso una <i>ZImage</i> non possono essere
 * modificate ed eventuali manipolazioni devono essere fatte attraverso
 * la generazione di una nuova <i>ZImage</i>

 *
 * @author Prof. A. Zennaro - Istituto Tecnico Statale "C. Zuccante" di Venezia
 * @version 1.0
 */
public final class ZImage {

    /*
     Una ZImage è gestita attraverso un oggetto interno chiamato ImageRepresentation. Attraverso questo tipo
     di dato si possono ottenere i dati di un'immagine usando le sue dimensioni e i canali di colore.
     Da notare che un'immagine rappresentata in questo modo è oggetto finale, quindi eventuali manipolazioni devono
     essere fatte generando nuove immagini e non modificando quella corrente.
     */
    private static class ImageRepresentation {
        private final int[][] red, green, blue;  // I tre canali per i colori primari della sintesi addititva
        private final int width;  // Larghezza (colonne)
        private final int height;  // Altezza (righe)

        public ImageRepresentation(int w, int h, int[][] rgb) {  // Costruttore di ImageRepresentation
            this.width = w;  // Si configura la larghezza
            this.height = h;  // e l'altezza
            this.red = new int[this.height][this.width];  // Il canale del rosso
            this.green = new int[this.height][this.width];  // Il canale del verde
            this.blue = new int[this.height][this.width];  // Il canale del blu

            for (int row = 0; row < this.height; row++){  // Per ognuna delle righe
                for (int col = 0; col < this.width; col++) {  // Per ognuna delle colonne
                    this.red[row][col] = (rgb[row][col] & 0xff0000) >> 16;  // Estraggo il rosso
                    this.green[row][col] = (rgb[row][col] & 0xff00) >> 8;  // Estraggo il verde
                    this.blue[row][col] = rgb[row][col] & 0xff;  // Estraggo il blu
                }
            }
        }

        public int getWidth() {  // Restituisco la larghezza
            return width;
        }

        public int getHeight() {  // Restituisco l'altezza
            return height;
        }

        public int[][] getRed() {  // Restituisco il canale rosso
            return this.red;
        }

        public int[][] getGreen() {  // Restituisco il canale verde
            return this.green;
        }

        public int[][] getBlue() {  // Restituisco il canale blu
            return this.blue;
        }
    }

    /*
     La classe ImageDisplayer è usata per visualizzare una ZImage all'interno di un JFrame. Poiché una ZImage
     è gestita come una matrice di punti da generare sulla base di tre matrici rappresentanti i tre canali di
     colore della sintesi additiva RGB, per disegnare l'immagine si configura ogni singolo pixel.
     La classe estende JPanel, quindi è un pannello sul quale viene ridefinito il metodo di disegno per poter
     mettere in output l'immagine.
     */
    private static class ImageDisplayer extends JPanel {
        private final int[] dimension;  // Array di due elementi con le dimensioni dell'immagine
        private final int[][] red, green, blue;  // I tre canali dei colori

        public ImageDisplayer(ZImage zi) {  // Il costruttore riceve una ZImage
            super();  // Si inizializza il pannello
            this.dimension = zi.getImageWidthAndHeight();  // Ottengo le dimensioni dell'immagine
            this.red = zi.getImageRedChannel();  // Ottengo il canale rosso
            this.green = zi.getImageGreenChannel();  // Ottengo il canale verde
            this.blue = zi.getImageBlueChannel();  // Ottengo il canale blu
            JFrame jf = this.setFrame();  // Creo il frame che conterrà l'immagine
            this.setBounds(0,0,this.dimension[1], this.dimension[0]);  // Dimensioni e posizione del JPanel
            jf.add(this);  // Aggiungo il pannello al JFrame (con i bounds fissati all'istruzione precedente)
            jf.setVisible(true);  // Mostro il frame
        }

        @Override
        public void paint(Graphics g) {  // Ridefinizione del metodo paint. Qui faccio in modo di disegnare l'immagine
            super.paint(g);  // Chiamo il paint della classe JPanel
            for (int i = 0; i < this.dimension[0]; i++) {  // Per ogni riga
                for (int j = 0; j < this.dimension[1]; j++) {  // Per ogni colonna
                    g.setColor(new Color(this.red[i][j], this.green[i][j], this.blue[i][j]));  // Configuro il colore
                    g.drawLine(j,i,j,i);  // Disegno un singolo punto
                }
            }
        }

        private JFrame setFrame() {  // Creo e configuro il JFrame che contiene l'immagine
            JFrame container = new JFrame();  // Creo il frame
            container.setSize(this.dimension[1] + 25,this.dimension[0] + 45);  // Configuro le dimensioni
            container.setResizable(false);  // Non lo rendo ridimensionabile
            container.setLocation(100,100);  // Il frame appare sempre alla posizione (100;100) dello schermo
            container.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  // Il programma termina alla chiusura
            container.setLayout(null);  // Non uso nessun LayoutManager
            return container;  // Restituisco il frame appena creato
        }
    }

    private final boolean loaded;  // L'immagine è stata caricata?
    private ImageRepresentation image = null;  // La rappresentazione dell'immagine

    /**
     * Costruttore di una ZImage creata a partire da un'immagine salvata su
     * disco.
     *
     * @param imageFile String con il percorso del file immagine da caricare
     */
    public ZImage(String imageFile) {
        BufferedImage img = this.loadImage(imageFile);  // Carico l'immagine usando una BufferedImage
        if (img == null) {  // Se non è stata caricata
            this.loaded = false;  // Configuro il flag a false
            return;  // E termino
        }

        // Se arrivo qui, l'immagine esiste
        int width = img.getWidth(), height = img.getHeight();  // Carico le dimensioni dell'immagine
        if (!this.chechWidth(width) || !this.checkHeight(height)) {  // Controllo che siano nei limiti di dimensione
            this.loaded = false;  // Se non lo sono, non carico l'immagine
            System.err.println("Dimensioni dell'immagine non compatibili con una ZImage. Limiti 1x1 => 1024x768.");
            return;  // e termino
        }

        // Se arrivo qui, l'immagine ha le dimensioni corrette
        int[][] pixels = this.getImageColoredPixels(img, width, height);  // Ottengo le informazioni sul colore
        this.image = new ImageRepresentation(width,height,pixels);  // Genero una ImageRepresentation
        this.loaded = true;  // Ora tutto è finalmente caricato!
    }

    /**
     * Costruttore di una ZImage creata a partire dalle sue dimensioni e con
     * le informazioni relative al colore, separate per canale (Rosso, Verde
     * e Blu).
     *
     * @param red Array bidimensionale di int: componente rossa di ogni pixel
     * @param green Array bidimensionale di int: componente verde di ogni pixel
     * @param blue Array bidimensionale di int: componente blu per di pixel
     * @param rows  Le righe che compongono l'immagine (la sua altezza)
     * @param cols Le colonne che compongono l'immagine (la sua larghezza)
     */
    public ZImage(int[][] red, int[][] green, int[][] blue, int rows, int cols) {
        if (!this.chechWidth(cols) || !this.checkHeight(rows)) {  // Controllo le dimensioni e se sono errate fallisco
            this.loaded = false;
            System.err.println("Dimensioni dell'immagine non compatibili con una ZImage. Limiti 1x1 => 1024x768.");
            return;
        }

        int[][] rgb = new int[rows][cols];  // Matrice per le informazioni di colore combinate
        try {  // Necessario per gestire problemi di dimensionamento di immagine e matrici
            for (int i = 0; i < rows; i++) {  // Per ogni riga
                for (int j = 0; j < cols; j++) {  // Per ogni colonna
                    /*
                     ATTENZIONE: Questo sistema è estremamente inefficiente: a partire dalle informazioni dei colori
                     sui tre canali RGB separati, si genera un'informazione complessiva che poi, all'interno
                     del costruttore di ImageRepresentation, verrà di nuovo separata nelle tre componenti. Questo
                     genera lo spreco di svariati cicli di clock per l'esecuzione degli shift e dei mascheramenti.
                     Trattandosi di una libreria didattica, tuttavia, si è scelto di prediligere la semplicità
                     piuttosto che l'efficienza.
                     */
                    rgb[i][j] = (red[i][j] << 16) + (green[i][j] << 8) + blue[i][j];  // Creo le informazioni di colore
                }
            }
        } catch (IndexOutOfBoundsException e) {  // Se le dimensioni non tornano...
            System.err.println("Qualcosa è andato storto con le dimensioni dell'immagine");
            System.exit(-1);  // Interrompo il programma
        }

        this.image = new ImageRepresentation(cols, rows, rgb);  // Genero una ImageRepresentation
        this.loaded = true;  // Ora tutto è finalmente caricato
    }

    /**
     * Metodo usato per controllare se la ZImage corrente è stata
     * effettivamente caricata.
     *
     * @return true se l'immagine è caricata, altrimenti false
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Metodo usato per ottenere i valori del canale <strong>rosso</strong>
     * di ogni pixel che compone l'immagine. La scansione è riga x colonna
     * e, quindi, altezza x larghezza dell'immagine.
     *
     * @return un int[][] con i valori del canale rosso per ogni pixel
     */
    public int[][] getImageRedChannel() {
        return this.image.getRed();
    }

    /**
     * Metodo usato per ottenere i valori del canale <strong>verde</strong>
     * di ogni pixel che compone l'immagine. La scansione è riga x colonna
     * e, quindi, altezza x larghezza dell'immagine.
     *
     * @return un int[][] con i valori del canale verde per ogni pixel
     */
    public int[][] getImageGreenChannel() {
        return this.image.getGreen();
    }

    /**
     * Metodo usato per ottenere i valori del canale <strong>blu</strong>
     * di ogni pixel che compone l'immagine. La scansione è riga x colonna
     * e, quindi, altezza x larghezza dell'immagine.
     *
     * @return un int[][] con i valori del canale blu per ogni pixel
     */
    public int[][] getImageBlueChannel() {
        return this.image.getBlue();
    }

    /**
     * Metodo usato per ottenere le dimensioni dell'immagine rappresentata
     * dalla ZImage. <br />
     * L'array restituito riporta come primo elemento l'altezza (le righe)
     * dell'immagine e come secondo elemento la larghezza (le colonne).<br />
     * Questo ordine è lo stesso utilizzato nelle matrici dei canali colore
     * che scansionano riga x colonna.
     *
     * @return un int[] con le dimensioni dell'immagine
     */
    public int[] getImageWidthAndHeight() {
        return new int[] {this.image.getHeight(), this.image.getWidth()};
    }

    /**
     * Questo metodo è usato per la visualizzazione a schermo della ZImage
     * corrente.
     */
    public void draw() {
        new ImageDisplayer(this);
    }

    private boolean chechWidth(int w) {  // Controllo della larghezza
        return (w > 0 && w <= 1024);
    }

    private boolean checkHeight(int h) {  // Controllo dell'altezza
        return (h > 0 && h <= 768);
    }

    private BufferedImage loadImage(String f) {  // Si carica l'immagine attraverso una BufferedImage
        BufferedImage img = null;  // Inizialmente la BufferedImage è null
        try {  // Provo a caricare il file
            img = ImageIO.read(new File(f));
        } catch (IOException e) {  // Se qualcosa fallisce si dà l'errore
            System.err.println(f + ": l'immagine non esiste.");
        }
        return img;  // Si restituisce l'immagine
    }

    private int[][] getImageColoredPixels(BufferedImage i, int width, int height) {  // Si estraggono i colori pixel per pixel
        int[][] rasterImage = new int[height][width];  // Si inizializza la matrice che conterrà tutti i valori di tutti i pixel

        for (int row = 0; row < height; row++) {  // Per ogni riga
            for (int col = 0; col < width; col++) {  // Per ogni colonna
                rasterImage[row][col] = i.getRGB(col, row);  // Si ottiene il valore RGB
            }
        }

        return rasterImage;  // Si ritorna la matrice così ottenuta.
    }

}
