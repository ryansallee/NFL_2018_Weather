package org.codelouisville.Logic;

import org.codelouisville.Models.Game;

import java.util.List;

public class Queries {
    private final DBFunctions d;

    public Queries(DBFunctions d){
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
