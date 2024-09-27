package org.example.task1;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person user = new Person("Анна", 17);
        try (FileOutputStream fileOutputStream = new FileOutputStream("user.bin");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(user);
            System.out.println("Объект сереализован");
        }

        try (FileInputStream fileInputStream = new FileInputStream("user.bin");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            user = (Person) objectInputStream.readObject();
            System.out.println("Объект десериализован");
        }
        System.out.println("Имя: " + user.getName());
        System.out.println("Возраст: " + user.getAge());

    }
}
