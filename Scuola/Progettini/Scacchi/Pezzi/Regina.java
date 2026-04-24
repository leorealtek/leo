package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Regina extends Pezzo {

    public Regina(char nome, int posX, int posY) {
        super(nome, posX, posY);
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
    
}