package main.DAO;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class

import main.logic.TransportationRequest;

public class SystemDAO {
    String file_name = "requests_record_file.txt";

    public SystemDAO() {
        try {
            File file = new File(file_name);
            if (!file.createNewFile()) {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void save_request(TransportationRequest new_request) {
        String basic_info = "--------- Request id: " + new_request.getRequest_id() +
                " --- User name: " + new_request.get_customer().getName() + " ---" +
                " Transportation date: " + new_request.get_transportation_date() + " -------------\n";
        String expert;
        if(new_request.get_wants_expert()) {
            expert = "Wants expert -- Estimated workers num: " +
                    new_request.get_expert_request().get_estimated_resource() + "\n";
        }
        else {
            expert = "Does not want expert -- Requested workers num: " + new_request.get_workers_num() + "\n";
        }
        String origin = "Origin address: " + new_request.get_origin().get_address() + "\n";
        String destination = "Destination address: " + new_request.get_destination().get_address() + "\n";
        try {
            FileWriter file = new FileWriter(file_name, true);
            file.write(basic_info);
            file.write(expert);
            file.write(origin);
            file.write(destination);
            file.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
