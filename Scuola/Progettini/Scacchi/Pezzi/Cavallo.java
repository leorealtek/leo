package Scuola.Progettini.Scacchi.Pezzi;

import Scuola.Progettini.Scacchi.Util.Casella;
import Scuola.Progettini.Scacchi.Util.Pezzo;

public class Cavallo extends Pezzo {

    public Cavallo(char nome, int posX, int posY) {
        super(nome, posX, posY);
        //TODO Auto-generated constructor stub
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