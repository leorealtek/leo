package turtle;

public class Poligoni extends TurtleScreen {
    private Turtle t;
    private int quantiLati;
    private int quantiPol;

    public Poligoni(int quantiLati, int quantiPol) {
        super(800,600);
        this.quantiLati = quantiLati;
        this.quantiPol = quantiPol;
    }

    @Override
    public void setup() {
        noLoop();
        t = createTurtle();
        double angolo = 360 / quantiLati;
        double avanti = 360 / quantiLati;
        double k = avanti;

        for (int i = 0; i < quantiPol; i++) {
            double apotema = avanti / (2 * Math.sin(Math.PI / quantiLati)) * Math.cos(Math.PI / quantiLati);

            for (int j = 0; j < quantiLati; j++) {
                t.forward(avanti);
                t.left(angolo);
            }
            t.penUp();
            t.goTo(0, 0);
            t.goTo(-avanti / 2, -apotema);
            avanti += k;
            t.penDown();
        }
        
        t.hideTurtle();
    }

    public static void main(String[] args) {
        Poligoni p = new Poligoni(8, 4);
        p.run();
    }
}
