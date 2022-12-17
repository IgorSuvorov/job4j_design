package ru.job4j.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        Student st1 = new Student("Mike");
        Student st2 = new Student("Maria");
        final Subject subject = new Subject(3, true, "math", new College("KSU"),
                new Student[] {st1, st2});

        final Gson gson = new GsonBuilder().create();

        String gsonString = gson.toJson(subject);

        System.out.println(gson.toJson(subject));

        final Subject subjectMod = gson.fromJson(gsonString, Subject.class);
        System.out.println(subjectMod);
    }
}