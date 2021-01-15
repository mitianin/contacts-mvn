package com.company.menu.actions;

import com.company.contactservice.ContactService;
import com.company.menu.MenuAction;

import java.util.Scanner;

public class GetContactByNameMenuActions implements MenuAction {
    ContactService cs;
    Scanner s;

    public GetContactByNameMenuActions(ContactService cs, Scanner s) {
        this.cs = cs;
        this.s = s;
    }

    @Override
    public void doAction() {
        System.out.println("enter beginning of name");
        String value = s.nextLine();
        if (cs.getContactByName(value).size() == 0) System.out.println("No such contact");
        else System.out.println(cs.getContactByName(value));

    }

    @Override
    public String getName() {
        return "get contact by part of name";
    }
}
