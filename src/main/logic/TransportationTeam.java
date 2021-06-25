package main.logic;

import java.util.ArrayList;

public class TransportationTeam {
    private int members_number;
    private int salary_per_hour = 10000;
    private int team_id;
    private ArrayList<TransportationStaff> staff = new ArrayList<>();

    public TransportationTeam(int team_id, int workers_numbers) {
        this.team_id = team_id;
        this.members_number = workers_numbers;
        for(int i = 0; i < workers_numbers; i++) {
            String worker_id = team_id + "-" + i;
            TransportationStaff new_worker = new TransportationStaff(worker_id);
            staff.add(new_worker);
        }
    }

    public void set_team_id(int id) {
        this.team_id = id;
    }

    public void set_salary_per_hour(int salary) {
        this.salary_per_hour = salary;
    }
    public void set_members_number(int members) {
        this.members_number = members;
    }
}