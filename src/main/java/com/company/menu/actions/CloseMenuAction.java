package com.company.menu.actions;

import com.company.menu.MenuAction;

public class CloseMenuAction implements MenuAction {


    @Override
    public void doAction() {

    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public boolean closeAfter() {
        return true;
    }
}
