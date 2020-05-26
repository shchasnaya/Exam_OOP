package exem.store;

import exem.model.Farba;

import javax.swing.*;
import java.io.Serializable;
import java.util.Arrays;

public class FarbaDirectory implements Serializable {
    private Farba[] arr = new Farba[3];
    private int count = 0;
    {
        try {
            initialization();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Farba[] getArr() {
        return Arrays.copyOf(arr,count);
    }

    public Farba get(String mark, String color) {
        for(int i = 0; i < count; i++) {
            if(arr[i].getMark().equals(mark)) {
                return arr[i];
            }
        }
        return null;
    }

    public boolean add(Farba newFarba) {
        if(get(newFarba.getMark(), newFarba.getColor()) != null) {
            JOptionPane.showMessageDialog(null, "Інформація про марку " + newFarba.getMark() + " з кольором " + newFarba.getColor() + " вже занесено", "Помилка", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(arr.length == count)
            arr = Arrays.copyOf(arr, count + count/2);
        arr[count++] = newFarba;
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Каталог марок:\n");
        for(int i = 0; i < count; i++) {
            sb.append(arr[i]).append("\n");
        }
        return sb.toString();
    }

    private void initialization() throws Exception {
        arr[0] = new Farba("ВД-АК", "Чорний", 0.5f,60.0f);
        arr[1] = new Farba("ХД-ВА", "Жовтий", 0.5f,60.0f);
        arr[2] = new Farba("АК-124", "Червоний", 0.5f,60.0f);
        count = 3;
    }
}
