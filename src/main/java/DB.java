import org.sql2o.*;
import org.postgresql.*; // heroku

public class DB {
  public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_site", null, null); // Local DB address
  // public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-54-163-226-155.compute-1.amazonaws.com:5432/d7vl83861osals", "byrtofezemhxtz", "BDk8VdPv5Zozc_XmCLUjWg5bBw"); // Heroku DB address
}
