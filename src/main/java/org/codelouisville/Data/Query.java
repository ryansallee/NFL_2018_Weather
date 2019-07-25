package org.codelouisville.Data;

import org.codelouisville.Models.Game;

import java.util.List;

//Class to run SQL queries against the Game database
public class Query {
    //Fields
    //A DbFunctions object must be passed so that any transactional activity from the Java Persistence API is encapsulated.
    private final DbFunctions d;

    public Query(DbFunctions d){
        this.d = d;
    }

    public List<Game> getGamesFromDb(){
        d.beginTransaction();
        List<Game> games = getGames();
        d.commitTransaction();
        return games;
    }

    private List<Game> getGames() {
        return d.getEntityManager().createQuery("from Game", Game.class).getResultList();
    }

    //Checks to see if the games table that is created by Hibernate contains any entries.
    public int checkDb(){
        d.beginTransaction();
        int dbSize = getGames().size();
        d.commitTransaction();
        return dbSize;
    }
}
