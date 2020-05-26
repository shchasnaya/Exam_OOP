package exem.model;

import java.time.LocalDate;

public class Realization implements IFarba{
    int count;
    Farba farba;

    public Realization(int count, Farba farba) {
        this.count = count;
        this.farba = farba;
    }

    @Override
    public double calcConsumption() {
        return farba.getConsumption();
    }

    @Override
    public double calcCost() {
        return farba.getConsumption() * farba.getPrice();
    }

    @Override
    public String toString() {
        return "Realization{" +
                "count=" + count +
                ", farba=" + farba +
                '}';
    }
}
