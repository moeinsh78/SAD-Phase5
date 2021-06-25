package main.logic;

import main.UI.Customer;

import java.util.Date;

public class Request {
    protected int request_id;
    protected Date request_date;
    protected OriginPlace origin_place;
    protected Customer customer;
    protected Receipt receipt;

    public Place get_origin() {
        return this.origin_place;
    }

    public Customer get_customer() {
        return this.customer;
    }

    public int getRequest_id() {
        return this.request_id;
    }

    public void set_origin_info(String address, String floor, String type) {
        this.origin_place.set_address(address);
        int floor_num = 0;
        try { floor_num = Integer.parseInt(floor); }
        catch (NumberFormatException ignored) {}
        this.origin_place.set_floor(floor_num);
        this.origin_place.set_type(type);
    }
}
