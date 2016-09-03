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
  public void getAuthor_retrievesAuthorByLineCorrectly_author() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getAuthor(),"author");
  }

  @Test
  public void getAuthorByLine_retrievesAuthorByLineCorrectly_authorByLine() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    assertEquals(newArticle.getAuthorByLine(),"authorByLine");
  }

  @Test
  public void getBodyPreview_returnsShorterVersionOfTheString() {
    Article article1 = new Article("titleToBeEdited","shortTitle","testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest","picture","subhead","subtitle","author","authorByLine");
    Article article2 = new Article("titleToBeEdited","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    String article1BodyPreview = article1.getBodyPreview();
    String article2BodyPreview = article2.getBodyPreview();
    assertTrue(article1BodyPreview.length() == 400);
    assertTrue(article2BodyPreview.length() == 4);
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
  public void delete_deletesArticleCorrectlyFromArticleTableAndJoinTable_1() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    Article article1 = new Article("title1","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    Article article2 = new Article("title2","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    article1.save();
    article2.save();
    newAuthor.add(article1);
    newAuthor.add(article2);
    Integer author_id = newAuthor.getId();
    article1.delete();
    int numberOfRecordsInJointTable = 0; // in this variable it will be stored the number of rows related to newAuthor
    try (Connection con = DB.sql2o.open()) {
      String jointTableRowCountsQuery = "SELECT count(*) from authors_articles WHERE author_id = :author_id;";
      numberOfRecordsInJointTable = Integer.parseInt(con.createQuery(jointTableRowCountsQuery).addParameter("author_id", author_id).executeAndFetchFirst(String.class));
    }
    assertEquals(1, numberOfRecordsInJointTable);
    assertEquals(1, newAuthor.findArticles().size());
  }

  @Test
  public void find_retrieveArticleFromDatabaseById_article() {
    Article newArticle = new Article("title","shortTitle","body","picture","subhead","subtitle","author","authorByLine");
    newArticle.save();
    assertTrue(Article.find(newArticle.getId()).equals(newArticle));
  }

  // @Test
  // public void findAllByAuthor_retrieveAllArticlesAssociatedWithAuthor_2() {
  //   Article article1 = new Article("title1","shortTitle","body","picture","subhead","subtitle","author1","authorByLine");
  //   Article article2 = new Article("title2","shortTitle","body","picture","subhead","subtitle","author1","authorByLine");
  //   Article article3 = new Article("title3","shortTitle","body","picture","subhead","subtitle","author2","authorByLine");
  //   article1.save();
  //   article2.save();
  //   article3.save();
  //   assertTrue(Article.findAllByAuthor("author1").size() == 2);
  // }



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
