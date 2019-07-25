package org.codelouisville.Data;

import org.codelouisville.Models.Game;

import java.util.List;

//Class that handles seeding the H2 database
public class DbSeed {
    //Fields
    private final DBFunctions dbFunctions;
    //Constructor
    //The DB seed uses an instance of DBFunctions class to be able to conduct transactions with the database.
    public DbSeed(DBFunctions dbFunctions) {
        this.dbFunctions =dbFunctions;
    }

    //Method that persists the list of games that is read from the 2018_nfl_results CSV. It checks to see if there are
    //any entries in the database to ensure duplicated data is not added to the database.
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
