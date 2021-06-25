package main.logic;

public class Place {
    private String address = "";
    private int floor;
    private String type = "";
    public void set_address(String Address) {
        this.address = Address;
    }
    public void set_floor(int Floor) {
        this.floor = Floor;
    }
    public void set_type(String Type) {
        this.type = Type;
    }
    public String get_address() {
        return this.address;
    }

}