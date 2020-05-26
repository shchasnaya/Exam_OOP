package exem.event;

import java.util.EventListener;

public interface IProductListener extends EventListener {
    public void onProductEvent(ProductEvent e);
}
