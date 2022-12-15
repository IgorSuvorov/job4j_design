package ru.job4j.io;

import java.util.Arrays;

public class Subject {
    private int hours;
    private boolean availableToRegisterFor;
    private String name;
    private College college;
    private Student[] students;

    public Subject(
            int hours,
            boolean availableToRegisterFor,
            String name,
            College college,
            Student[] students) {
        this.hours = hours;
        this.availableToRegisterFor = availableToRegisterFor;
        this.name = name;
        this.college = college;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Subject{"
                +
                "hours=" + hours
                +
                ", availableToRegisterFor=" + availableToRegisterFor
                +
                ", name='" + name + '\''
                +
                ", college=" + college
                +
                ", students=" + Arrays.toString(students)
                +
                '}';
    }
}
