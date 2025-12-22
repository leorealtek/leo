package leo.FormaGeometrica;

public class Rettangolo extends FormaGeometrica {
    private final int base;
    private final int altezza;
    
    public Rettangolo(int base, int altezza) {
        this.base = base;
        this.altezza = altezza;
    }

    public int getBase() {
        return base;
    }

    public int getAltezza() {
        return altezza;
    }

    @Override
    public int getArea() {
        return 0;
    }

    @Override
    public int getPerimetro() {
        return 0;
    }
    
}
