package org.example;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "persons")
public class Person {
    private static final String[] names = new String[]{"Александр", "Алексей", "Иван", "Андрей", "Василий", "Павел", "Максим"};
    private static final Random random = new Random();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static Person create(){
        return new Person(names[random.nextInt(names.length)], random.nextInt(20)+18);
    }

    public void updateName(){
        name = names[random.nextInt(names.length)];
    }
    public void updateAge(){
        age = random.nextInt(20)+18;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
