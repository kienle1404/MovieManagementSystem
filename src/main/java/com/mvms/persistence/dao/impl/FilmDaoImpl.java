package com.mvms.persistence.dao.impl;

import com.mvms.entity.*;
import com.mvms.persistence.dao.FilmDao;
import com.mvms.utils.HibernateUtil;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmDaoImpl implements FilmDao {
    private static Scanner scanner;
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        FilmDaoImpl.scanner = scanner;
    }

    public void printSessionFactory() {
        System.out.println(sessionFactory);
    }

    @Override
    public String create() {
        Transaction tx = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // Get film information
            System.out.println("Film name:");
            String filmName = scanner.nextLine();
            System.out.println("Film description:");
            String filmDescription = scanner.nextLine();

            // Get film language
            System.out.println("Film language:");
            String languageName = scanner.nextLine();

            // Get film categories, assume user doesn't list same categories
            System.out.print("Number of categories: ");
            int categoryNumber = Integer.parseInt(scanner.nextLine());
            List<String> categoryNames = new ArrayList<>();
            for (int i = 0; i < categoryNumber; i++) {
                System.out.print("Category #" + (i + 1) + ": ");
                String categoryName = scanner.nextLine();
                categoryNames.add(categoryName);
            }

            // Get film actor(s)
            System.out.print("Number of actors: ");
            int actorNumber = Integer.parseInt(scanner.nextLine());
            List<String> actorFirstNames = new ArrayList<>();
            List<String> actorLastNames = new ArrayList<>();
            for (int i = 0; i < actorNumber; i++) {
                System.out.print("Actor #" + (i + 1) + " First Name: ");
                String actorName = scanner.nextLine();
                actorFirstNames.add(actorName);

                System.out.println("Actor #" + (i + 1) + " Last Name: ");
                actorName = scanner.nextLine();
                actorLastNames.add(actorName);
            }

            // Insert new categories into table (assume categories don't have same name)
            List<Category> filmCategoryList = new ArrayList<>();
            for (String categoryName: categoryNames) {
                Query query = session.createQuery("From Category WHERE name = :name", Category.class);
                query.setParameter("name", categoryName);
                List<Category> categories = query.getResultList();

                if (categories.isEmpty()) {
                    Category category = new Category(categoryName);
                    filmCategoryList.add(category);
                    session.persist(category);
                } else {
                    filmCategoryList.add(categories.get(0));
                }
            }

            // Insert new actors into table (assume actors don't have same name)
            List<Actor> filmActorList = new ArrayList<>();
            for (int i = 0; i < actorNumber; i++) {
                Query query = session.createQuery("FROM Actor WHERE firstName = :firstName AND lastName = :lastName", Actor.class);
                query.setParameter("firstName", actorFirstNames.get(i));
                query.setParameter("lastName", actorLastNames.get(i));
                List<Actor> actors = query.getResultList();

                if (actors.isEmpty()) {
                    Actor actor = new Actor(actorFirstNames.get(i), actorLastNames.get(i));
                    filmActorList.add(actor);
                    session.persist(actor);
                } else {
                    filmActorList.add(actors.get(0));
                }
            }

            // Insert language into table
            Language filmLanguage = null;
            Query query = session.createQuery("From Language WHERE name = :name", Language.class);
            query.setParameter("name", filmLanguage);
            List<Language> languages = query.getResultList();

            if (languages.isEmpty()) {
                filmLanguage = new Language(languageName);
                session.persist(filmLanguage);
            } else {
                filmLanguage = languages.get(0);
            }

            Film film = new Film(filmName, filmDescription);

            for (Category category: filmCategoryList) {
                FilmCategory filmCategory = new FilmCategory(film, category);
                category.getFilmCategories().add(filmCategory);
                film.getFilmCategories().add(filmCategory);
                session.persist(category);
            }

            for (Actor actor: filmActorList) {
                FilmActor filmActor = new FilmActor(film, actor);
                actor.getFilmActors().add(filmActor);
                film.getFilmActors().add(filmActor);
                session.persist(actor);
            }

            filmLanguage.getFilms().add(film);
            session.persist(filmLanguage);
            tx.commit();

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
        return "Success";
    }
}
