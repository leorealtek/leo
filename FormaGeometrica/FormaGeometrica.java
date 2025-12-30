package FormaGeometrica;

public class FormaGeometrica {

    protected double perimetro;
    protected double area;

    public FormaGeometrica(double perimetro, double area) {
        this.perimetro = perimetro;
        this.area = area;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public double getArea() {
        return area;
    }
}
