package com.company.menu.actions;

import com.company.Contact;
import com.company.contactservice.ContactService;
import com.company.menu.MenuAction;

import java.sql.SQLOutput;
import java.util.Scanner;

public class RemoveContactMenuAction implements MenuAction {
    ContactService cs;
    Scanner s;

    public RemoveContactMenuAction(ContactService cs, Scanner s) {
        this.cs = cs;
        this.s = s;
    }

    @Override
    public void doAction() {
        System.out.println("enter index to remove");
        int index = Integer.parseInt(s.nextLine());
        if (index < 0 || index > cs.getAll().size() - 1) {
            System.out.println("contact is not exist");
        }
        cs.remove(index);
    }

    @Override
    public String getName() {
        return "remove contact";
    }
}
