package com.company;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private String name;
    private String contactData;
    private EnumContactData type;

    public Contact(String name, String contactData) {
        this.name = name;
        this.contactData = contactData;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", contactData='" + contactData + '\'' +
                ", type=" + type +
                '}';
    }
}
