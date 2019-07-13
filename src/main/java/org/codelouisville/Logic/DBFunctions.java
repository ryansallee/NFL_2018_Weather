package org.codelouisville.Logic;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class DBFunctions {
    private final EntityManagerFactory entityManagerFactory;
    private Server server;
    private EntityManager entityManager;

    public DBFunctions()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.codelouisville.NFL_2018_WEATHER");
        {
            try {
                setServer();
                } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        try {
            Server.openBrowser("http://192.168.1.9:8082");
        } catch (Exception e) {
            e.printStackTrace();
        }
        openEntityManager();
    }

    private void setServer() throws SQLException
    {
        this.server = Server.createTcpServer().start();
    }

    private void openEntityManager()
    {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public void beginTransaction(){
        entityManager.getTransaction().begin();
    }

    public EntityManager getEntityManager(){
        return entityManager;
    }

    public void commit()
    {
        entityManager.getTransaction().commit();
    }

    public void closeEntityManager()
    {
        this.entityManager.close();
    }
    public void closeEntityManagerFactory () {
        entityManagerFactory.close();
    }

    public void stopServer()
    {
        this.server.stop();
    }
}
