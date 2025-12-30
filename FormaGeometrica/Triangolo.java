package FormaGeometrica;

public class Triangolo extends FormaGeometrica {

    private final int lato1;
    private final int lato2;
    private final int lato3;

    public Triangolo(int lato1, int lato2, int lato3) {
        super(lato1 + lato2 + lato3, lato1 * lato3 / 2);
        this.lato1 = lato1;
        this.lato2 = lato2;
        this.lato3 = lato3;
    }

    public int getLato1() {
        return lato1;
    }

    public int getLato2() {
        return lato2;
    }

    public int getLato3() {
        return lato3;
    }

}
