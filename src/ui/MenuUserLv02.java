package ui;

import java.util.Scanner;
import smartsite.Person;
import smartsite.Worker;
import smartsite.Visitor;
import static ui.Draft.pause;

public class MenuUserLv02 extends MenuUserLv03 {

    @Override
    public void displayOption() {
        super.displayOption();
        System.out.println("5. Take tools");
        System.out.println("6. Add person");
    }

    @Override
    public void chooseOption(int choice) {
        switch (choice) {
            case (5):
                System.out.println("Function not complete yet!");
                pause();
                break;
            case (6):
                handleAddPerson();
                break;
            default:
                super.chooseOption(choice);
                break;
        }
    }

    private void handleAddPerson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("           ADD NEW PERSON               ");
        System.out.println("----------------------------------------");

        System.out.print("Enter ID (e.g., P004): ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("[!] ID cannot be empty!");
            pause();
            return;
        }

        if (pm.findPersonByID(id) != null) {
            System.out.println("[!] ID already exists: " + id);
            pause();
            return;
        }

        System.out.print("Enter Full Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("[!] Full name cannot be empty!");
            pause();
            return;
        }

        System.out.print("Enter Code (e.g., W002, V002): ");
        String code = sc.nextLine().trim();

        System.out.println("Select type/role:");
        System.out.println("1. Worker");
        System.out.println("2. Visitor");
        System.out.println("3. Other Person");
        System.out.print("Your choice (1-3): ");
        String typeChoice = sc.nextLine().trim();

        Person newPerson;
        switch (typeChoice) {
            case "1":
                System.out.print("Enter Company Name: ");
                String company = sc.nextLine().trim();
                System.out.print("Enter Contract Type (e.g., Full-time / Part-time): ");
                String contract = sc.nextLine().trim();
                newPerson = new Worker(id, name, code, "WORKER", "ACTIVE", company, contract);
                break;
            case "2":
                System.out.print("Enter Visit Purpose: ");
                String purpose = sc.nextLine().trim();
                System.out.print("Enter Host Name: ");
                String host = sc.nextLine().trim();
                newPerson = new Visitor(id, name, code, "VISITOR", "ACTIVE", purpose, host);
                break;
            default:
                System.out.print("Enter Role name (e.g., CONTRACTOR, SAFETY_OFFICER): ");
                String role = sc.nextLine().trim();
                if (role.isEmpty()) {
                    role = "WORKER";
                }
                newPerson = new Worker(id, name, code, role, "ACTIVE");
                break;
        }

        boolean success = pm.addPerson(newPerson);
        if (success) {
            System.out.println("\n[+] Added person successfully!");
            System.out.println("    " + newPerson);
        } else {
            System.out.println("\n[!] Failed to add person! (List full or invalid data)");
        }
        pause();
    }
}
