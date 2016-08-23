import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main (String[] args){

    get("/", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/index.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    },new VelocityTemplateEngine());

    post("/login", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      String username = request.queryParams("username");
      String password = request.queryParams("password");
      if (Author.checkCredentials(username,password)) {
        model.put("template", "/templates/hub.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      } else {
        model.put("template", "/templates/index.vtl");
        return new ModelAndView(model, "templates/layout.vtl");
      }
    },new VelocityTemplateEngine());

    get("/hub", (request,response) -> { // for this and other paths, I should be able to get there only if the user is autenticated
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/hub.vtl");
      model.put("articles", Article.all());
      return new ModelAndView(model, "templates/layout.vtl");
    },new VelocityTemplateEngine());

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

    post("/articles/:article_id/delete", (request,response) -> {
      int articleId = Integer.parseInt(request.params("article_id"));
      Article articleToDelete = Article.find(articleId);
      articleToDelete.delete();
      response.redirect("/hub");
      return null;
    });

    get("/articles/:article_id/edit", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/article-edit-form.vtl");
      model.put("article", Article.find(Integer.parseInt(request.params("article_id"))));
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post("/articles/:article_id/edit", (request,response) -> {

      HashMap<String,Object> model = new HashMap<String,Object>();
      String title = request.queryParams("newTitle");
      String shortTitle = request.queryParams("newShortTitle");
      String body = request.queryParams("newBody");
      String picture = request.queryParams("newPicture");
      String subhead = request.queryParams("newSubhead");
      String subtitle = request.queryParams("newSubtitle");
      String authorByLine = request.queryParams("newAuthorByLine");
      int articleId = Integer.parseInt(request.params(":article_id"));

      Article newArticle = new Article(title,shortTitle,body,picture,subhead,subtitle,authorByLine);
      newArticle.edit(articleId);
      response.redirect("/hub");
      return null;
    });

    get("/authors/new", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/author-form.vtl");
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/authors", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/authors.vtl");
      model.put("authors", Author.all());
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
      String password = "123456"; // currently we are using fixed password, they are not chosen by the admin
      Author newAuthor = new Author(name,role,bio,picture,email,facebook,twitter,password);
      newAuthor.save();
      response.redirect("/authors/new");
      return null;
    });



  }
}
