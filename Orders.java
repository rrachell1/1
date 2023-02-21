/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.model;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import ordermanagement.list.IObject;
import ordermanagement.util.Util;
/**
 *
 * @author imnvi
 */
public class Orders implements IObject {
    
    public static final String ID_FORMAT = "Dxxx";
    private static final String ID_PATTERN = "D\\d{3}";
    private static final int ATTRIBUTE_COUNT = 6;
    
    private String id;
    private String customerId;
    private String productId;
    private int orderQuantity;
    private Date date;
    private boolean status;

    public Orders(String[] attributes) {
        setAttribute(attributes);
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

    public String getCustomerId() {
        return customerId;
    }

    public final void setCustomerId(String cusomerId) {
        if (validateCustomerId(cusomerId)) {
            this.customerId = customerId;
        }
    }

    public String getProductId() {
        return productId;
    }

    public final void setProductId(String ProductId) {
        if (validateProductId(ProductId)) {
            this.productId = productId;
        }
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Date getDate() {
        return date;
    }

    public final void setDate(Date date) {
        if (validateDate(date)) {
            this.date = date;
        }
    }

    public boolean getStatus() {
        return this.status;
    }

    public final void setStatus(boolean status) {
        this.status = status;
    }
    
    public Orders() {
    }

    public Orders(String id, String customerId, String productId, int orderQuantity, Date date, boolean status) {
        setId(id);
        setCustomerId(customerId);
        setProductId(productId);
        setOrderQuantity(orderQuantity);
        setDate(date);
        setStatus(status);
    }
    
    

    public void input() {
        System.out.println("Input order ...");
        this.id = inputId().toUpperCase();
        this.customerId = inputCustomerId();
        this.productId = inputProductId();
        this.orderQuantity = Util.inputInteger1("Please enter order's quantity (not blank or empty)", 0);
        this.date = new Date();
        
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
        sb.append(this.customerId);
        sb.append(Util.SEPARATOR);
        sb.append(this.productId);
        sb.append(Util.SEPARATOR);
        sb.append(this.orderQuantity);
        sb.append(Util.SEPARATOR);
        sb.append(this.date);
        sb.append(Util.SEPARATOR);
        sb.append(this.status);
        sb.append(Util.SEPARATOR);
        return sb.toString();
    }
    
    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Util.SEPARATOR));
        }
        return 0;
    }

    public int setAttribute(String[] attributes) {
        int idx = 0;
        if (attributes != null && attributes.length >= Orders.ATTRIBUTE_COUNT) {
            setId(attributes[idx++].trim());
            setCustomerId(attributes[idx++].trim());
            setProductId(attributes[idx++].trim());
            setStatus(Boolean.parseBoolean(attributes[idx++].trim()));
            try {
                setDate(Util.toDate(attributes[idx++].trim()));
            } catch (ParseException ex) {
                Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                setOrderQuantity(Integer.parseInt(attributes[idx++].trim()));
            } catch (NumberFormatException ex) {
                Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return idx;
    }

    public int getAttributeCount() {
        return ATTRIBUTE_COUNT;
    }
    
    private boolean validateId(String id, boolean checkExists) {
        return Util.validateString(id, Orders.ID_PATTERN, true)
                && (!checkExists || !Util.isOrdersIDExists(id));
    }
    
    private String inputId() {
        String inputId;
        do {  
            inputId = Util.inputString("Please enter the id with the pattern(" + Orders.ID_FORMAT + ")");
        } while (!validateId(inputId, true));
        return inputId;
    }
    
    private String inputCustomerId() {
        String cusId;
        do {
            cusId = Util.inputString("Please enter the customer's id with the pattern(" + Customer.ID_FORMAT + ")").trim();
        } while (!validateCustomerId(cusId));

        return cusId;
    }
    
    private String inputProductId() {
        String proId;
        do {
            proId = Util.inputString("Please enter the product's id with the pattern(" + Product.ID_FORMAT + ")").trim();
        } while (!validateCustomerId(proId));

        return proId;
    }
    
    private boolean validateCustomerId(String id) {
        return Util.isCustomerExists(id);
    }

    private boolean validateProductId(String id) {
        return Util.isProductExists(id);
    }
    
    private boolean validateDate(Date date) {
        return Util.validateDate(date, null);
    }
}
