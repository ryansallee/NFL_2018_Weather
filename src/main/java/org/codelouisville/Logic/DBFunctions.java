//package org.codelouisville.Logic;
//
//import org.h2.tools.Server;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.io.Serializable;
//import java.sql.SQLException;
//
//public class DBFunctions {
//    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.codelouisville.NFL_2018_WEATHER");
//    private Server server;
//
//    {
//        try {
//            server = Server.createTcpServer().start();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private EntityManager entityManager;
//
//    public void setServer() throws SQLException
//    {
//        this.server = Server.createTcpServer().start();
//    }
//
//    public void openEntityManager()
//    {
//        this.entityManager = entityManagerFactory.createEntityManager();
//    }
//
//    public void closeEntityManager()
//    {
//        this.entityManager.close();
//    }
//
//    public void stop()
//    {
//        this.server.stop();
//    }
//}
