/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordermanagement.main;

import ordermanagement.model.Customer;
import ordermanagement.model.Product;
import ordermanagement.model.Orders;
import ordermanagement.service.UserManagetment;
import java.util.Date;
import java.util.Scanner;
import ordermanagement.list.CustomerList;
import ordermanagement.list.ProductList;
import ordermanagement.list.OrdersList;
import ordermanagement.util.Util;
/**
 *
 * @author imnvi
 */
public class OrdersManagement {
    
    private static final OrdersManagement instance = new OrdersManagement();

    private final String customerFilePath;
    private final String productFilePath;
    private final String ordersFilePath;

    private final CustomerList customerList;
    private final ProductList productList;
    private final OrdersList ordersList;
    
    public static OrdersManagement getInstance() {
        return instance;
    }

    public CustomerList getCustomerList() {
        return customerList;
    }

    public ProductList getProductList() {
        return productList;
    }

    public OrdersList getOrdersList() {
        return ordersList;
    }
    
    private OrdersManagement() {
        customerFilePath = "Customer.dat";
        productFilePath = "Product.dat";
        ordersFilePath = "Orders.dat";

        customerList = new CustomerList(customerFilePath);
        productList = new ProductList(productFilePath);
        ordersList = new OrdersList(ordersFilePath);
    }
    
    private void loadData() {
        this.customerList.load();
        this.productList.load();
        this.ordersList.load();
    }
    
    private void showAllProduct() {
        System.out.println("Product list:");
        productList.show();
    }

    private void showAllCustomer() {
        System.out.println("Customer list:");
        customerList.show();
    }

    private void filterCustomerById() {
        System.out.println("filterCustomerById ...");
        String search = Util.inputString("Not blank or empty.");
        boolean check = false;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().toLowerCase().contains(search.toLowerCase())) {
                check = true;
            }
        }
        if (check == false) {
            System.out.println("This customer does not exist");
            return;
        }
        System.out.println("Result searching by: " + search);
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().toLowerCase().contains(search.toLowerCase())) {
                System.out.println(customerList.get(i).toString());
            }
        }
    }

    private void addNewCustomer() {
        // check user role ROLE_ADMIN
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.ADMIN) == true) {
            Customer cus = new Customer();
            cus.input();
            // Check if the customer ID already exists in the database
        if (Util.isCustomerExists(cus.getId())) {
            System.out.println("Error: Customer ID already exists.");
            return;
        }
        if (cus.getName() == null || cus.getAddress() == null || cus.getPhone() == null) {
            System.out.println("Error: Customer's Name, Address, and Phone number fields cannot be null.");
            return;
        }
        if (!Customer.validPhone(cus.getPhone())) {
            System.out.println("Error: Customer's Phone number should have length from 10 to 12 characters.");
            return;
        }
        customerList.add(cus);
        customerList.save();
    } else {
        System.out.println("Error: User is not authorized to add a new customer.");
    }
    }

    private void updateCustomer() {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter customer ID to update: ");
        String customerId = sc.nextLine();

        // Find the customer by ID
        Customer cus = null;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().equals(customerId)) {
                cus = customerList.get(i);
                break;
            }
        }

        if (cus == null) {
            System.out.println("Error: Customer does not exist.");
            return;
        }

        System.out.print("Enter customer name (leave blank to keep current value): ");
        String name = sc.nextLine().trim();
        if (!name.isEmpty()) {
            cus.setName(name);
        }

        System.out.print("Enter customer address (leave blank to keep current value): ");
        String address = sc.nextLine().trim();
        if (!address.isEmpty()) {
            cus.setAddress(address);
        }

        System.out.print("Enter customer phone number (leave blank to keep current value): ");
        String phoneNumber = sc.nextLine().trim();
        if (!phoneNumber.isEmpty()) {
            // Check if phone number is valid
            if (Customer.validPhone(phoneNumber)) {
                cus.setPhone(phoneNumber);
            } else {
                System.out.println("Error: Invalid phone number.");
                return;
            }
        }

        // Save updated customer information and show success message
        customerList.save();
        System.out.println("Customer information updated successfully.");
    }
    

    private void deleteCustomer() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the customer ID to delete: ");
    String customerId = scanner.nextLine();
    
    Customer cus = null;
        for (int i = 0; i < customerList.size(); i++) {
            if (customerList.get(i).getId().equals(customerId)) {
                cus = customerList.get(i);
                break;
            }
        }

        if (cus == null) {
            System.out.println("Error: Customer does not exist.");
            return;
        }
    
    customerList.remove(cus);
    System.out.println("Customer deleted successfully.");
    }
    
    private void showAllOrders() {
        System.out.println("Orders list:");
        ordersList.show();
    }

    private void addNewOrders() {
        // check user role ROLE_USER
        if (UserManagetment.getInstance().getCurrentUser().checkRole(UserRole.USER) == true) {
            Orders ord = new Orders();
            ord.input();
            if (!Util.isCustomerExists(ord.getCustomerId())) {
                Customer p = new Customer();
                p.setId(ord.getCustomerId());
                p.input();
//                ord.setPatientId(p.getId());
                customerList.add(p);
                customerList.save();
            }
            ordersList.add(ord);
            ordersList.save();
        } else {
            System.out.println("Eror: ...");
        }
    }
    
    private void updateOrders() {
        System.out.println("updateOrders ...");
    }

    private void deleteOrders() {
        System.out.println("deleteOrders ...");
    }
    
    private void run() {
        Menu menu = new Menu();
        int option = Integer.MAX_VALUE;
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case PRODUCT_SHOW_ALL :
                    showAllProduct();
                    break;
                case CUSTOMER_SHOW_ALL :
                    showAllCustomer();
                    break;
                case CUSTOMER_FILTER_BY_ID :
                    filterCustomerById();
                    break;
                case CUSTOMER_ADD_NEW :
                    addNewCustomer();
                    break;
                case CUSTOMER_UPDATE :
                    updateCustomer();
                    break;
                case CUSTOMER_DELETE :
                    deleteCustomer();
                    break;
                case ORDERS_SHOW_ALL :
                    showAllOrders();
                    break;
                case ORDERS_ADD_NEW :
                    addNewOrders();
                    break;
                case ORDERS_UPDATE :
                    updateOrders();
                    break;
                case ORDERS_DELETE :
                    deleteOrders();
                    break;
                case EXIT :
                    System.out.println("Exited!");
                    break;
                default :
                    System.out.println("???");
                }           
        } while (userChoice != MenuItem.EXIT);
    }
    
    private void start() {
        System.out.println("Orders management");
        if (UserManagetment.getInstance().login()) {
            UserManagetment.getInstance().getCurrentUser().output();
            loadData();
            run();
        } else {
            System.out.println("Login failed!");
        }
    }
    
    public static void main(String[] args) {
        new OrdersManagement().start();
    }
    
}
