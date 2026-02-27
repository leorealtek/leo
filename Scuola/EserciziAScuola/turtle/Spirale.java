package turtle;

public class Spirale extends TurtleScreen{
    private Turtle t;
    private int lunghezza;
    private int quante = 20;

    public Spirale(int lunghezza) {
        super(800, 600);
        this.lunghezza = lunghezza;
    }

    @Override
    public void setup() {
        noLoop();
        t = createTurtle();
        int n = 0;
        for (int i = 0; i < quante; i++) {
            t.forward(lunghezza--);
            String binario = Integer.toBinaryString(n);
            int ultimo = (binario.charAt(binario.length()-1) == '0') ? 1 : 0;
            t.right((ultimo == 0) ? 90 : -180);
            n++;
        }
    }

    public static void main(String[] args) {
        Spirale s = new Spirale(50);
        s.run();
    }
    
}