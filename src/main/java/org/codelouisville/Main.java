package org.codelouisville;

import org.codelouisville.Logic.ReadCsv;
import org.codelouisville.Models.Game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;
import java.sql.SQLException;

public class Main {
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        setEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Game> games = null;
        try {
            games = ReadCsv.readingCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

        entityManager.getTransaction().begin();
        entityManager.persist(games.get(20));
        entityManager.getTransaction().commit();
        entityManager.close();


    }

    private static void setEntityManagerFactory()
    {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("org.codelouisville.NFL_2018_WEATHER");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}




