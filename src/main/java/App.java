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

  }
}
