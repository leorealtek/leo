package Game;

public class Armory {
    private static final Weapon mani = new Weapon("Mani", 2, Integer.MAX_VALUE);
    private static final Weapon spadaDiLegno = new Weapon("Spada di legno", 5, 10);
    private static final Weapon spadaDiFerro = new Weapon("Spada di ferro", 8, 17);
    private static final Weapon spadaLaser = new Weapon("Spada laser", 15, 35);

    public static Weapon getMani() {
        return mani;
    }
    public static Weapon getSpadaDiLegno() {
        return spadaDiLegno;
    }
    public static Weapon getSpadaDiFerro() {
        return spadaDiFerro;
    }
    public static Weapon getSpadaLaser() {
        return spadaLaser;
    }
    
}
