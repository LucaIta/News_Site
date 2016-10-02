import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void author_instantiatesCorrectly_true() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertTrue(newAuthor instanceof Author);
  }

  @Test
  public void getName_retrievesNameCorrectly_reporter() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getName(),"Luca");
  }

  @Test
  public void getRole_retrievesRoleCorrectly_reporter() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getRole(),"Reporter");
  }

  @Test
  public void getBio_retrievesBioCorrectly_born_in_may() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getBio(),"Born in may");
  }

  @Test
  public void getPicture_retrievesPictureCorrectly_url() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getPicture(),"www.testUrl.com");
  }

  @Test
  public void getEmail_retrievesEmailCorrectly_email() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getEmail(),"luca@gmail.com");
  }

  @Test
  public void getFacebook_retrievesFacebookContactCorrectly_facebookLink() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getFacebook(),"facebookLink");
  }

  @Test
  public void getUsername_retrievesUsernameCorrectly_LucaABC() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getUsername(),"LucaABC");
  }

  @Test
  public void getTwitter_retrievesTwitterContactCorrectly_twitterLink() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getTwitter(),"twitterLink");
  }

  @Test
  public void getPassword_returnPasswordCorrectly() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    String hashedPassword = PasswordEncrypter.getSha1SecurePassword("123456", newAuthor.getSalt());
    assertEquals(hashedPassword,newAuthor.getPassword());
  }

  // @Test
  // public void getUserIdFromUsername_returnIdCorrectly() {
  //   Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
  //   newAuthor.save();
  //   int authorId = newAuthor.getId();
  //   assertEquals(authorId, Author.getUserIdFromName("Luca"));
  // }

  @Test
  public void editName_editNameCorrectly_Mark() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editName("Mark");
    assertEquals(Author.find(newAuthor.getId()).getName(),"Mark");
  }

  @Test
  public void editRole_editRoleCorrectly_host() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editRole("Host");
    assertEquals(Author.find(newAuthor.getId()).getRole(),"Host");
  }

  @Test
  public void editBio_editBioCorrectly_bio() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editBio("Born in June");
    assertEquals(Author.find(newAuthor.getId()).getBio(),"Born in June");
  }

  @Test
  public void editPicture_editPictureCorrectly_newUrl() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editPicture("www.newUrl.com");
    assertEquals(Author.find(newAuthor.getId()).getPicture(),"www.newUrl.com");
  }

  @Test
  public void editEmail_editEmailCorrectly_newEmail() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editEmail("luca@hotmail.it");
    assertEquals(Author.find(newAuthor.getId()).getEmail(),"luca@hotmail.it");
  }

  @Test
  public void editFacebook_editFacebookCorrectly_facebookLink2() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editFacebook("facebookLink2");
    assertEquals(Author.find(newAuthor.getId()).getFacebook(),"facebookLink2");
  }

  @Test
  public void editTwitter_editTwitterCorrectly_twitterLink2() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editTwitter("twitterLink2");
    assertEquals(Author.find(newAuthor.getId()).getTwitter(),"twitterLink2");
  }

  @Test
  public void equals_compareAuthorsCorrectly_true() {
    Author newAuthor1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    Author newAuthor2 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    assertTrue(newAuthor1.equals(newAuthor2));
  }

  @Test
  public void save_savedAuthorInDatabaseCorrectly_true(){
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "LucaABC","123456",true,true,true,true,true,true);
    newAuthor.save();
    assertTrue(Author.all().get(0).equals(newAuthor));
  }


  @Test
  public void find_retrieveAuthorFromDatabaseCorrectly_author() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "LucaABC","123456",true,true,true,true,true,true);
    newAuthor.save();
    assertTrue(Author.find(newAuthor.getId()).equals(newAuthor));
  }

  @Test
  public void findByUsername_retrieveAuthorFromDatabaseCorrectly_author() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "LucaABC","123456",true,true,true,true,true,true);
    newAuthor.save();
    assertTrue(Author.findByUsername("LucaABC").equals(newAuthor));
  }

  @Test
  public void findArticles_retrievesAllAuthorTests_2() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "LucaABC","123456",true,true,true,true,true,true);
    Author author2 = new Author("Mark","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "MarkABC","123456",true,true,true,true,true,true);
    author1.save();
    author2.save();
    Article article1 = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    Article article2 = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    Article article3 = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    article1.save();
    article2.save();
    article3.save();
    author1.add(article1);
    author1.add(article2);
    author2.add(article3);
    assertTrue(author1.findArticles().size() == 2);
  }

  @Test
  public void delete_deletesAuthorCorrectly_1() {
    Author newAuthor1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "LucaABC","123456",true,true,true,true,true,true);
    Author newAuthor2 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "LucaABC","123456",true,true,true,true,true,true);
    newAuthor1.save();
    newAuthor2.save();
    newAuthor1.delete();
    assertEquals(1, Author.all().size());
  }

  @Test
  public void checkCredentials_returnTrueIfCredentialsAreCorrectAndFalseIfTheyAreNot_true() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,true,true,true,true,true);
    newAuthor.save(); // save // check
    assertEquals(false, Author.checkCredentials("Luca","12345"));
    assertTrue(Author.checkCredentials("LucaABC","123456"));
  }

  @Test
  public void edit_editAuthorCorrectly_true() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,false,true,false,true,false);
    author1.save();
    Author author2 = new Author("Mark","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",false,true,false,true,false,true);
    author2.edit(author1.getId());
    assertEquals(author2, Author.find(author1.getId()));
  }
  @Test
  public void getCanCreateAuthor_returnParameterCorrectly_true() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,false,true,false,true,false);
    assertTrue(author1.getCanCreateAuthor());
  }

  @Test
  public void getCanCreateArticle_returnParameterCorrectly_false() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,false,true,false,true,false);
    assertTrue(!(author1.getCanCreateArticle()));
  }

  @Test
  public void getCanEditAuthor_returnParameterCorrectly_true() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,false,true,false,true,false);
    assertTrue(author1.getCanEditAuthor());
  }

  @Test
  public void getCanEditArticle_returnParameterCorrectly_false() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,false,true,false,true,false);
    assertTrue(!(author1.getCanEditArticle()));
  }

  @Test
  public void getCanDeleteArticle_returnParameterCorrectly_true() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,false,true,false,true,false);
    assertTrue(author1.getCanDeleteArticle());
  }

  @Test
  public void getCanDeleteAuthor_returnParameterCorrectly_false() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,false,true,false,true,false);
    assertTrue(!(author1.getCanDeleteAuthor()));
  }

  @Test
  public void getExistingUsernames_returnListOfUSernames_true() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,false,true,false,true,false);
    Author author2 = new Author("Mark","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaDFG","123456",true,false,true,false,true,false);
    author1.save();
    author2.save();
    assertEquals("LucaDFG", Author.getExistingUsernames().get(1));
  }

  @Test
  public void editPassword_changesPasswordCorrectly_654321() {
    Author author = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,true,true,true,true,true);
    author.save();
    int authorId = author.getId();
    String newPassword = "654321";
    author.editPassword(newPassword);
    byte[] salt = author.getSalt();
    String encryptedNewPassword = PasswordEncrypter.getSha1SecurePassword(newPassword,salt);
    assertEquals(author.getPassword(), encryptedNewPassword);
  }


}
