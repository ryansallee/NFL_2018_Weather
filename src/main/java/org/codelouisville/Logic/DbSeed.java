package org.codelouisville.Logic;

import org.codelouisville.Models.Game;

import java.util.List;

public class DbSeed {
    private DBFunctions dbFunctions;
    public DbSeed(DBFunctions d) {
        this.dbFunctions =d;
    }

    public void seed(List<Game> games, Queries queries){
        if(queries.getGamesfromDb().size() <= 0) {
            dbFunctions.beginTransaction();
            games.forEach(dbFunctions.getEntityManager()::persist);
            dbFunctions.commit();
            System.out.println("Database seeded!");
        } else{
            System.out.println("The database has already been seeded! No Db needed!");
        }
    }

}
