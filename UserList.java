/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.list;

import java.util.Date;
import java.util.List;
import ordermanagement.model.User;

/**
 *
 * @author imnvi
 */
public class UserList extends ObjectList<User> {

    @Override
    public List<User> filter(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<User> filter(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected User parseString(String stringObject) {
        User u = new User();
        u.parseString(stringObject);
        return u;
    }
    
}
