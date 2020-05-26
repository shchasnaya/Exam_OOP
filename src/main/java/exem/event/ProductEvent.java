package exem.event;

import exem.model.IFarba;

import java.time.LocalDate;
import java.util.Date;
import java.util.EventObject;

public class ProductEvent extends EventObject {
    private IFarba product;
    LocalDate date = LocalDate.now();;

    public ProductEvent(Object source, IFarba product) {
        super(source);
        this.product = product;
    }

    public IFarba getProduct() {
        return product;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date + " "  + ": " + product;
    }

}
