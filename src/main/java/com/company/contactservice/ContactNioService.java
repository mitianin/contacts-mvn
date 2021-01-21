package com.company.contactservice;

import com.company.Contact;
import com.company.EnumContactData;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ContactNioService implements ContactService {

    private final String path;

    private List<Contact> list = new ArrayList<>();
    private ByteBuffer bb = ByteBuffer.allocate(1024);

    @Override
    public List<Contact> getAll() {
        list.clear();
        fromFileToArray();
        return list;
    }

    @Override
    public void remove(int index) {
        list.remove(index);

        try {
            Files.newBufferedWriter(Path.of(path), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileChannel fc = new FileOutputStream(path).getChannel()){
            for (Contact contact : list) {
                String contactData = contact.getName() + "[" +
                        contact.getType().getType() + contact.getContactData() + "]\n";
                bb.put(contactData.getBytes());
            }

            fc.write(ByteBuffer.wrap(bb.array(), 0, bb.position()));
            bb.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void fromFileToArray() {
        try (FileChannel fc = (FileChannel) Files.newByteChannel(Path.of(path))){
            fc.read(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String contactData : new String(bb.array(), 0, bb.position()).split("\n")) {
            String[] data = contactData.split("[\\[:\\]]");

            if (data.length == 3) {
                list.add(new Contact(data[0], data[2],
                        data[1].equals("mail") ? EnumContactData.MAIL : EnumContactData.PHONE));
            }
        }
        bb.clear();
    }

    @Override
    public void add(Contact contact) {

        if (!new File(path).exists()) {
            try {
                new File(path).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String contactData = contact.getName() + "[" +
                contact.getType().getType() + contact.getContactData() + "]\n";

        try {
            Files.write(Paths.get(path), contactData.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> getContactByNumber(String value) {
        return  list.stream().
                filter(contact -> contact.getContactData().contains(value)).
                collect(Collectors.toList());
    }

    @Override
    public List<Contact> getContactByName(String value) {
        return  list.stream().
                filter(contact -> contact.getName().startsWith(value)).
                collect(Collectors.toList());
    }
}
