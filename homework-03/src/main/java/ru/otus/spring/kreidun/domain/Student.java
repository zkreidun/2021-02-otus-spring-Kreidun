package ru.otus.spring.kreidun.domain;

public class Student {

    private final String firstname;
    private final String lastname;

    public Student(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() { return lastname; }
}
