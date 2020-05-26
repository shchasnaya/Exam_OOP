package exem.store;

import exem.model.IFarba;

import java.util.Arrays;

public class ProductStore {
    public IFarba[] arr = new IFarba[3];
    private int count = 0;

    public IFarba[] getArr() {
        return Arrays.copyOf(arr,count);
    }

    public void add(IFarba newProduct) {
        //запобігаємо переповненню масиву
        if(arr.length == count)
            arr = Arrays.copyOf(arr, count + count/2);
        //додаємоновий елемент
        arr[count++] = newProduct;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Каталог фарби:\n");
        for(int i = 0; i < count; i++) {
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }
}
