package com.company.menu.actions;

import com.company.contactservice.ContactService;
import com.company.menu.MenuAction;

import java.util.Scanner;

public class GetContactByNumberMenuAction implements MenuAction {
    ContactService cs;
    Scanner s;

    public GetContactByNumberMenuAction(ContactService cs, Scanner s) {
        this.cs = cs;
        this.s = s;
    }

    @Override
    public void doAction() {
        System.out.println("enter part of number");
        String value = s.nextLine();
        if (cs.getContactByNumber(value).size() == 0) System.out.println("No such contact");
        else System.out.println(cs.getContactByNumber(value));
    }

    @Override
    public String getName() {
        return "get contact by part of number";
    }

    @Override
    public boolean closeAfter() {
        return false;
    }
}
