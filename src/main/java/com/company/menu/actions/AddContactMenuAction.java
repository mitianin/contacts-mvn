package com.company.menu.actions;

import com.company.Contact;
import com.company.contactservice.ContactService;
import com.company.menu.MenuAction;

import java.sql.SQLOutput;
import java.util.Scanner;

public class AddContactMenuAction implements MenuAction {
    ContactService cs;
    Scanner s;

    public AddContactMenuAction(ContactService cs, Scanner s) {
        this.cs = cs;
        this.s = s;
    }

    @Override
    public void doAction() {
        System.out.println("enter contact NAME");
        String name = s.nextLine();
        System.out.println("enter contact PHONE in format +380*********");
        String phone = s.nextLine();

        if (!phone.trim().matches("\\+380\\d{9}")) {
            System.out.println("Invalid phone format");
        } else {
            cs.add(new Contact(name, phone));
        }

    }

    @Override
    public String getName() {
        return "add contact";
    }
}
