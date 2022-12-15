package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;

public class Contact {
    private final String phone;

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}