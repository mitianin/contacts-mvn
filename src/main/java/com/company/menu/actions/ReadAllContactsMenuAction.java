package com.company.menu.actions;

import com.company.contactservice.ContactService;
import com.company.menu.MenuAction;

import javax.swing.*;

public class ReadAllContactsMenuAction implements MenuAction {

    ContactService cs;

    public ReadAllContactsMenuAction(ContactService cs) {
        this.cs = cs;
    }

    @Override
    public void doAction() {
        if (cs.getAll().isEmpty()) System.out.println("List is EMPTY");
        else System.out.println(cs.getAll());
    }

    @Override
    public String getName() {
        return "show contacts";
    }
}
