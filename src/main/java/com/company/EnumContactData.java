package com.company;

import lombok.Getter;

public enum EnumContactData {
    PHONE("phone:"), MAIL("mail:");

    @Getter
    private String type;

    EnumContactData(String type) {
        this.type = type;
    }
}
