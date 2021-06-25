package main.logic;

import main.UI.Customer;

import java.util.ArrayList;
import java.util.Date;

public class TransportationRequest extends Request {
    private ArrayList<Truck> trucks = new ArrayList<>();
    private String transportation_date = "";
    private boolean wants_expert;
    private DestinationPlace destination_place;
    private ExpertRequest expert_request;
    private int workers_num;

    public TransportationRequest(int id, Customer customer) {
        this.request_id = id;
        this.request_date = new Date();
        this.customer = customer;
        this.origin_place = new OriginPlace();
        this.receipt = new Receipt();
        this.destination_place = new DestinationPlace();
    }

    public Place get_destination() {
        return this.destination_place;
    }

    public int get_workers_num() {
        return this.workers_num;
    }

    public ExpertRequest get_expert_request() {
        return this.expert_request;
    }

    public String get_transportation_date() {
        return this.transportation_date;
    }

    public void set_date(String date) {
        this.transportation_date = date;
    }

    public void set_workers_num(int num) {
        this.workers_num = num;
    }

    public void set_wants_expert(boolean expert) {
        this.wants_expert = expert;
    }

    public boolean get_wants_expert() {
        return this.wants_expert;
    }

    public void make_expert_request(Place place, String visit_date) {
        this.expert_request = new ExpertRequest(place, visit_date);
    }

    public void set_destination_place(String address, String floor, String type) {
        this.destination_place.set_address(address);
        int floor_num = 0;
        try { floor_num = Integer.parseInt(floor); }
        catch (NumberFormatException ignored) {}
        this.destination_place.set_floor(floor_num);
        this.destination_place.set_type(type);
    }

    public void create_team() {
        int workers_number;
        if(this.wants_expert) {
            workers_number = this.expert_request.get_estimated_resource();
        }
        else {
            workers_number = this.workers_num;
        }
        int needed_trucks = workers_number / 4;
        for (int i = 0; i < needed_trucks; i++) {
            String truck_id = this.request_id + "-" + i;
            Truck new_truck = new Truck(truck_id);
            trucks.add(new_truck);
        }
        TransportationTeam team = new TransportationTeam(this.request_id, workers_number);
    }
}