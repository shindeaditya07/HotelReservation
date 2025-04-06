package model;

public class Driver {
    public static void main(String[] args) {
        try{
            Customer customer1 = new Customer("Aditya", "Shinde", "shindeaditya07@gmail.com");
            System.out.println(customer1);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try{
            Customer customer2 = new Customer("Aditya", "Shinde", "shindeaditya07@");
            System.out.println(customer2);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
