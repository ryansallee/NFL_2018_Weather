package org.codelouisville;

import org.codelouisville.Logic.ReadCsv;
import org.codelouisville.Models.Game;
import org.codelouisville.Models.Stadium;
import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;
import java.sql.SQLException;

public class Main {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.codelouisville.NFL_2018_WEATHER");

    public static void main(String[] args) throws Exception {
        //setEntityManagerFactory();
        Server server = Server.createTcpServer(args).start();
        EntityManager entityManager = entityManagerFactory.createEntityManager();

/*        List<Game> games = null;
        try {
            games = ReadCsv.readingCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        entityManager.getTransaction().begin();
        int checkDb = entityManager.createQuery("from Game", Game.class).getResultList().size();
        entityManager.getTransaction().commit();
        entityManager.getTransaction().begin();
        Game g1 = new Game();
        g1.setAwayScore(20);
        Game g2 = new Game();
        g2.setAwayScore(45);
        entityManager.persist(g1);
        entityManager.persist(g2);
        entityManager.getTransaction().commit();
        if(checkDb == 0) {
            entityManager.getTransaction().begin();
            Game g22 = new Game();
            g22.setAwayScore(45);
            entityManager.persist(g22);
            entityManager.getTransaction().commit();
            //entityManager.close();
        } else {
            System.out.println("Already have games! No DB work needed!");
        }
        entityManager.close();
        entityManagerFactory.close();
        server.stop();
        System.out.println("Press enter to quit;");
    }


}




