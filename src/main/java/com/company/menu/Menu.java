package com.company.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuAction> actions = new ArrayList<>();

    public void addAction(MenuAction action) {
        actions.add(action);
    }

    public void run() {
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + 1 +" "+ actions.get(i).getName());
        }
    }

    public boolean checkChoice(int choice) {
        return (choice < 0 || choice > actions.size() - 1);
    }

    public void doAction(int choice) {
        actions.get(choice).doAction();
    }

    public boolean checkCloseStatus(int choice) {
        return actions.get(choice).closeAfter();
    }

}
