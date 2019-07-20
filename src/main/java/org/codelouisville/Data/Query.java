package org.codelouisville.Data;

import org.codelouisville.Models.Game;

import java.util.List;

public class Query {
    private final DBFunctions d;

    public Query(DBFunctions d){
        this.d = d;
    }

    public List<Game> getGamesfromDb(){
        d.beginTransaction();
        List<Game> games = getGames();
        d.commit();
        return games;
    }

    private List<Game> getGames() {
        return d.getEntityManager().createQuery("from Game", Game.class).getResultList();
    }

    public int checkDb(){
        d.beginTransaction();
        int dbSize = getGames().size();
        d.commit();
        return dbSize;
    }
}
