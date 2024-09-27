package org.example.task2;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        File file = new File(PersonList.FILE_JSON);
        List<Person> persons;
        if (file.exists() && !file.isDirectory()) {
            persons = PersonList.readPersonsFromFile(PersonList.FILE_JSON);
        } else {
            persons = preparePersonList();
        }
        PersonList.savePersonsToFile(PersonList.FILE_JSON, persons);
        PersonList.savePersonsToFile(PersonList.FILE_XML, persons);
        PersonList.savePersonsToFile(PersonList.FILE_BIN, persons);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Пожалуйста, выберите опцию:\n1.Показать список пользователей\n2.Добавить пользователя\n3.Изменить пользователя\n4.Удалить пользователя\n5.Выход");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    PersonList.displayPersons(persons);
                    break;
                case "2":
                    PersonList.addPerson(sc, persons);
                    PersonList.displayPersons(persons);
                    break;
                case "3":
                    PersonList.renewPerson(sc, persons);
                    PersonList.displayPersons(persons);
                    break;
                case "4":
                    PersonList.removePersonFromList(sc, persons);
                    PersonList.displayPersons(persons);
                    break;
                case "5":
                    PersonList.savePersonsToFile(PersonList.FILE_JSON, persons);
                    PersonList.savePersonsToFile(PersonList.FILE_XML, persons);
                    PersonList.savePersonsToFile(PersonList.FILE_BIN, persons);
                    System.out.println("Завершение приложения");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Нет такой возможности");
                    break;
            }
        }

    }

    static List<Person> preparePersonList() {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("Анна", 32));
        list.add(new Person("Евдокия", 13));
        list.add(new Person("Александр", 24));
        list.add(new Person("Мария", 65));
        list.add(new Person("Павел", 44));
        return list;
    }
}
