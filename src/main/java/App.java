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
      String title = request.queryParams("title");
      String shortTitle = request.queryParams("shortTitle");
      String body = request.queryParams("body");
      String picture = request.queryParams("picture");
      String subhead = request.queryParams("subhead");
      String subtitle = request.queryParams("subtitle");
      String authorByLine = request.queryParams("authorByLine");
      Article newArticle = new Article(title,shortTitle,body,picture,subhead,subtitle,authorByLine);
      newArticle.save();
      return null;
    });

  }
}
