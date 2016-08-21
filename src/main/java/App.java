import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main (String[] args){

    get("/articles/new", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/article-form.vtl");
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post("/articles", (request,response) -> {
      // code that get from the session the current user
      Author currentAuthor = Author.find(662); // here I'm retrieving always the same user, to get rid of when I implement the login system

      String title = request.queryParams("title");
      String shortTitle = request.queryParams("shortTitle");
      String body = request.queryParams("body");
      String picture = request.queryParams("picture");
      String subhead = request.queryParams("subhead");
      String subtitle = request.queryParams("subtitle");
      String authorByLine = request.queryParams("authorByLine");
      Article newArticle = new Article(title,shortTitle,body,picture,subhead,subtitle,authorByLine);
      newArticle.save();

      currentAuthor.add(newArticle); // here we associate the Author and the Article

      response.redirect("/articles/new");
      return null;
    });

    get("/authors/new", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/author-form.vtl");
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post("/authors", (request,response) -> {
      String name = request.queryParams("name");
      String role = request.queryParams("role");
      String bio = request.queryParams("bio");
      String picture = request.queryParams("picture");
      String email = request.queryParams("email");
      String facebook = request.queryParams("facebook");
      String twitter = request.queryParams("twitter");
      Author newAuthor = new Author(name,role,bio,picture,email,facebook,twitter);
      newAuthor.save();
      response.redirect("/authors/new");
      return null;
    });

  }
}
