package exem.model;

public class Disc extends Abstract{
    private float radius;

    public Disc(String name, Farba farba, float radius) throws Exception{
        super(name, farba);
        setRadius(radius);
    }

    public void setRadius(float radius) throws Exception {
        if(radius < 5f || radius > 500f)
            throw new Exception("Радус " + radius + " є некоректним.\nВін мє бути від 5 до 500 см");
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Disc{" +
                "radius=" + radius +
                ", name='" + name + '\'' +
                ", farba=" + farba + '}';
    }

    @Override
    public double area() {
        return 2 * Math.PI * radius * radius;
    }
}
