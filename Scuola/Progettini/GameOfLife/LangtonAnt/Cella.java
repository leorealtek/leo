package Scuola.Progettini.GameOfLife.LangtonAnt;

public enum Cella {
    antViva, antMorta, viva, morta;

    public Cella cambiaCella() {
        switch (this) {
            case antMorta:
                return viva;   
            case antViva:
                return morta;
            case viva:
                return morta;         
            default:
                return viva;
        }
    }
}