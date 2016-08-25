import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;


public class AuthorTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void author_instantiatesCorrectly_true() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertTrue(newAuthor instanceof Author);
  }

  @Test
  public void getName_retrievesNameCorrectly_reporter() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getName(),"Luca");
  }

  @Test
  public void getRole_retrievesRoleCorrectly_reporter() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getRole(),"Reporter");
  }

  @Test
  public void getBio_retrievesBioCorrectly_born_in_may() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getBio(),"Born in may");
  }

  @Test
  public void getPicture_retrievesPictureCorrectly_url() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getPicture(),"www.testUrl.com");
  }

  @Test
  public void getEmail_retrievesEmailCorrectly_email() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getEmail(),"luca@gmail.com");
  }

  @Test
  public void getFacebook_retrievesFacebookContactCorrectly_facebookLink() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getFacebook(),"facebookLink");
  }

  @Test
  public void getTwitter_retrievesTwitterContactCorrectly_twitterLink() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertEquals(newAuthor.getTwitter(),"twitterLink");
  }

  @Test
  public void getPassword_returnPasswordCorrectly() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertEquals("123456",newAuthor.getPassword());
  }

  @Test
  public void editName_editNameCorrectly_Mark() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editName("Mark");
    assertEquals(Author.find(newAuthor.getId()).getName(),"Mark");
  }

  @Test
  public void editRole_editRoleCorrectly_host() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editRole("Host");
    assertEquals(Author.find(newAuthor.getId()).getRole(),"Host");
  }

  @Test
  public void editBio_editBioCorrectly_bio() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editBio("Born in June");
    assertEquals(Author.find(newAuthor.getId()).getBio(),"Born in June");
  }

  @Test
  public void editPicture_editPictureCorrectly_newUrl() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editPicture("www.newUrl.com");
    assertEquals(Author.find(newAuthor.getId()).getPicture(),"www.newUrl.com");
  }

  @Test
  public void editEmail_editEmailCorrectly_newEmail() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editEmail("luca@hotmail.it");
    assertEquals(Author.find(newAuthor.getId()).getEmail(),"luca@hotmail.it");
  }

  @Test
  public void editFacebook_editFacebookCorrectly_facebookLink2() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editFacebook("facebookLink2");
    assertEquals(Author.find(newAuthor.getId()).getFacebook(),"facebookLink2");
  }

  @Test
  public void editTwitter_editTwitterCorrectly_twitterLink2() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    newAuthor.editTwitter("twitterLink2");
    assertEquals(Author.find(newAuthor.getId()).getTwitter(),"twitterLink2");
  }

  @Test
  public void equals_compareAuthorsCorrectly_true() {
    Author newAuthor1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    Author newAuthor2 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    assertTrue(newAuthor1.equals(newAuthor2));
  }

  @Test
  public void save_savedAuthorInDatabaseCorrectly_true(){
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    assertTrue(Author.all().get(0).equals(newAuthor));
  }


  @Test
  public void find_retrieveAuthorFromDatabaseById_author() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor.save();
    assertTrue(Author.find(newAuthor.getId()).equals(newAuthor));
  }

  @Test
  public void delete_deletesAuthorCorrectly_1() {
    Author newAuthor1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    Author newAuthor2 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink", "123456",true,true,true,true,true,true);
    newAuthor1.save();
    newAuthor2.save();
    newAuthor1.delete();
    assertEquals(1, Author.all().size());
  }

  @Test
  public void checkCredentials_returnTrueIfCredentialsAreCorrectAndFalseIfTheyAreNot_true() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","123456",true,true,true,true,true,true);
    newAuthor.save();
    assertEquals(false, Author.checkCredentials("Luca","12345"));
    assertTrue(Author.checkCredentials("Luca","123456"));
  }

  @Test
  public void edit_editAuthorCorrectly_true() {
    Author author1 = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","123456",true,true,true,true,true,true);
    author1.save();
    Author author2 = new Author("Mark","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","123456",true,true,true,true,true,true);
    author2.edit(author1.getId());
    assertEquals(author2, Author.find(author1.getId()));
  }

}
