import java.util.List;
import org.sql2o.*;

public class Author {
  private String name;
  private String role;
  private String bio;
  private String picture;
  private String email;
  private String facebook;
  private String twitter;
  private int id;

  public Author(String name,String role,String bio,String picture,String email,String facebook,String twitter) {
    this.name = name;
    this.role = role;
    this.bio = bio;
    this.picture = picture;
    this.email = email;
    this.facebook = facebook;
    this.twitter = twitter;
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

  public void editName(String name) {
    this.name = name;
  }

  public void editRole(String role) {
    this.role = role;
  }

  public void editBio(String bio) {
    this.bio = bio;
  }

  public void editPicture(String picture) {
    this.picture = picture;
  }

  public void editEmail(String email) {
    this.email = email;
  }
  //
  public void editFacebook(String facebook) {
    this.facebook = facebook;
  }

  public void editTwitter(String twitter) {
    this.twitter = twitter;
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT into authors (name,role,bio,picture,email,facebook,twitter) VALUES (:name,:role,:bio,:picture,:email,:facebook,:twitter);";
      con.createQuery(sql)
        .addParameter("name",this.name)
        .addParameter("role",this.role)
        .addParameter("bio",this.bio)
        .addParameter("picture",this.picture)
        .addParameter("email",this.email)
        .addParameter("facebook",this.facebook)
        .addParameter("twitter",this.twitter)
        .executeUpdate();
    }
  }

  public static List<Author> all() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors;";
      return con.createQuery(sql).executeAndFetch(Author.class);
    }
  }

}
