import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Date;
import java.text.SimpleDateFormat; // could remove if unused



public class ArticleTest {

  Date testDate = new Date();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void article_instantiatesCorrectly_true() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertTrue(newArticle instanceof Article);
  }

  @Test
  public void getTitle_retrievesTitleCorrectly_title() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getTitle(),"title");
  }

  @Test
  public void getShortTitle_retrievesShortTitleCorrectly_ShortTitle() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getShortTitle(),"shortTitle");
  }

  @Test
  public void getBody_retrievesBodyCorrectly_body() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getBody(),"body");
  }

  @Test
  public void getPicture_retrievesPictureCorrectly_picture() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getPicture(),"picture");
  }

  @Test
  public void getSubhead_retrievesSubheadCorrectly_subhead() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getSubhead(),"subhead");
  }

  @Test
  public void getSubtitle_retrievesSubtitleCorrectly_subtitle() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getSubtitle(),"subtitle");
  }

  @Test
  public void getAuthorByLine_retrievesAuthorByLineCorrectly_authorByLine() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getAuthorByLine(),"authorByLine");
  }

  @Test
  public void editTitle_editTitleCorrectly_title2() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    newArticle.editTitle("title2");
    assertEquals(Article.find(newArticle.getId()).getTitle(),"title2");
  }

  @Test
  public void editShortTitle_editShortTitleCorrectly_shortTitle2() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    newArticle.editShortTitle("shortTitle2");
    assertEquals(Article.find(newArticle.getId()).getShortTitle(),"shortTitle2");
  }

  @Test
  public void editBody_editBodyCorrectly_body2() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    newArticle.editBody("body2");
    assertEquals(Article.find(newArticle.getId()).getBody(),"body2");
  }

  @Test
  public void editPicture_editPictureCorrectly_picture2() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    newArticle.editPicture("picture2");
    assertEquals(Article.find(newArticle.getId()).getPicture(),"picture2");
  }

  @Test
  public void editSubhead_editSubheadCorrectly_subhead2() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    newArticle.editSubhead("subhead2");
    assertEquals(Article.find(newArticle.getId()).getSubhead(),"subhead2");
  }

  @Test
  public void editSubtitle_editSubtitleCorrectly_Subtitle2() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    newArticle.editSubtitle("subtitle2");
    assertEquals(Article.find(newArticle.getId()).getSubtitle(),"subtitle2");
  }

  @Test
  public void editAuthorByLine_editAuthorByLineCorrectly_authorByLine2() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    newArticle.editAuthorByLine("authorByLine2");
    assertEquals(Article.find(newArticle.getId()).getAuthorByLine(),"authorByLine2");
  }

  // @Test
  // public void creationDate_creationDateGetsCorrectlySavedOnCreation_creationDateMinorThanCurrentDate() {
  //   Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
  //   assertTrue(newArticle.getCreationDate().after(testDate));
  // }

  @Test
  public void save_savesArticlesCorrectly_true() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    assertTrue(Article.all().get(0).equals(newArticle));
  }

  @Test
  public void delete_deletesArticleCorrectly_1() {
    Article article1 = new Article("title1","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    Article article2 = new Article("title2","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    article1.save();
    article2.save();
    article1.delete();
    assertEquals(1, Article.all().size());
  }

  @Test
  public void find_retrieveArticleFromDatabaseById_article() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    assertTrue(Article.find(newArticle.getId()).equals(newArticle));
  }

  @Test
  public void edit_editArticleCorrectly_true() {
    Article newArticle = new Article("titleToBeEdited","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    int articleId = newArticle.getId();
    Article editedArticle = new Article("editedTitle","editedShortTitle","editedBody","editedPicture","editedSubhead","editedSubtitle","editedAuthor","editedAuthorByLine");
    editedArticle.edit(articleId);
    assertTrue(Article.find(articleId).equals(editedArticle));
  }



}
