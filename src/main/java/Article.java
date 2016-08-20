import java.util.List;
import org.sql2o.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Article {
  private String title;
  private String shortTitle;
  private String body;
  private String picture;
  private String subhead;
  private String subtitle;
  private String authorByLine;
  private int id;
  // private Date creationDate = new Date();
  private Date creationDate;

  public Article(String title,String shortTitle,String body,String picture,String subhead,String subtitle,String authorByLine) {
    this.title = title;
    this.shortTitle = shortTitle;
    this.body = body;
    this.picture = picture;
    this.subhead = subhead;
    this.subtitle = subtitle;
    this.authorByLine = authorByLine;
    // saveCreationDate();
    creationDate = new Date();
  }

  // public void saveCreationDate() {
  //   // DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
  //   creationDate =
  //   // System.out.println(dateFormat.format(creationDate));
  // }

  public String getTitle() {
    return this.title;
  }

  public String getShortTitle() {
    return this.shortTitle;
  }

  public String getBody() {
    return this.body;
  }

  public String getPicture() {
    return this.picture;
  }

  public String getSubhead() {
    return this.subhead;
  }

  public String getSubtitle() {
    return this.subtitle;
  }

  public String getAuthorByLine() {
    return this.authorByLine;
  }

  public Date getCreationDate() {
    return this.creationDate;
  }

}

// Data di publicazione (viene mosrato solo quando superato)
// Autore/autori
// Tag
