package main.UI;

public class Customer {
    private String name;
    private String phone_number;
    private int customer_id;

    public Customer(String _name, String _phone_number, int id) {
        this.name = _name;
        this.phone_number = _phone_number;
        this.customer_id = id;
    }
    public String getName() {
        return this.name;
    }
}
