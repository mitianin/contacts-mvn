package com.company;

import com.company.contactservice.MemoryContactService;
import com.company.menu.Menu;
import com.company.menu.actions.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Scanner s = new Scanner(System.in);
        MemoryContactService memory = new MemoryContactService();

        menu.addAction(new ReadAllContactsMenuAction(memory));
        menu.addAction(new AddContactMenuAction(memory, s));
        menu.addAction(new RemoveContactMenuAction(memory, s));
        menu.addAction(new GetContactByNumberMenuAction(memory, s));
        menu.addAction(new GetContactByNameMenuActions(memory, s));
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
