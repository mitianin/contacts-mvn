package com.company;

import com.company.contactservice.ContactService;
import com.company.contactservice.FileContactService;
import com.company.menu.Menu;
import com.company.menu.actions.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner s = new Scanner(System.in);
        ContactService file = new FileContactService("contacts.txt");

        menu.addAction(new ReadAllContactsMenuAction(file));
        menu.addAction(new AddContactMenuAction(file, s));
        menu.addAction(new RemoveContactMenuAction(file, s));
        menu.addAction(new GetContactByNameMenuActions(file, s));
        menu.addAction(new GetContactByNumberMenuAction(file, s));
        menu.addAction(new CloseMenuAction());

        while (true) {
            menu.run();
            int choice = Integer.parseInt(s.nextLine());

            if (menu.checkChoice(choice - 1)) {
                System.out.println("Invalid input");
                continue;
            }

            if (menu.checkCloseStatus(choice - 1)) return;
            menu.doAction(choice - 1);

        }
    }
}
