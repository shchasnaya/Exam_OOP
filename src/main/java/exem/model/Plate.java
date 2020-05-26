package exem.model;

public class Plate extends Abstract{
    private float length;
    private float width;

    public Plate(String name, Farba farba, float length, float width) throws Exception {
        super(name, farba);
        setLength(length);
        setWeight(width);
    }

    public void setLength(float length) throws Exception {
        if(length < 5f || length > 500f)
            throw new Exception("Дожина " + length + " є некоректною.\nВона має бути від 5 до 500 см");
        this.length = length;
    }

    public void setWeight(float width) throws Exception {
        if(width < 5f || width > 500f)
            throw new Exception("Ширина " + width + " є некоректною.\nВона має бути від 5 до 500 см");
        this.width = width;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }

    @Override
    public double area() {
        return  2 * width * length;
    }

}
