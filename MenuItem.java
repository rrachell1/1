/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.main;

/**
 *
 * @author imnvi
 */
public enum MenuItem {
    
    EXIT("Exit", UserRole.USER),
    BACK("Back", UserRole.USER),
       
    CUSTOMER("Customer", UserRole.USER),
    CUSTOMER_SHOW_ALL("Show all", UserRole.USER),
    CUSTOMER_FILTER_BY_ID("Filter by id", UserRole.USER),
    CUSTOMER_ADD_NEW("Add new", UserRole.ADMIN),
    CUSTOMER_UPDATE("Update", UserRole.ADMIN),
    CUSTOMER_DELETE("Delete", UserRole.ADMIN),
    
    PRODUCT("Product", UserRole.USER),
    PRODUCT_SHOW_ALL("Show all", UserRole.USER),
    PRODUCT_ADD_NEW("Add new", UserRole.ADMIN),
    PRODUCT_UPDATE("Update", UserRole.ADMIN),
    PRODUCT_DELETE("Delete", UserRole.ADMIN),
    
    ORDERS("Orders", UserRole.USER),
    ORDERS_SHOW_ALL("Show all", UserRole.USER),
    ORDERS_ADD_NEW("Add new", UserRole.ADMIN),
    ORDERS_UPDATE("Update", UserRole.ADMIN),
    ORDERS_DELETE("Delete", UserRole.ADMIN);
    
    private final UserRole role;
    private final String label;

    public UserRole getRole() {
        return role;
    }

    public String getLabel() {
        return label;
    }

    private MenuItem(String label, UserRole role) {
        this.role = role;
        this.label = label;
    }
}
