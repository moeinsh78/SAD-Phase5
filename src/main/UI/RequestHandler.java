package main.UI;

import main.DAO.SystemDAO;
import main.logic.Request;
import main.logic.TransportationRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

public class RequestHandler {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Request> requests = new ArrayList<>();
    private TransportationRequest current_request;
    private SystemDAO system_DAO = new SystemDAO();

    public Customer create_customer(String name, String phone_number) {
        Customer new_customer = new Customer(name, phone_number, customers.size());
        customers.add(new_customer);
        return new_customer;
    }
    public void provide_service(Customer current_customer) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter your request type: (new_transportation)");
            String command = reader.readLine();
            if (command.equals("new_transportation")) {
                TransportationRequest transport_req = new TransportationRequest(requests.size(), current_customer);
                requests.add(transport_req);
                current_request = transport_req;
                get_transportation_date();
                system_DAO.save_request(transport_req);
            }
            else if(command.equals("logout")) {
                System.out.println("User " + current_customer.getName() + " logged out!");
                return;
            }
            else {
                System.out.println("Invalid Command!");
            }
        }
    }

    public void get_transportation_date() throws IOException {
        System.out.println("Enter Transportation Date: (set_date ...)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String command = reader.readLine();
            try {
                String[] command_tokens = command.split("\\s+");
                if (command_tokens[0].equals("set_date") && command_tokens.length == 2) {
                    String date = command_tokens[1];
                    current_request.set_date(date);
                    get_origin_place();
                    return;
                }
                else if (command.equals("end")) {
                    return;
                }
                else {
                    System.out.println("Invalid Command!");
                }
            } catch (PatternSyntaxException ignored) {}
        }
    }

    public void get_origin_place() throws IOException {
        System.out.println("Enter Origin Place Info: (origin_place address floor type)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String command = reader.readLine();
            try {
                String[] command_tokens = command.split("\\s+");
                if (command_tokens[0].equals("origin_place") && command_tokens.length == 4) {
                    String address = command_tokens[1];
                    String floor = command_tokens[2];
                    String type = command_tokens[3];
                    current_request.set_origin_info(address, floor, type);
                    boolean wants_expert = wants_expert(type);
                    current_request.set_wants_expert(wants_expert);
                    if(wants_expert) {
                        String visit_date = get_expert_visit_date();
                        current_request.make_expert_request(current_request.get_origin(), visit_date);
                    }
                    get_destination_place();
                    if(!wants_expert) {
                        int workers_num = get_required_workers_num();
                        current_request.set_workers_num(workers_num);
                    }
                    current_request.create_team();
                    return;
                }
                else if (command.equals("end")) {
                    return;
                }
                else {
                    System.out.println("Invalid Command!");
                }
            } catch (PatternSyntaxException ignored) {}
        }
    }

    public boolean wants_expert(String type) throws IOException {
        if(!type.equals("residential"))
            return true;
        System.out.println("Do you need us to send an expert for resource estimation?: (yes/no)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String command = reader.readLine();
            if (command.equals("yes"))
                return true;
            else if (command.equals("no"))
                return false;
            else
                System.out.println("Invalid Command!");
        }
    }

    public String get_expert_visit_date() throws IOException {
        System.out.println("Enter expert visit date: (set_visit_date ...)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String command = reader.readLine();
            try {
                String[] command_tokens = command.split("\\s+");
                if (command_tokens[0].equals("set_visit_date") && command_tokens.length == 2) {
                    return command_tokens[1];
                }
                else {
                    System.out.println("Invalid Command!");
                }
            } catch (PatternSyntaxException ignored) {}
        }
    }

    public void get_destination_place() throws IOException {
        System.out.println("Enter Destination Place Info: (destination_place address floor type)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String command = reader.readLine();
            try {
                String[] command_tokens = command.split("\\s+");
                if (command_tokens[0].equals("destination_place") && command_tokens.length == 4) {
                    String address = command_tokens[1];
                    String floor = command_tokens[2];
                    String type = command_tokens[3];
                    current_request.set_destination_place(address, floor, type);
                    return;
                }
                else if (command.equals("end")) {
                    return;
                }
                else {
                    System.out.println("Invalid Command!");
                }
            } catch (PatternSyntaxException ignored) {}
        }
    }

    public int get_required_workers_num() throws IOException {
        System.out.println("Enter Estimated Workers Num: (workers_num num)");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String command = reader.readLine();
            try {
                String[] command_tokens = command.split("\\s+");
                if (command_tokens[0].equals("workers_num") && command_tokens.length == 2) {
                    int num = 0;
                    try { num = Integer.parseInt(command_tokens[1]); }
                    catch (NumberFormatException ignored) {}
                    return num;
                }
                else {
                    System.out.println("Invalid Command!");
                }
            } catch (PatternSyntaxException ignored) {}
        }
    }
}
