package org.codelouisville.Data;

import org.codelouisville.Models.Game;

import java.util.List;

public class DbSeed {
    private final DBFunctions dbFunctions;
    public DbSeed(DBFunctions dbFunctions) {
        this.dbFunctions =dbFunctions;
    }

    public void seed(List<Game> games, Query query){
        if(query.getGamesfromDb().size() <= 0) {
            dbFunctions.beginTransaction();
            games.forEach(dbFunctions.getEntityManager()::persist);
            dbFunctions.commitTransaction();
            System.out.println("Database seeded!");
        } else{
            System.out.println("The database has already been seeded!");
        }
    }

}
