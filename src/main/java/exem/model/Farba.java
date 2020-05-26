package exem.model;

public class Farba {
    private String mark;
    private String color;
    private float consumption;
    private float price;

    public Farba(String mark, String color, float consumption, float price) throws Exception{
        setMark(mark);
        setColor(color);
        this.consumption = consumption;
        setPrice(price);
    }

    @Override
    public String toString() {
        return mark + " (" + color + ")";
    }

    public String getMark() {
        return mark;
    }

    public String getColor() {
        return color;
    }

    public void setPrice(float price) throws Exception{
        if(price < 1.0f || price > 500f)
            throw new Exception("Ціна " + price + " є некоректною.\nВона мє бути від 1 до 500 грн");
        this.price = price;
    }

    public void setMark(String mark) throws Exception{
        if(mark.isEmpty())
            throw new Exception("Помилка при вводі\nВсі поля треба обовязково заповнити");
        this.mark = mark;
    }

    public void setColor(String color) throws Exception{
        if(color.isEmpty())
            throw new Exception("Помилка при вводі\nВсі поля треба обовязково заповнити");
        this.color = color;
    }

    public float getConsumption() {
        return consumption;
    }

    public float getPrice() {
        return price;
    }
}
