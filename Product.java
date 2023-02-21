/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.model;

/**
 *
 * @author imnvi
 */
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import ordermanagement.list.IObject;
import ordermanagement.util.Util;

public class Product implements IObject {
    
    public static final String ID_FORMAT = "Pxxx";
    private static final String ID_PATTERN = "P\\d{3}";
    private static final int ATTRIBUTE_COUNT = 5;

    private String id;
    private String name;
    private String unit;
    private String origin;
    private double price;

    public Product() {
    }

    public Product(String id, String name, String unit, String origin, double price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    @Override
    public String getId() {
        return id;
    }

    public final void setId(String id) {
        if (validateId(id, false)) {
            this.id = id.toUpperCase();
        } else {
            System.out.println("Err");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void input() {
        System.out.println("Input department ...");
        if (this.id == null || this.id.isEmpty()) {
            this.id = inputId().toUpperCase();
        }
        this.name = Util.inputString("Please enter name (not blank or empty)").trim();
        this.unit = Util.inputString("Please enter unit (not blank or empty)").trim();    
        this.origin = Util.inputString("Please enter origin (not blank or empty)").trim();
        this.price = Util.inputInteger1("Please enter origin (not blank or empty)", 0);
    }
    
    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Util.SEPARATOR));
        }
        return 0;
    }
    
    public int setAttribute(String[] attributes) {
        int idx = 0;
        if (attributes != null && attributes.length >= getAttributeCount()) {
            setId(attributes[idx++].trim());
            setName(attributes[idx++].trim());
            setUnit(attributes[idx++].trim());
            setOrigin(attributes[idx++].trim());
            setPrice(Double.parseDouble(attributes[idx++].trim()));
        }
        return idx;
    }
    
    @Override
    public void output() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append(" {");
        sb.append(toString());
        sb.append("}");
        System.out.println(sb.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(Util.SEPARATOR);
        sb.append(this.name);
        sb.append(Util.SEPARATOR);
        sb.append(this.unit);
        sb.append(Util.SEPARATOR);
        sb.append(this.origin);
        sb.append(Util.SEPARATOR);
        sb.append(this.price);
        return sb.toString();
    }

    protected int getAttributeCount() {
        return ATTRIBUTE_COUNT;
    }
    
    private boolean validateId(String id, boolean checkExists) {
        return Util.validateString(id, Product.ID_PATTERN, true)
                && (!checkExists || !Util.isProductExists(id));
    }

    private String inputId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the id with the pattern(" + Product.ID_FORMAT + ")");
        } while (!validateId(inputId, true));

        return inputId;
    }

}
