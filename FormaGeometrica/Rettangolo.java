package leo.FormaGeometrica;

public class Rettangolo extends FormaGeometrica {
    private final int base;
    private final int altezza;
    
    public Rettangolo(int base, int altezza) {
        super(2*(base+altezza), base*altezza);
        this.base = base;
        this.altezza = altezza;
    }

    public int getBase() {
        return base;
    }

    public int getAltezza() {
        return altezza;
    }

}
