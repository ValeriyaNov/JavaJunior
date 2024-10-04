package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        try(SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .buildSessionFactory()) {

            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            for (int i = 0; i < 5; i++) {
                Person person = Person.create();
                session.save(person);
                System.out.println("Object person save successfully");
            }

            session.getTransaction().commit();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}