import org.junit.*;
import static org.junit.Assert.*;

public class AuthorTest {

  @Test
  public void author_instantiatesCorrectly_true() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertTrue(newAuthor instanceof Author);
  }

  @Test
  public void getName_retrievesNameCorrectly_reporter() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getName(),"Luca");
  }

  @Test
  public void getRole_retrievesRoleCorrectly_reporter() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getRole(),"Reporter");
  }

  @Test
  public void getBio_retrievesBioCorrectly_born_in_may() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getBio(),"Born in may");
  }

  @Test
  public void getPicture_retrievesPictureCorrectly_url() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getPicture(),"www.testUrl.com");
  }

  @Test
  public void getEmail_retrievesEmailCorrectly_email() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getEmail(),"luca@gmail.com");
  }

  @Test
  public void getFacebook_retrievesFacebookContactCorrectly_facebookLink() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getFacebook(),"facebookLink");
  }

  @Test
  public void getTwitter_retrievesTwitterContactCorrectly_twitterLink() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getTwitter(),"twitterLink");
  }

  @Test
  public void editName_editNameCorrectly_Mark() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    newAuthor.editName("Mark");
    assertEquals(newAuthor.getName(),"Mark");
  }

  private String facebook;
  private String twitter;

  @Test
  public void editRole_editRoleCorrectly_host() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    newAuthor.editRole("Host");
    assertEquals(newAuthor.getRole(),"Host");
  }

  @Test
  public void editBio_editBioCorrectly_bio() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    newAuthor.editBio("Born in June");
    assertEquals(newAuthor.getBio(),"Born in June");
  }

  @Test
  public void editPicture_editPictureCorrectly_newUrl() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    newAuthor.editPicture("www.newUrl.com");
    assertEquals(newAuthor.getPicture(),"www.newUrl.com");
  }

  @Test
  public void editEmail_editEmailCorrectly_newEmail() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    newAuthor.editEmail("luca@hotmail.it");
    assertEquals(newAuthor.getEmail(),"luca@hotmail.it");
  }

  @Test
  public void editFacebook_editFacebookCorrectly_facebookLink2() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    newAuthor.editFacebook("facebookLink2");
    assertEquals(newAuthor.getFacebook(),"facebookLink2");
  }

  @Test
  public void editTwitter_editTwitterCorrectly_twitterLink2() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    newAuthor.editTwitter("twitterLink2");
    assertEquals(newAuthor.getTwitter(),"twitterLink2");
  }


}
