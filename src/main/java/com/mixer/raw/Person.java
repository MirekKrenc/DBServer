package com.mixer.raw;

public class Person {

    String name;
    int age;
    String address;
    String cardPlate;
    String description;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", cardPlate='" + cardPlate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
