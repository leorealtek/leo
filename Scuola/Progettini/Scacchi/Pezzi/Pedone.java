package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Pedone extends Pezzo {

    private boolean primaMossa;

    public Pedone(char nome, int posX, int posY) {
        super(nome, posX, posY);
        primaMossa = true;
    }

    @Override
    protected void muovi(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'muovi'");
    }

    @Override
    protected String mossePossibili(Casella[][] mappa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mossePossibili'");
    }

    public boolean isPrimaMossa() {
        return primaMossa;
    }
    
}