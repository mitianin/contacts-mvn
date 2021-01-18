package com.company.contactservice;

import com.company.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemoryContactService implements ContactService {

    private static List<Contact> list = new ArrayList<>();

    @Override
    public List<Contact> getAll() {
        return list;
    }

    @Override
    public void remove(int index) {
        list.remove(index);
    }

    @Override
    public void add(Contact contact) {
        list.add(contact);
    }

    @Override
    public List<Contact> getContactByNumber(String value) {
        return list.stream().
                filter(contact -> contact.getContactData().contains(value)).
                collect(Collectors.toList());
    }

    @Override
    public List<Contact> getContactByName(String value) {
        return list.stream().
                filter(contact -> contact.getName().startsWith(value)).
                collect(Collectors.toList());
    }
}
