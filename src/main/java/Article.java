import java.util.List;
import org.sql2o.*;
import java.util.Date;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Article {
  private String title;
  private String shortTitle;
  private String body;
  private String picture;
  private String subhead;
  private String subtitle;
  private String author;
  private String authorByLine;
  private int id;
  private Date creationDate = new Date();

  public Article(String title,String shortTitle,String body,String picture,String subhead,String subtitle,String author,String authorByLine) {
    this.title = title;
    this.shortTitle = shortTitle;
    this.body = body;
    this.picture = picture;
    this.subhead = subhead;
    this.subtitle = subtitle;
    this.author = author;
    this.authorByLine = authorByLine;
  }

  @Override
  public boolean equals(Object article) {
    if (!(article instanceof Article)) {
      return false;
    } else {
      Article newArticle = (Article) article;
      return newArticle.getTitle().equals(this.title) && newArticle.getShortTitle().equals(this.shortTitle) && newArticle.getBody().equals(this.body) && newArticle.getPicture().equals(this.picture) && newArticle.getSubhead().equals(this.subhead) && newArticle.getSubtitle().equals(this.subtitle) && newArticle.getAuthor().equals(this.author) && newArticle.getAuthorByLine().equals(this.authorByLine);
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

  public String getAuthor() {
    return this.author;
  }

  public String getAuthorByLine() {
    return this.authorByLine;
  }

  public Date getCreationDate() {
    return this.creationDate;
  }

  public int getId() {
    return this.id;
  }

  public void editTitle(String title) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE articles SET title = :title WHERE id = :id";
      con.createQuery(sql).addParameter("title",title).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editShortTitle(String shortTitle) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE articles SET shortTitle = :shortTitle WHERE id = :id";
      con.createQuery(sql).addParameter("shortTitle",shortTitle).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editBody(String body) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE articles SET body = :body WHERE id = :id";
      con.createQuery(sql).addParameter("body",body).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editPicture(String picture) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE articles SET picture = :picture WHERE id = :id";
      con.createQuery(sql).addParameter("picture",picture).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editSubhead(String subhead) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE articles SET subhead = :subhead WHERE id = :id";
      con.createQuery(sql).addParameter("subhead",subhead).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editSubtitle(String subtitle) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE articles SET subtitle = :subtitle WHERE id = :id";
      con.createQuery(sql).addParameter("subtitle",subtitle).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editAuthorByLine(String authorByLine) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE articles SET authorByLine = :authorByLine WHERE id = :id";
      con.createQuery(sql).addParameter("authorByLine",authorByLine).addParameter("id", this.id).executeUpdate();
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT into articles (title,shortTitle,body,picture,subhead,subtitle,author,authorByLine,creationDate) VALUES (:title,:shortTitle,:body,:picture,:subhead,:subtitle,:author,:authorByLine,:creationDate);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title",this.title)
        .addParameter("shortTitle",this.shortTitle)
        .addParameter("body",this.body)
        .addParameter("picture",this.picture)
        .addParameter("subhead",this.subhead)
        .addParameter("subtitle",this.subtitle)
        .addParameter("author",this.author)
        .addParameter("authorByLine",this.authorByLine)
        .addParameter("creationDate",this.creationDate)
        .executeUpdate().getKey();
    }
  }

  public void edit(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE articles SET title = :title, shortTitle = :shortTitle ,body = :body, picture = :picture,subhead = :subhead,subtitle = :subtitle,author = :author,authorByLine = :authorByLine WHERE id = :id";
      con.createQuery(sql)
        .addParameter("title",this.title)
        .addParameter("shortTitle",this.shortTitle)
        .addParameter("body",this.body)
        .addParameter("picture",this.picture)
        .addParameter("subhead",this.subhead)
        .addParameter("subtitle",this.subtitle)
        .addParameter("author",this.author)
        .addParameter("authorByLine",this.authorByLine)
        .addParameter("id",id)
        .executeUpdate();
    }
  }

  // the SQL string is correct, if I insert it manually, it works ...

  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM articles WHERE id = :id;";
      con.createQuery(sql).addParameter("id", this.id).executeUpdate();
      String sql2 = "DELETE FROM authors_articles WHERE article_id = :id;";
      con.createQuery(sql2).addParameter("id", this.id).executeUpdate();
    }
  }

  public static List<Article> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM articles;";
      return con.createQuery(sql).executeAndFetch(Article.class);
    }
  }

  public static Article find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM articles WHERE id = :id;";
      return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Article.class);
    }
  }

  public String getBodyPreview(int previewLenght) {
    if (this.body.length() > previewLenght) {
      return this.body.substring(0,previewLenght);
    } else {
      return this.body;
    }
  }

  public static List<Article> getArticlesForPage(int pageNumber) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM articles limit 12 offset :offset";
      return con.createQuery(sql).addParameter("offset", (pageNumber - 1) * 12).executeAndFetch(Article.class); // with the parameter offset I'm setting which line in the database I'm starting to retrieve the articles from
    }
  }

  public static boolean isLastPage(double pageNumber) {
    double numberOfPages = (double) Article.all().size() / 12;
    if (Double.compare(pageNumber, numberOfPages) >= 0) {
      return true;
    } else {
      return false;
    }
  }

  // public List<Article> getArticlesForPage(int pageNumber) {  ???? will this be good?
  //   List<Article> articles = new ArrayList<Article>();
  //   String sql = "SELECT * FROM articles limit 12 offset :offset";
  //   // SELECT * FROM articles limit 2 offset 2; limit is the amount of lines, offset is where to start getting them
  // }


}
