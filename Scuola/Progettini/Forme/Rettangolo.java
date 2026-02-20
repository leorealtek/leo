public class Rettangolo implements Forma {
    private final double puntoX;
    private final double puntoY;
    private final double base;
    private final double altezza;
    private final char carattere;

    public Rettangolo(double puntoX, double puntoY, double base, double altezza, char carattere) {
        this.puntoX = puntoX;
        this.puntoY = puntoY;
        this.altezza = base - 1;
        this.base = altezza - 1;
        this.carattere = carattere;
    }

    @Override
    public boolean contiene(double x, double y) {
        boolean contLarghezza, contAltezza;
        contLarghezza = (x >= puntoX) && (x <= (puntoX + base));
        contAltezza = (y >= puntoY) && (y <= (puntoY + altezza));
        return contAltezza && contLarghezza;
    }

    @Override
    public char getCarattere() {
        return carattere;
    }

}