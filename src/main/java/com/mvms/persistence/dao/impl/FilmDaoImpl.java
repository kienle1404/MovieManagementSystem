package com.mvms.persistence.dao.impl;

import com.mvms.entity.Film;
import com.mvms.persistence.dao.FilmDao;
import com.mvms.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.Scanner;

public class FilmDaoImpl implements FilmDao {
    private Scanner scanner;

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String create() {
        Transaction tx = null;
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession()
        ) {
            tx = session.beginTransaction();

            System.out.println("Film name:");
            String filmName = scanner.nextLine();
            System.out.println("Film description:");
            String filmDescription = scanner.nextLine();

            Film film = new Film(filmName, filmDescription);
            //TODO: Implement create function

            session.persist(film);
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return "Failed";
        }
        return "Success";
    }

//    @Override
//    public String delete() throws SQLException {
//        return "";
//    }
//
//    @Override
//    public String modify() throws SQLException {
//        return "";
//    }
//
//    @Override
//    public String query() throws SQLException {
//        return "";
//    }
}
