package main;

import main.UI.Customer;
import main.UI.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

public class LastPhase {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        RequestHandler requestHandler = new RequestHandler();
        while (true) {
            System.out.println("Enter your info: (customer name phone_num)");
            String command = reader.readLine();
            try {
                String[] command_tokens = command.split("\\s+");
                if (command_tokens[0].equals("customer")) {
                    String name = command_tokens[1];
                    String phone_number = command_tokens[2];
                    Customer new_customer = requestHandler.create_customer(name, phone_number);
                    requestHandler.provide_service(new_customer);
                }
                else if (command.equals("end"))
                    break;
                else {
                    System.out.println("Invalid Command!");
                }
            } catch (PatternSyntaxException ignored) {}
        }
        System.out.println("End Of Commands!");
    }
}