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
  private Date creationDate = new Date();

  public Article(String title,String shortTitle,String body,String picture,String subhead,String subtitle,String authorByLine) {
    this.title = title;
    this.shortTitle = shortTitle;
    this.body = body;
    this.picture = picture;
    this.subhead = subhead;
    this.subtitle = subtitle;
    this.authorByLine = authorByLine;
  }

  @Override
  public boolean equals(Object article) {
    if (!(article instanceof Article)) {
      return false;
    } else {
      Article newArticle = (Article) article;
      return newArticle.getTitle().equals(this.title) && newArticle.getShortTitle().equals(this.shortTitle) && newArticle.getBody().equals(this.body) && newArticle.getPicture().equals(this.picture) && newArticle.getSubhead().equals(this.subhead) && newArticle.getSubtitle().equals(this.subtitle) && newArticle.getAuthorByLine().equals(this.authorByLine);
    }
  }

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

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT into articles (title,shortTitle,body,picture,subhead,subtitle,authorByLine,creationDate) VALUES (:title,:shortTitle,:body,:picture,:subhead,:subtitle,:authorByLine,:creationDate);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title",this.title)
        .addParameter("shortTitle",this.shortTitle)
        .addParameter("body",this.body)
        .addParameter("picture",this.picture)
        .addParameter("subhead",this.subhead)
        .addParameter("subtitle",this.subtitle)
        .addParameter("authorByLine",this.authorByLine)
        .addParameter("creationDate",this.creationDate)
        .executeUpdate().getKey();
    }
  }

  public static List<Article> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM articles;";
      return con.createQuery(sql).executeAndFetch(Article.class);
    }
  }

}
