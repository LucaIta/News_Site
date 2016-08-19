import org.junit.*;
import static org.junit.Assert.*;

public class AuthorTest {

  @Test
  public void author_instantiatesCorrectly_true() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertTrue(newAuthor instanceof Author);
  }

  @Test
  public void getRole_retrievesRoleCorrectly_reporter() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getRole(),"Reporter");
  }

  // this.email = email;
  // this.facebook = facebook;
  // this.twitter = twitter;


  //
  @Test
  public void getBio_retrievesBioCorrectly_born_in_may() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getBio(),"Born in may");
  }
  //
  @Test
  public void getPicture_retrievesPictureCorrectly_url() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getPicture(),"www.testUrl.com");
  }
  //
  @Test
  public void getEmail_retrievesEmailCorrectly_email() {
    Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
    assertEquals(newAuthor.getEmail(),"luca@gmail.com");
  }
  //
  // @Test
  // public void getName_retrievesNameCorrectly_Luca() {
  //   Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
  //   assertEquals(newAuthor.getName(),"Luca");
  // }
  //
  // @Test
  // public void getName_retrievesNameCorrectly_Luca() {
  //   Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
  //   assertEquals(newAuthor.getName(),"Luca");
  // }
  //
  // @Test
  // public void getName_retrievesNameCorrectly_Luca() {
  //   Author newAuthor = new Author("Luca","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink");
  //   assertEquals(newAuthor.getName(),"Luca");
  // }
}
