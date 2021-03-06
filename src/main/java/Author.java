import java.util.List;
import java.security.NoSuchAlgorithmException;
import org.sql2o.*;
import java.util.ArrayList;


public class Author {
  private String username;
  private String name;
  private String role;
  private String bio;
  private String picture;
  private String email;
  private String facebook;
  private String twitter;
  private int id;
  private byte[] salt;
  private String password;
  private boolean canCreateAuthor;
  private boolean canCreateArticle;
  private boolean canEditAuthor;
  private boolean canEditArticle;
  private boolean canDeleteArticle;
  private boolean canDeleteAuthor;

  public Author(String name,String role,String bio,String picture,String email,String facebook,String twitter,String username,String password,   boolean canCreateAuthor, boolean canCreateArticle, boolean canEditAuthor, boolean canEditArticle,boolean canDeleteArticle,boolean canDeleteAuthor) {
    this.name = name;
    this.role = role;
    this.bio = bio;
    this.picture = picture;
    this.email = email;
    this.facebook = facebook;
    this.twitter = twitter;
    this.username = username;
    this.salt = PasswordEncrypter.getSalt();
    this.password = PasswordEncrypter.getSha1SecurePassword(password,this.salt);
    this.canCreateAuthor = canCreateAuthor;
    this.canCreateArticle = canCreateArticle;
    this.canEditAuthor = canEditAuthor;
    this.canEditArticle = canEditArticle;
    this.canDeleteArticle = canDeleteArticle;
    this.canDeleteAuthor = canDeleteAuthor;
  }

  @Override
  public boolean equals(Object author) {
    if (!(author instanceof Author)) {
      return false;
    } else {
      Author newAuthor = (Author) author;
      return newAuthor.getName().equals(this.name);
    }
  }

  public String getName() {
    return this.name;
  }

  public String getRole() {
    return this.role;
  }

  public String getBio() {
    return this.bio;
  }
  //
  public String getPicture() {
    return this.picture;
  }

  public String getEmail() {
    return this.email;
  }

  public String getFacebook() {
    return this.facebook;
  }

  public String getTwitter() {
    return this.twitter;
  }

  public String getUsername() {
    return this.username;
  }

  public int getId() {
    return this.id;
  }

  public String getPassword() {
    return this.password;
  }

  public byte[] getSalt() {
    return this.salt;
  }

  public boolean getCanCreateAuthor() {
    return this.canCreateAuthor;
  }

  public boolean getCanCreateArticle() {
    return this.canCreateArticle;
  }

  public boolean getCanEditAuthor() {
    return this.canEditAuthor;
  }

  public boolean getCanEditArticle() {
    return this.canEditArticle;
  }

  public boolean getCanDeleteArticle() {
    return this.canDeleteArticle;
  }

  public boolean getCanDeleteAuthor() {
    return this.canDeleteAuthor;
  }

