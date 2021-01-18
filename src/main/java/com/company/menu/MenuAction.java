package com.company.menu;

public interface MenuAction {
    void doAction();

    String getName();

    default boolean closeAfter() {
        return false;
    }
}
