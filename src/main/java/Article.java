import java.util.List;
import org.sql2o.*;

public class Article {
  private String title;
  private String short_title;
  private String body;
  private String picture;
  private String subhead;
  private String subtitle;
  private String author_by_line;
  private int id;

  public Article(String title,String short_title,String body,String picture,String subhead,String subtitle,String author_by_line) {
    this.title = title;
    this.short_title = short_title;
    this.body = body;
    this.picture = picture;
    this.subhead = subhead;
    this.subtitle = subtitle;
    this.author_by_line = author_by_line;
  }

}

// Data di creazione
// Data di publicazione (viene mosrato solo quando superato)
// Autore/autori
// Tag
