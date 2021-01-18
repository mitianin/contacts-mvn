package com.company.contactservice;

import com.company.*;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();
    void remove(int index);
    void add(Contact contact);
    default List<Contact> getContactByNumber(String value) {
        return null;
    }
    default List<Contact> getContactByName(String value) {
        return null;
    }
}
