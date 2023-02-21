/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.list;

import java.util.Date;
import java.util.List;
import ordermanagement.model.Customer;

/**
 *
 * @author imnvi
 */
public class CustomerList extends ObjectList<Customer> {

    public CustomerList() {
    }

    public CustomerList(String filePath) {
        super(filePath);
    }

    @Override
    public List<Customer> filter(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Customer> filter(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected Customer parseString(String stringObject) {
        Customer obj = new Customer();
        obj.parseString(stringObject);
        return obj;
    }
    
}
