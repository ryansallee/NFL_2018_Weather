package org.codelouisville.Data;

import org.codelouisville.Models.Game;

import java.util.List;

//Class that handles persisting to the H2 database.
public class DbPersistence {
    //Fields
    private final DBFunctions dbFunctions;
    //Constructor
    //The DB dbPersist uses an instance of DBFunctions class to be able to conduct transactions with the database.
    public DbPersistence(DBFunctions dbFunctions) {
        this.dbFunctions =dbFunctions;
    }

    //Method that persists the list of games that is read from the 2018_nfl_results CSV. It checks to see if there are
    //not any entries in the game table to persist the games from the games list to the table. If there are entries,
    //no transactions will be performed against the database.
    public void dbPersist(List<Game> games, Query query){
        if(query.getGamesFromDb().size() <= 0) {
            dbFunctions.beginTransaction();
            games.forEach(dbFunctions.getEntityManager()::persist);
            dbFunctions.commitTransaction();
            System.out.println("Database seeded!");
        } else{
            System.out.println("The database has already been seeded!");
        }
    }

}
