package com.company.menu.actions;

import com.company.Contact;
import com.company.EnumContactData;
import com.company.contactservice.ContactService;
import com.company.menu.MenuAction;

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
        System.out.println("enter contact PHONE in format +380********* or MAIL");
        String data = s.nextLine();

        if (data.trim().matches("\\+380\\d{9}")) {
            cs.add(new Contact(name, data, EnumContactData.PHONE));
        } else if (data.trim().matches(".+@gmail\\.com")) {
            cs.add(new Contact(name, data, EnumContactData.MAIL));
        } else {
            System.out.println("Invalid phone format");
        }

    }

    @Override
    public String getName() {
        return "add contact";
    }
}
