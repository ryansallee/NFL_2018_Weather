package org.codelouisville;


import org.codelouisville.Logic.DBFunctions;
import org.codelouisville.Logic.DbSeed;
import org.codelouisville.Logic.Queries;
import org.codelouisville.Logic.ReadCsv;
import org.codelouisville.Models.Game;

import java.io.IOException;
import java.util.List;

public class Main {
    private static DBFunctions getDbFunctions() {
        return dbFunctions;
    }

    private static Queries getQueries() {
        return queries;
    }

    private static DBFunctions dbFunctions = new DBFunctions();
    private static Queries queries = new Queries(dbFunctions);

    public static void main(String[] args){
      List<Game> games = null;
      if(getQueries().checkDb() <=0) {
          try {
              games = ReadCsv.readingCSV(queries);
          } catch (IOException e) {
              e.printStackTrace();
          }
          System.out.println("Hey! We're seeding the db!");
          DbSeed dbSeed = new DbSeed(getDbFunctions());
          dbSeed.seed(games,queries);
          games = getQueries().getGamesfromDb();
      } else{
          System.out.println("We didn't need to seed the db!");
          games = getQueries().getGamesfromDb();
      }

        games.forEach(g -> System.out.println(g.toString()));
        getDbFunctions().closeEntityManager();
        getDbFunctions().closeEntityManagerFactory();
        getDbFunctions().stopServer();

        System.out.println("Press enter to quit;");
        }


}




