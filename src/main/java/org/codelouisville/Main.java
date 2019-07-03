package org.codelouisville;


import org.codelouisville.Logic.ReadCsv;
import org.codelouisville.Models.Game;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class Main {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.codelouisville.NFL_2018_WEATHER");

    public static void main(String[] args) throws Exception {
        //setEntityManagerFactory();
        Server server = Server.createTcpServer(args).start();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

      List<Game> games = null;
        try {
            games = ReadCsv.readingCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

        entityManager.getTransaction().begin();
        List<Game> checkDbL = entityManager.createQuery("from Game", Game.class).getResultList();
        checkDbL.forEach(g->System.out.println(g.toString()));
        int checkDb = checkDbL.size();
        entityManager.getTransaction().commit();
        if(checkDb == 0) {
            entityManager.getTransaction().begin();
            games.forEach(entityManager::persist);

            entityManager.getTransaction().commit();
        } else {
            System.out.println("Already have games! No DB work needed!");
        }
        entityManager.close();
        entityManagerFactory.close();
        server.stop();
        System.out.println("Press enter to quit;");
        }


}




