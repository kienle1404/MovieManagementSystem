package com.mvms;

import com.mvms.persistence.dao.impl.FilmDaoImpl;

import java.util.Scanner;

public class TestFilmDao {
    public static void main(String[] args) {
        FilmDaoImpl filmService = new FilmDaoImpl();

        Scanner scanner = new Scanner(System.in);
        filmService.setScanner(scanner);

        filmService.printSessionFactory();
        filmService.create();
    }
}
