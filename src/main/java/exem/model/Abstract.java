package exem.model;

public abstract class Abstract implements IFarba{
    protected String name;
    protected Farba farba;

    public Abstract(String name, Farba farba) throws Exception{
        setName(name);
        this.farba = farba;
    }

    public void setName(String name) throws Exception{
        if(name.isEmpty())
            throw new Exception("Помилка при вводі\nВсі поля треба обовязково заповнити");
        this.name = name;
    }

    public abstract double area();

    @Override
    public double calcConsumption() {
        return area() * farba.getConsumption();
    }


    @Override
    public double calcCost() {
        return calcConsumption() * farba.getPrice();
    }

}
