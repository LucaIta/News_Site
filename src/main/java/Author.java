public class Author {
  private String name;
  private String role;
  private String bio;
  private String picture;
  private String email;
  private String facebook;
  private String twitter;

  public Author(String name,String role,String bio,String picture,String email,String facebook,String twitter) {
    this.name = name;
    this.role = role;
    this.bio = bio;
    this.picture = picture;
    this.email = email;
    this.facebook = facebook;
    this.twitter = twitter;
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
  //
  // public String getName() {
  //   return this.name;
  // }
  //
  // public String getName() {
  //   return this.name;
  // }
  //
  // public String getName() {
  //   return this.name;
  // }

}
