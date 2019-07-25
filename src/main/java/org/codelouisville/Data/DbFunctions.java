package org.codelouisville.Data;

import org.h2.tools.Server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

//This class handles Java Persistence API functions H2 functions so that it is encapsulated away from other classes.
public class DbFunctions {
    //Fields
    private final EntityManagerFactory entityManagerFactory;
    private Server server;
    private EntityManager entityManager;

    //The constructor initializes an EntityManagerFactory object as this is required to open the database connection. An
    //EntityManger object is also created so that transactions can be performed on the database.
    public DbFunctions()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("org.codelouisville.NFL_2018_WEATHER");
        {
            try {
                setServer();
                } catch (SQLException e) {
                e.printStackTrace();
            }
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

    void beginTransaction(){
        entityManager.getTransaction().begin();
    }

    //An Entity Manager is needed to conduct a transaction.
    EntityManager getEntityManager(){
        return entityManager;
    }

    void commitTransaction()
    {
        entityManager.getTransaction().commit();
    }

    //The EntityManager, EntityManagerFactory, and Server must be closed upon application exit.
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
