package org.codelouisville.Logic;

import org.codelouisville.Models.Game;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

public class DbSeed {
    private DBFunctions d;
    public DbSeed(DBFunctions d) {
        this.d =d;
    }

    public void seed(List<Game> games){
        if(new fromDatabase(d).getGamesfromDb().size() <= 0) {
            d.beginTransaction();
            games.forEach(d.getEntityManager()::persist);
            d.commit();
            System.out.println("Database seeded!");
        } else{
            System.out.println("The database has already been seeded! No Db needed!");
        }
    }

}
