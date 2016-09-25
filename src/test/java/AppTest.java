import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.Rule;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.sql2o.*;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  // Here I store the methods for the tests repetitive actions

  public Author createUserAndLogin() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    goTo("http://localhost:4567/");
    fill("#username").with("LucaABC");
    fill("#password").with("123456");
    submit("#loginBtn");
    return newAuthor;
  }

  public Article createArticleAndSave() {
    Article newArticle = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    newArticle.save();
    return newArticle;
  }

  public static void createsAndSaveMultipleArticles(int numberOfArticlesToCreate) {
    for (int i = 0 ; i < numberOfArticlesToCreate ; i ++) {
      Article article = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
      article.save();
    }
  }

  //

  @Test
  public void loginPageIsDisplayed() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Username");
  }

  @Test
  public void newArticlePageIsDisplayed() {
    goTo("http://localhost:4567/articles/new");
    assertThat(pageSource()).contains("Add an Article");
  }

  @Test
  public void newAuthorPageIsDisplayed() {
    goTo("http://localhost:4567/authors/new");
    assertThat(pageSource()).contains("Add an Author");
  }

  @Test
  public void loginWorksCorrectly() {
    createUserAndLogin();
    assertThat(pageSource()).contains("create a new article");
  }

  @Test
  public void whenWrongCredentialsAreInsertedErrorMessageIsDisplayed() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    goTo("http://localhost:4567/");
    fill("#username").with("MarkABC");
    fill("#password").with("123456");
    submit("#loginBtn");
    assertThat(pageSource()).contains("Inserted Username or Password were incorrect, try again");
  }

  @Test
  public void linksOnHubPageWorks() {
    createUserAndLogin();
    submit("#newArticleBtn");
    assertThat(pageSource().contains("Article Title"));
    goTo("http://localhost:4567/hub");
    submit("#newAuthorBtn");
    assertThat(pageSource()).contains("Author Name");
  }

  @Test
  public void articlesGetsDisplayed() {
    Author author = createUserAndLogin();
    Article article = createArticleAndSave();
    author.add(article);
    goTo("http://localhost:4567/hub");
    assertThat(pageSource()).contains("div");
  }

  @Test
  public void articlePageIsDisplayed() {
    Author author = createUserAndLogin();
    Article newArticle = createArticleAndSave();
    String url = String.format("http://localhost:4567/articles/%d",newArticle.getId());
    goTo(url);
    assertThat(pageSource()).contains("How to learn English");
    assertThat(pageSource()).contains("subhead");
    assertThat(pageSource()).contains("An Easy Way to Learn English");
    assertThat(pageSource()).contains("LucaABC");
    assertThat(pageSource()).contains("picture");
    assertThat(pageSource()).contains("this article is about...");
  }

  @Test
  public void articleEditPageIsDisplayed() {
    Author author = createUserAndLogin();
    Article newArticle = createArticleAndSave();
    author.add(newArticle);
    goTo("http://localhost:4567/hub");
    click("a", withText("How to learn English"));
    click(".btn", withText("Edit Article"));
    // click("a", withText("How to learn English"));
    assertThat(pageSource()).contains("An Easy Way to Learn English");
    assertThat(pageSource()).contains("this article is about...");
    assertThat(pageSource()).contains("picture");
  }

  @Test
  public void articleGetsEditedCorrectly() {
    Author author = createUserAndLogin();
    Article newArticle = createArticleAndSave();
    author.add(newArticle);
    String url = String.format("http://localhost:4567/articles/%d/edit", newArticle.getId());
    goTo(url);
    fill("#newTitle").with("editedTitle");
    fill("#newShortTitle").with("editedShortTitle");
    fill("#newBody").with("editedBody");
    fill("#newPicture").with("editedPicture");
    fill("#newSubhead").with("editedSubhead");
    fill("#newSubtitle").with("editedSubtitle");
    fill("#newAuthorByLine").with("editedAuthorByLine");
    submit("#editBtn");
    assertThat(pageSource()).contains("create a new article");
    assertThat(pageSource()).contains("editedTitle");
    goTo(url);
    assertThat(pageSource()).contains("editedTitle");
    assertThat(pageSource()).contains("editedShortTitle");
    assertThat(pageSource()).contains("editedBody");
    assertThat(pageSource()).contains("editedPicture");
    assertThat(pageSource()).contains("editedSubhead");
    assertThat(pageSource()).contains("editedSubtitle");
    assertThat(pageSource()).contains("editedAuthorByLine");
  }

  @Test
  public void articleGetsDelitedCorrectly() {
    Author author = createUserAndLogin();
    Article article1 = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","authorByLine");
    Article article2 = new Article("How to learn Spanish","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","authorByLine");
    article1.save();
    article2.save();
    author.add(article1);
    author.add(article2);
    String url = String.format("http://localhost:4567/articles/%d/edit", article1.getId());
    goTo(url);
    submit("#deleteBtn");
    assertThat(pageSource()).contains("How to learn Spanish");
    assertThat(pageSource()).doesNotContain("How to learn English");
  }

  @Test
  public void authorsGetDisplayed() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    goTo("http://localhost:4567/authors");
    assertThat(pageSource()).contains("Luca M");
  }

  @Test
  public void authorsGetEditedCorrectly() {
    Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    newAuthor.save();
    goTo("http://localhost:4567/authors");
    click("a", withText("Luca M"));
    fill("#newName").with("editedName");
    fill("#newRole").with("editedRole");
    fill("#newBio").with("editedBio");
    fill("#newPicture").with("editedPicture");
    fill("#newEmail").with("editedEmail");
    fill("#newFacebook").with("editedFacebook");
    fill("#newTwitter").with("editedTwitter");
    fill("#newUsername").with("editedUsername");
    click("#canCreateAuthor"); // here I'm clicking in order to unflag those checkboxes
    click("#canEditAuthor");
    click("#canDeleteAuthor");
    submit("#editBtn");
    click("a", withText("editedName"));
    assertThat(pageSource()).contains("editedName");
    assertThat(pageSource()).contains("editedRole");
    assertThat(pageSource()).contains("editedBio");
    assertThat(pageSource()).contains("editedPicture");
    assertThat(pageSource()).contains("editedEmail");
    assertThat(pageSource()).contains("editedFacebook");
    assertThat(pageSource()).contains("editedTwitter");
    assertThat(pageSource()).contains("editedUsername");

    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors";
      newAuthor = con.createQuery(sql).executeAndFetchFirst(Author.class);
    }
    assertTrue(newAuthor.getCanCreateArticle()
        && newAuthor.getCanEditArticle()
        && newAuthor.getCanDeleteArticle());
  }

  @Test
  public void authorsGetDelitedCorrectly() {
    Author author1 = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,true,true,true,true,true);
    Author author2 = new Author("Mark M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC","123456",true,true,true,true,true,true);
    author1.save();
    author2.save();
    String url = String.format("http://localhost:4567/authors/%d/edit", author1.getId());
    goTo(url);
    submit("#deleteBtn");
    assertThat(pageSource()).contains("Mark M");
    assertThat(pageSource()).doesNotContain("Luca M");
  }

  @Test
  public void authorsGetsCreatedCorrectly() {
    goTo("http://localhost:4567/authors/new");
    fill("#name").with("Name");
    fill("#role").with("Role");
    fill("#bio").with("Bio");
    fill("#picture").with("Picture");
    fill("#email").with("Email");
    fill("#facebook").with("Facebook");
    fill("#twitter").with("Twitter");
    fill("#username").with("Username");
    click("#canCreateAuthor");
    click("#canCreateArticle");
    click("#canEditAuthor");
    click("#canEditArticle");
    click("#canDeleteAuthor");
    click("#canDeleteArticle");
    submit("#createAuthorBtn");
    goTo("http://localhost:4567/authors");
    click("a", withText("Name"));
    assertThat(pageSource()).contains("Name");
    assertThat(pageSource()).contains("Role");
    assertThat(pageSource()).contains("Bio");
    assertThat(pageSource()).contains("Picture");
    assertThat(pageSource()).contains("Email");
    assertThat(pageSource()).contains("Facebook");
    assertThat(pageSource()).contains("Twitter");
    Author newAuthor;
    try (Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM authors";
      newAuthor = con.createQuery(sql).executeAndFetchFirst(Author.class);
    }
    assertTrue(newAuthor.getCanCreateAuthor()
        && newAuthor.getCanCreateArticle()
        && newAuthor.getCanEditAuthor()
        && newAuthor.getCanEditArticle()
        && newAuthor.getCanDeleteAuthor()
        && newAuthor.getCanDeleteArticle());
  }

  @Test
  public void ifUsernameIsAlreadyTakenAuthorDoesNotGetCreatedAndErrorIsDisplayed() {
    Author author1 = createUserAndLogin();
    goTo("http://localhost:4567/authors/new");
    fill("#username").with("LucaABC");
    submit("#createAuthorBtn");
    assertThat(pageSource()).contains("selected username was already taken, please select a different username");
    assertEquals(1,Author.all().size());
  }

  @Test
  public void ifNoArticleIsCreatedTheUserIsInformedAboutIt() {
    createUserAndLogin();
    goTo("http://localhost:4567/hub");
    assertThat(pageSource()).contains("You haven't created any article yet");
  }

  @Test
  public void authorByLineIsDisplayedOnlyWhenItIsInserted() {
    createUserAndLogin();
    Article newArticle = createArticleAndSave();
    String articleDetailUrl = String.format("http://localhost:4567/articles/%d", newArticle.getId());
    goTo(articleDetailUrl);
    assertThat(pageSource()).doesNotContain("and"); // I'm using and to check because this word is displayed only when the author by line is inserted as in "By Luca and Mark" where Mark is the authorByLine
    String articleEditUrl = String.format("http://localhost:4567/articles/%d/edit", newArticle.getId());
    goTo(articleEditUrl);
    fill("#newAuthorByLine").with("MarkABC");
    submit("#editBtn");
    goTo(articleDetailUrl);
    assertThat(pageSource()).contains("and MarkABC");
  }

  @Test
  public void viewArticlesButtonShowsAllArticles() {
    createUserAndLogin();
    Article article1 = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    article1.save();
    Article article2 = new Article("How to learn German","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    article2.save();
    Author differentAuthor = new Author("Mark M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",true,true,true,true,true,true);
    differentAuthor.save();
    goTo("http://localhost:4567/");
    fill("#username").with("Mark M");
    fill("#password").with("123456");
    submit("#loginBtn");
    goTo("http://localhost:4567/articles");
    assertThat(pageSource()).contains("How to learn English");
    assertThat(pageSource()).contains("How to learn German");
  }

  // @Test
  // public void getToUnauthorizedPageIfDoesntHavePermitsForPage() {
  //   Author newAuthor = new Author("Luca M","Reporter", "Born in may", "www.testUrl.com", "luca@gmail.com", "facebookLink", "twitterLink","LucaABC", "123456",false,false,false,false,false,false);
  //   newAuthor.save();
  //   goTo("http://localhost:4567/");
  //   fill("#username").with("LucaABC");
  //   fill("#password").with("123456");
  //   submit("#loginBtn");
  //   Article newArticle = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
  //   newArticle.save();
  //   String articleEditUrl = String.format("http://localhost:4567/articles/%d/edit", newArticle.getId());
  //   goTo(articleEditUrl);
  //   assertThat(pageSource()).contains("You tried to access a section which you don't have the permits for, please contact the administrator");
  // }

  @Test
  public void homePageDisplaysArticles() {
    Article article1 = new Article("How to learn English","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    article1.save();
    Article article2 = new Article("How to learn German","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    article2.save();
    goTo("http://localhost:4567/home");
    assertThat(pageSource()).contains("How to learn English");
    assertThat(pageSource()).contains("How to learn German");
  }

  @Test
  public void secondPageContainsSecondPageArticle() {
    createsAndSaveMultipleArticles(12);
    Article article = new Article("Second Page Article","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    article.save();
    goTo("http://localhost:4567/home/2");
    assertThat(pageSource()).contains("Second Page Article");
    assertThat(pageSource()).doesNotContain("How to learn German");
  }

  @Test
  public void clickingNextPageButtonBringsToNextPage() {
    createsAndSaveMultipleArticles(24);
    Article article = new Article("Second Page Article","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    article.save();
    goTo("http://localhost:4567/home/2");
    click("#nextBtn");
    assertThat(pageSource()).contains("Second Page Article");
    assertThat(pageSource()).doesNotContain("How to learn German");
  }

  @Test
  public void clickingPreviousPageButtonBringsToPreviousPage() {
    Article article = new Article("First Page Article","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    article.save();
    createsAndSaveMultipleArticles(12);
    goTo("http://localhost:4567/home/2");
    click("#previousBtn");
    assertThat(pageSource()).contains("First Page Article");
  }

  @Test
  public void articleDetailPageIsReachableFromReadersHomePage() {
    Article article = new Article("First Page Article","shortTitle","this article is about...","picture","subhead","An Easy Way to Learn English","LucaABC","");
    article.save();
    goTo("http://localhost:4567/home");
    click("a", withText("First Page Article"));
    assertThat(pageSource()).contains("LucaABC");
  }



}
