public class Cerchio implements Forma {
    private final double centroX;
    private final double centroY;
    private final double raggio;
    private final char carattere;

    public Cerchio(double centroX, double centroY, double raggio, char carattere) {
        this.centroX = centroX;
        this.centroY = centroY;
        this.raggio = raggio;
        this.carattere = carattere;
    }

    @Override
    public boolean contiene(double x, double y) {
        double distanza = Math.pow(x - centroX, 2) + Math.pow(y - centroY, 2);
        return distanza <= raggio;
    }
    
    @Override
    public char getCarattere() {
        return carattere;
    }

}