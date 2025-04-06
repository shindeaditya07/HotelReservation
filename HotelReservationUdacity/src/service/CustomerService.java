package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService INSTANCE = new CustomerService();
    private final Map<String, Customer> customers = new HashMap<>();

    private CustomerService() {}

    public static CustomerService getInstance(){
        return INSTANCE;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        if (!customers.containsKey(email.toLowerCase())) {
            customers.put(email.toLowerCase(), new Customer(email, firstName, lastName));
        } else {
            System.out.println("⚠️ Customer with this email already exists.");
        }
    }


    public Customer getCustomer(String email) {
        return customers.get(email.toLowerCase());  // Ensure email is case-insensitive
    }

    public Collection<Customer> getAllCustomers(){
        return customers.values();
    }
}
