public class ASCII {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private Forma[] forme;
    private int indiceForme;
    private char[][] mappa;

    public ASCII(int righe, int colonne, int quanteForme) {
        forme = new Forma[quanteForme];
        mappa = creaMappa(righe, colonne);
    }

    private char[][] creaMappa(int righe, int colonne) {
        char[][] mappa = new char[righe][colonne];

        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[0].length; j++) {
                mappa[i][j] = ' ';
            }
        }
        return mappa;
    }

    public void aggiungiForma(Forma f) {
        forme[indiceForme++] = f;
    }

    public void rimuoviForma(int indice) {
        if (indice > indiceForme || indice < 0) {
            System.out.println("Impossibile rimuovere");
            return;
        }

        forme[indice] = null;

        for (int i = indice; i < indiceForme - 1; i++) {
            forme[i] = forme[i + 1];
        }
        forme[indiceForme--] = null;
    }

    public void stampaMappa() {
        for (int i = 0; i < mappa.length; i++) {
            for (int j = 0; j < mappa[i].length; j++) {
                System.out.print(ANSI_GREEN + mappa[i][j] + ANSI_RESET);
            }
            System.out.println();
        }
    }

    public void disegna() {
        for (int i = 0; i < indiceForme; i++) {
            for (int j = 0; j < mappa.length; j++) {
                for (int k = 0; k < mappa[i].length; k++) {
                    if (forme[i].contiene(j, k)) {
                        mappa[j][k] = forme[i].getCarattere();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Forma r = new Rettangolo(2, 7, 3, 4, '*');
        Forma c = new Cerchio(2, 2, 3, 'O');
        ASCII a = new ASCII(25, 25, 4);
        a.aggiungiForma(c);
        a.aggiungiForma(r);
        a.disegna();
        a.stampaMappa();
    }
}