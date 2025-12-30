package FormaGeometrica;

public class Main {
    public static void main(String[] args) {
        float sommaPerimetro = 0;
        float sommaArea = 0;
        Cerchio c = new Cerchio(5);
        Rettangolo r = new Rettangolo(5,5);
        Triangolo t = new Triangolo(5,5,5);
        FormaGeometrica[] forme = {c,r,t};
        
        for (FormaGeometrica formaGeometrica : forme) {
            sommaPerimetro += formaGeometrica.getPerimetro();
            sommaArea += formaGeometrica.getArea();
        }
        System.out.println(sommaPerimetro);
        System.out.println(sommaArea);
        
    }
}