  public void editName(String name) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE authors SET name = :name WHERE id = :id";
      con.createQuery(sql).addParameter("name",name).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editRole(String role) {
    try (Connection con = DB.sql2o.open()) {
    String sql = "UPDATE authors SET role = :role WHERE id = :id";
    con.createQuery(sql).addParameter("role",role).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editBio(String bio) {
    try (Connection con = DB.sql2o.open()) {
    String sql = "UPDATE authors SET bio = :bio WHERE id = :id";
    con.createQuery(sql).addParameter("bio",bio).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editPicture(String picture) {
    try (Connection con = DB.sql2o.open()) {
    String sql = "UPDATE authors SET picture = :picture WHERE id = :id";
    con.createQuery(sql).addParameter("picture",picture).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editEmail(String email) {
    try (Connection con = DB.sql2o.open()) {
    String sql = "UPDATE authors SET email = :email WHERE id = :id";
    con.createQuery(sql).addParameter("email",email).addParameter("id", this.id).executeUpdate();
    }
  }
  //
  public void editFacebook(String facebook) {
    try (Connection con = DB.sql2o.open()) {
    String sql = "UPDATE authors SET facebook = :facebook WHERE id = :id";
    con.createQuery(sql).addParameter("facebook",facebook).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editTwitter(String twitter) {
    try (Connection con = DB.sql2o.open()) {
    String sql = "UPDATE authors SET twitter = :twitter WHERE id = :id";
    con.createQuery(sql).addParameter("twitter",twitter).addParameter("id", this.id).executeUpdate();
    }
  }

  public void editPassword(String password) {
    try (Connection con = DB.sql2o.open()) {
    byte[] salt = PasswordEncrypter.getSalt();
    String encryptedPassword = PasswordEncrypter.getSha1SecurePassword(password,salt);
    String sql = "UPDATE authors SET password = :password, salt = :salt WHERE id = :id";
    con.createQuery(sql).addParameter("password",encryptedPassword).addParameter("salt",salt).addParameter("id", this.id).executeUpdate();
    this.password = encryptedPassword;
    this.salt = salt;
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT into authors (name,role,bio,picture,email,facebook,twitter,username,salt,password,canCreateAuthor,canCreateArticle,canEditAuthor,canEditArticle,canDeleteArticle,canDeleteAuthor) VALUES (:name,:role,:bio,:picture,:email,:facebook,:twitter,:username,:salt,:password,:canCreateAuthor,:canCreateArticle,:canEditAuthor,:canEditArticle,:canDeleteArticle,:canDeleteAuthor);";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name",this.name)
        .addParameter("role",this.role)
        .addParameter("bio",this.bio)
        .addParameter("picture",this.picture)
        .addParameter("email",this.email)
        .addParameter("facebook",this.facebook)
        .addParameter("twitter",this.twitter)
        .addParameter("username",this.username)
        .addParameter("salt",this.salt)
        .addParameter("password", this.password)
        .addParameter("canCreateAuthor",this.canCreateAuthor)
        .addParameter("canCreateArticle",this.canCreateArticle)
        .addParameter("canEditAuthor",this.canEditAuthor)
        .addParameter("canEditArticle",this.canEditArticle)
        .addParameter("canDeleteArticle",this.canDeleteArticle)
        .addParameter("canDeleteAuthor",this.canDeleteAuthor)
        .executeUpdate().getKey();
    }
  }

  public static List<String> getExistingUsernames() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT username FROM authors;";
      return con.createQuery(sql).executeAndFetch(String.class);
    }
  }



  public void edit(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "UPDATE authors SET name = :name, role = :role ,bio = :bio, picture = :picture,email = :email,facebook = :facebook,twitter = :twitter,username = :username ,canCreateAuthor = :canCreateAuthor,canCreateArticle = :canCreateArticle,canEditAuthor = :canEditAuthor,canEditArticle = :canEditArticle,canDeleteArticle = :canDeleteArticle,canDeleteAuthor = :canDeleteAuthor WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name",this.name)
        .addParameter("role",this.role)
        .addParameter("bio",this.bio)
        .addParameter("picture",this.picture)
        .addParameter("email",this.email)
        .addParameter("facebook",this.facebook)
        .addParameter("twitter",this.twitter)
        .addParameter("username",this.username)
        .addParameter("canCreateAuthor",this.canCreateAuthor)
        .addParameter("canCreateArticle",this.canCreateArticle)
        .addParameter("canEditAuthor",this.canEditAuthor)
        .addParameter("canEditArticle",this.canEditArticle)
        .addParameter("canDeleteArticle",this.canDeleteArticle)
        .addParameter("canDeleteAuthor",this.canDeleteAuthor)
        .addParameter("id",id)
        .executeUpdate();
    }
  }


  public void delete() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM authors WHERE id = :id;";
      con.createQuery(sql).addParameter("id", this.id).executeUpdate();
    }
  }

  public static List<Author> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors order by id desc;";
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

  public static Author find(int id) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors WHERE id = :id;";
      return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(Author.class);
    }
  }

  public static Author findByUsername(String username) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors WHERE username = :username;";
      return con.createQuery(sql).addParameter("username",username).executeAndFetchFirst(Author.class);
    }
  }

  public void add (Article article) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT into authors_articles (author_id,article_id) VALUES (:author_id,:article_id);";
      con.createQuery(sql)
        .addParameter("author_id", this.id)
        .addParameter("article_id", article.getId())
        .executeUpdate();
    }
  }

  public static boolean checkCredentials(String username, String password) {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors WHERE username = :username;";
      Author author = con.createQuery(sql).addParameter("username", username).executeAndFetchFirst(Author.class);
      if (author == null) {
        return false;
      } else {
        byte[] salt = author.getSalt();
        String hashedPassword = PasswordEncrypter.getSha1SecurePassword(password,salt);
        return author.getPassword().equals(hashedPassword);
      }
    }
  }

  public List<Article> findArticles() {
    try (Connection con = DB.sql2o.open()) {
      String articleIdsQuery = "SELECT article_id FROM authors_articles WHERE author_id = :author_id order by id desc";
      List<Integer> articleIds = con.createQuery(articleIdsQuery).addParameter("author_id",this.id).executeAndFetch(Integer.class);
      List<Article> articles = new ArrayList<Article>();
      for (int articleId : articleIds) {
        String articlesQuery = "SELECT * FROM articles WHERE id = :id;";
        Article article = con.createQuery(articlesQuery).addParameter("id", articleId).executeAndFetchFirst(Article.class);
        articles.add(article);
      }
      return articles;
    }
  }

}
