package org.codelouisville.Logic;

import org.codelouisville.Models.Game;

import java.util.List;

public class fromDatabase {
    private DBFunctions d;

    public fromDatabase(DBFunctions d){
        this.d = d;
    }

    public List<Game> getGamesfromDb(){
        d.beginTransaction();
        List<Game> games = d.getEntityManager().createQuery("from Game", Game.class).getResultList();
        d.commit();
        return games;
    }
}
