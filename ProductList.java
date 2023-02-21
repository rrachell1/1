/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.list;

import java.util.Date;
import java.util.List;
import ordermanagement.model.Product;

/**
 *
 * @author imnvi
 */
public class ProductList extends ObjectList<Product> {
    
    public ProductList() {
    }

    public ProductList(String filePath) {
        super(filePath);
    }

    @Override
    public List<Product> filter(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Product> filter(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected Product parseString(String stringObject) {
        Product obj = new Product();
        obj.parseString(stringObject);
        return obj;
    }
}
