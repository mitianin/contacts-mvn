package com.company.contactservice;

import com.company.Contact;
import com.company.EnumContactData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileContactService implements ContactService {
    //private String path;
    private File file;

    public FileContactService(String path) {
        //this.path = path;
        file = new File(path);
    }

    private List<Contact> contactList = new ArrayList<>();



    @Override
    public List<Contact> getAll() {
        if (!file.exists() || file.length() <= 1) return new ArrayList<>();
        contactList.clear();
        fileToArray();
        return contactList;
    }

    @Override
    public void remove(int index) {
        if (contactList.size() > index && index > -1) {
            contactList.remove(index);
        }
        arrayToFile();
    }

    @Override
    public void add(Contact contact) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(contact.getName() + "[" +
                    contact.getType().getType() + contact.getContactData() + "]");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileToArray(){
        try(BufferedReader fr = new BufferedReader(new FileReader(file))) {
            while (fr.ready()) {
                String[] data = fr.readLine().split("[\\[:\\]]");
                contactList.add(new Contact(data[0], data[2],
                        data[1].equals("mail") ? EnumContactData.MAIL : EnumContactData.PHONE ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void arrayToFile(){
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write("");
            bw = new BufferedWriter(new FileWriter(file, true));
            for (Contact contact : contactList) {
                bw.write(contact.getName() + "[" +
                        contact.getType().getType() + contact.getContactData() + "]");
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Contact> getContactByNumber(String value) {
        return contactList.stream().
                filter(contact -> contact.getContactData().contains(value)).
                collect(Collectors.toList());
    }

    @Override
    public List<Contact> getContactByName(String value) {
        return contactList.stream().
                filter(contact -> contact.getName().startsWith(value)).
                collect(Collectors.toList());
    }

}
