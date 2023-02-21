/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import ordermanagement.list.IObject;
import ordermanagement.util.Util;

/**
 *
 * @author imnvi
 */
public class Customer implements IObject {
    
    public static final String ID_FORMAT = "Cxxx";
    private static final String ID_PATTERN = "C\\d{3}";
    private static final int ATTRIBUTE_COUNT = 4;
    private static final int PHONE_MIN_LENGTH = 10;
    private static final int PHONE_MAX_LENGTH = 12;
    
    private String id;
    private String name;
    private String address;
    private String phone;
    
    public Customer() {
        this.id = "";
        this.name = "";
        this.address = "";
        this.phone = "";
    }
    
    public Customer(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    
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
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public final void setPhone(String phone) {
        if (validPhone(phone)) {
            this.phone = phone;
        }
    }
    
    public void input() {
        System.out.println("Input customer ...");
        if (this.id == null || this.id.isEmpty()) {
            this.id = inputId().toUpperCase();
        }
        this.name = Util.inputString("Please enter name (not blank or empty)").trim();
        this.address = Util.inputString("Please enter address (not blank or empty)").trim();
        while (true) {
        String phone = Util.inputString("Please enter phone number (not blank or empty)");
        if (validPhone(phone)) {
            this.phone = phone;
            break;
        } else {
            System.out.println("Invalid phone number. Please try again.");
        }
    }
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
        sb.append(this.address);
        sb.append(Util.SEPARATOR);
        sb.append(this.phone);
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
        if (attributes != null && attributes.length >= getAttributeCount()) {
            setId(attributes[idx++].trim());
            setName(attributes[idx++].trim());
            setAddress(attributes[idx++].trim());
            setPhone(attributes[idx++].trim());
        }
        return idx;
    }
    
    protected int getAttributeCount() {
        return ATTRIBUTE_COUNT;
    }
    
//    public static boolean isValid(Customer customer) {
//        if (customer == null) {
//            return false;
//        }
//        if (customer.getId() == null || customer.getId().isEmpty()) {
//            return false;
//        }
//        if (customer.getName() == null || customer.getAddress() == null || customer.getPhone() == null) {
//            return false;
//        }
//        if (!isValidPhone(customer.getPhone())) {
//            return false;
//        }
//        return true;
//    }
    
    public int compareTo(Customer o) {
        return this.id.compareTo(o.id);
    }
    
    protected String getIdFormatString() {
        return Customer.ID_FORMAT;
    }
    
    protected boolean validateId(String id, boolean checkExists) {
        return Util.validateString(id, Customer.ID_PATTERN, true)
                && (!checkExists || !Util.isCustomerExists(id));
    }

    private String inputId() {
        String inputId;
        do {
            inputId = Util.inputString("Please enter the id with the pattern(" + getIdFormatString() + ")");
        } while (!validateId(inputId, true));
        return inputId;
    }
    
    public static boolean validPhone(String phone) {
        return phone != null && phone.length() >= PHONE_MIN_LENGTH && phone.length() <= PHONE_MAX_LENGTH;
    }
}
