import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test public void loginPageIsDisplayed() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Username");
  }

  @Test public void newArticlePageIsDisplayed() {
    goTo("http://localhost:4567/articles/new");
    assertThat(pageSource()).contains("Add an Article");
  }

  @Test public void newAuthorPageIsDisplayed() {
    goTo("http://localhost:4567/authors/new");
    assertThat(pageSource()).contains("Add an Author");
  }

  @Test public void loginWorksCorrectly() {
    goTo("http://localhost:4567/");
    fill("#username").with("Luca");
    fill("#password").with("123456");
    submit("#loginBtn");
    assertThat(pageSource()).contains("click here to create a new Article");
  }

  @Test public void linksOnHubPageWorks() {
    goTo("http://localhost:4567/hub");
    submit("#newArticleBtn");
    assertThat(pageSource().contains("Article Title"));
    // goTo("http://localhost:4567/hub");
    // submit("#newAuthorBtn");
    // assertThat(pageSource().contains("Author Name"));
  }



  // @Test
  // public void bandGetUpdated() {
  //   Band newBand = new Band("The Music Band", 4);
  //   newBand.save();
  //   String url = String.format("http://localhost:4567/band/%d", newBand.getId());
  //   goTo(url);
  //   click("option", withText("Edit band Name"));
  //   fill("#newValue").with("Sex Bob-omb");
  //   submit("#editBand");
  //   assertThat(pageSource()).contains("Sex Bob-omb");
  //   click("option", withText("Edit band members number"));
  //   fill("#newValue").with("6");
  //   submit("#editBand");
  //   assertThat(pageSource()).contains("Number of members: 6");
  // }

}
