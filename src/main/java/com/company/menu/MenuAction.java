package com.company.menu;

import com.company.Contact;

public interface MenuAction {
    void doAction();

    String getName();

    default boolean closeAfter() {
        return false;
    }
}
