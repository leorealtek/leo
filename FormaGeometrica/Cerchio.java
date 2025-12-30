package FormaGeometrica;

public class Cerchio extends FormaGeometrica {
    private final float raggio;

    public Cerchio(float raggio) {
        super(raggio*2*3.14,raggio*3.14*raggio);
        this.raggio = raggio;
    }

    public float getRaggio() {
        return raggio;
    }
    
}
