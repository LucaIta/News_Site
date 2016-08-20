import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Date;



public class ArticleTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void article_instantiatesCorrectly_true() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    assertTrue(newArticle instanceof Article);
  }

  @Test
  public void getTitle_retrievesTitleCorrectly_title() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    assertEquals(newArticle.getTitle(),"title");
  }

  @Test
  public void getShortTitle_retrievesShortTitleCorrectly_ShortTitle() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    assertEquals(newArticle.getShortTitle(),"shortTitle");
  }

  @Test
  public void getBody_retrievesBodyCorrectly_body() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    assertEquals(newArticle.getBody(),"body");
  }

  @Test
  public void getPicture_retrievesPictureCorrectly_picture() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    assertEquals(newArticle.getPicture(),"picture");
  }

  @Test
  public void getSubhead_retrievesSubheadCorrectly_subhead() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    assertEquals(newArticle.getSubhead(),"subhead");
  }

  @Test
  public void getSubtitle_retrievesSubtitleCorrectly_subtitle() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    assertEquals(newArticle.getSubtitle(),"subtitle");
  }

  @Test
  public void getAuthorByLine_retrievesAuthorByLineCorrectly_authorByLine() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    assertEquals(newArticle.getAuthorByLine(),"authorByLine");
  }

  @Test
  public void creationDate_creationDateGetsCorrectlySavedOnCreation_creationDate() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","authorByLine");
    Date currentDate = new Date();
    assertTrue(newArticle.getCreationDate().before(currentDate));
  }



}
