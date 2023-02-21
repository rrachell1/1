 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.main;

import ordermanagement.model.User;
import ordermanagement.service.UserManagetment;
import ordermanagement.util.Util;
/**
 *
 * @author imnvi
 */
public class Menu {
    
    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.CUSTOMER,
        MenuItem.PRODUCT,
        MenuItem.ORDERS
    };
    
    private final MenuItem[] customerOptions = {
        MenuItem.BACK,
        MenuItem.CUSTOMER_SHOW_ALL,
        MenuItem.CUSTOMER_FILTER_BY_ID,
        MenuItem.CUSTOMER_ADD_NEW,
        MenuItem.CUSTOMER_UPDATE,
        MenuItem.CUSTOMER_DELETE
    };
    
    private final MenuItem[] productOptions = {
        MenuItem.BACK,
        MenuItem.PRODUCT_SHOW_ALL,
        MenuItem.PRODUCT_ADD_NEW,
        MenuItem.PRODUCT_UPDATE,
        MenuItem.PRODUCT_DELETE
    };
    
    private final MenuItem[] ordersOptions = {
        MenuItem.BACK,
        MenuItem.ORDERS_SHOW_ALL,
        MenuItem.ORDERS_ADD_NEW,
        MenuItem.ORDERS_UPDATE,
        MenuItem.ORDERS_DELETE
    };
    
    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }
    
    public MenuItem getUserChoice() {
        do {
            if (subOption == MenuItem.BACK) {
                primaryOption = getChoice(null);
            }
            if (primaryOption != MenuItem.EXIT) {
                subOption = getChoice(primaryOption);
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }
    
    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "Orders management:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);
        User currentUser = UserManagetment.getInstance().getCurrentUser();
        for (MenuItem item : optionList) {
            if (currentUser.checkRole(item.getRole())) {
                if (choice == 0) {
                    return item;
                }
                choice--;
            }
        }
        return optionList[0];
    }
    
    private int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
        User currentUser = UserManagetment.getInstance().getCurrentUser();
        for (int i = 1; i < optionList.length; i++) {
            if (currentUser.checkRole(optionList[i].getRole())) {
                System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
                numItems++;
            }
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;
    }
    
    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            switch (option) {
                case CUSTOMER:
                    optionList = customerOptions;
                case PRODUCT:
                    optionList = productOptions;
                case ORDERS:
                    optionList = ordersOptions;
                default:
                    optionList = primaryOptions;
                    break;
            };
        }
        return optionList;
    }
}
