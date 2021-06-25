package main.logic;

public class Staff {
    protected String staff_id;
    private String name = "worker";
    private String phone_number = "09123456789";
    private String employment_date = "1400/04/04";
    public void set_name(String Name) {
        this.name = Name;
    }
    public void set_phone_number(String Phone_number) {
        this.phone_number = Phone_number;
    }
    public void set_employment_date(String date) {
        this.employment_date = date;
    }
}