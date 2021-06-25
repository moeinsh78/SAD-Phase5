package main.logic;

public class ExpertRequest extends Request {
    private int estimated_required_resource = 8;
    private String visit_date;
    private Place place;

    public ExpertRequest(Place origin_place, String date) {
        this.place = origin_place;
        this.visit_date = date;
    }

    public int get_estimated_resource() {
        return this.estimated_required_resource;
    }
}
