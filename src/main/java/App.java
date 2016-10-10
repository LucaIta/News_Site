import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {

  public static void main (String[] args){
    staticFileLocation("/public");

    get("/", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      if(request.session().attribute("credentialsAreIncorrect") != null) { // when I get to this path for the first time, "usernameIsTaken" is null. So I need this check to retrieve the value of "usernameIsTaken" and put it in the model only when it is not null.
        boolean credentialsAreIncorrect = request.session().attribute("credentialsAreIncorrect");
        model.put("credentialsAreIncorrect", credentialsAreIncorrect);
      }
      model.put("template", "/templates/index.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    },new VelocityTemplateEngine());

    get("/home", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      int pageNumber = 1;
      model.put("pageNumber", pageNumber);
      model.put("isLastPage", Article.isLastPage(pageNumber)); // here I put in the model a Boolean indicating wheter it is the last page (depending on if there are articles to create another page) in order to hide if necessary the next page button
      model.put("articles", Article.getArticlesForPage(pageNumber));
      model.put("template", "/templates/home-page.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    },new VelocityTemplateEngine());

    get("/home/:pageNumber", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      int pageNumber = Integer.parseInt(request.params("pageNumber"));
      model.put("pageNumber", pageNumber);
      model.put("articles", Article.getArticlesForPage(pageNumber));
      model.put("isLastPage", Article.isLastPage(pageNumber));
      model.put("template", "/templates/home-page.vtl");
      return new ModelAndView(model, "templates/layout.vtl");
    },new VelocityTemplateEngine());

    post("/login", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      String username = request.queryParams("username");
      String password = request.queryParams("password");
      if (Author.checkCredentials(username,password)) {
        // model.put("template", "/templates/hub.vtl"); - ok
        Author author = Author.findByUsername(username);
        request.session().attribute("credentialsAreIncorrect", false);
        request.session().attribute("author", author); // here I store in the session the author so that I can check the permits in the other paths

        // return new ModelAndView(model, "templates/layout.vtl");
        response.redirect("/hub");
        return null;
      } else {
        request.session().attribute("credentialsAreIncorrect", true); // I store in the session a boolean indicating that the credentials were wrong so that the login page can display a message error
        response.redirect("/");
        return null;
      }
    },new VelocityTemplateEngine());

    get("/hub", (request,response) -> { // for this and other paths, I should be able to get there only if the user is autenticated
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/hub.vtl");
      Author author = request.session().attribute("author");
      model.put("author",author);
      if (author.findArticles() != null) {
        List<Article> articles = author.findArticles();
        model.put("articles",articles);
      }
      return new ModelAndView(model, "templates/layout.vtl");
    },new VelocityTemplateEngine());

    get("/articles", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      if (request.session().attribute("author") != null) {
        Author currentAuthor = request.session().attribute("author"); // Here I retrieve the author so that the VTL can check wheter the user of the current session can edit the article
        model.put("author", currentAuthor);
      }
      model.put("template", "/templates/articles.vtl");
      model.put("articles", Article.all());
      return new ModelAndView(model, "templates/layout.vtl");
    },new VelocityTemplateEngine());

    get("/articles/new", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      if (request.session().attribute("author") != null) {
        Author currentAuthor = request.session().attribute("author"); // Here I retrieve the author so that the VTL can check wheter the user of the current session can edit the article
        model.put("author", currentAuthor);
      }
      model.put("template", "/templates/article-form.vtl");
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/articles/:article_id", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      if (request.session().attribute("author") != null) {
        Author currentAuthor = request.session().attribute("author"); // Here I retrieve the author so that the VTL can check wheter the user of the current session can edit the article
        model.put("author", currentAuthor);
      } else {
        boolean visitorUser = true; // here it creates a boolean that keep traks of wheter who is visiting the page is a visito or an author so that I can hide or display the edit button
        model.put("visitorUser", visitorUser);
      }
      model.put("template", "/templates/article.vtl");
      model.put("article", Article.find(Integer.parseInt(request.params("article_id"))));
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post("/articles", (request,response) -> {
      Author currentAuthor = request.session().attribute("author"); // here I'm retrieving always the same user, to get rid of when I implement the login system
      String title = request.queryParams("title");
      String shortTitle = request.queryParams("shortTitle");
      String body = request.queryParams("body");
      String picture = request.queryParams("picture");
      String subhead = request.queryParams("subhead");
      String subtitle = request.queryParams("subtitle");
      String author = ((Author) request.session().attribute("author")).getName();
      String authorByLine = request.queryParams("authorByLine");
      Article newArticle = new Article(title,shortTitle,body,picture,subhead,subtitle,author,authorByLine);
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
      Author author = request.session().attribute("author");
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("author", author);
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
      String author = ((Author) request.session().attribute("author")).getName();
      String authorByLine = request.queryParams("newAuthorByLine");
      int articleId = Integer.parseInt(request.params(":article_id"));
      Article newArticle = new Article(title,shortTitle,body,picture,subhead,subtitle,author,authorByLine);
      newArticle.edit(articleId);
      response.redirect("/articles/" + articleId);
      return null;
    });

    get("/authors/new", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();

      if (request.session().attribute("author") != null) {
        Author currentAuthor = request.session().attribute("author"); // Here I retrieve the author so that the VTL can check wheter the user of the current session can edit the article
        model.put("author", currentAuthor);
      }

      if(request.session().attribute("usernameIsTaken") != null) { // when I get to this path for the first time, "usernameIsTaken" is null. So I need this check to retrieve the value of "usernameIsTaken" and put it in the model only when it is not null.
        boolean usernameIsTaken = request.session().attribute("usernameIsTaken");
        model.put("usernameIsTaken", usernameIsTaken);
      }
      if(request.session().attribute("passwordsDoesNotCorrespond") != null) {
        boolean passwordsDoesNotCorrespond = request.session().attribute("passwordsDoesNotCorrespond");
        model.put("passwordsDoesNotCorrespond", passwordsDoesNotCorrespond);
      }
      request.session().attribute("passwordsDoesNotCorrespond", false); // here I put the value to false so that next time the page is accessed, unless a not corresponding password was inserted, the value is false
      model.put("template", "/templates/author-form.vtl");
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/authors", (request,response) -> {
      Author author = request.session().attribute("author");
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("author", author);
      model.put("template", "/templates/authors.vtl");
      model.put("authors", Author.all());
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post("/authors", (request,response) -> { // should be /new?
      String username = request.queryParams("username");
      if (Author.getExistingUsernames().contains(username)) {
        request.session().attribute("usernameIsTaken", true); // Here I set the "Username" attribute to true so that the boolean can get to "/authors/new" path through the session and display an error
        response.redirect("/authors/new");
      } else {
        request.session().attribute("usernameIsTaken", false);
        String password =  request.queryParams("password");
        String repeatedPassword =  request.queryParams("repeatedPassword");
        if (password.equals(repeatedPassword)) {
          String name = request.queryParams("name");
          String role = request.queryParams("role");
          String bio = request.queryParams("bio");
          String picture = request.queryParams("picture");
          String email = request.queryParams("email");
          String facebook = request.queryParams("facebook");
          String twitter = request.queryParams("twitter");
          boolean canCreateAuthor = Boolean.parseBoolean(request.queryParams("canCreateAuthor"));
          boolean canCreateArticle = Boolean.parseBoolean(request.queryParams("canCreateArticle"));
          boolean canEditAuthor = Boolean.parseBoolean(request.queryParams("canEditAuthor"));
          boolean canEditArticle = Boolean.parseBoolean(request.queryParams("canEditArticle"));
          boolean canDeleteArticle = Boolean.parseBoolean(request.queryParams("canDeleteArticle"));
          boolean canDeleteAuthor = Boolean.parseBoolean(request.queryParams("canDeleteAuthor"));
          Author newAuthor = new Author(name,role,bio,picture,email,facebook,twitter,username,password,canCreateAuthor,canCreateArticle,canEditAuthor,canEditArticle,canDeleteArticle,canDeleteAuthor);
          newAuthor.save();
          response.redirect("/authors/new");
        } else {
          request.session().attribute("passwordsDoesNotCorrespond", true); // if by default these values are false, I might be able to invert the logic and get rid of the if statements that I have in every path where I have to set it as true
          response.redirect("/authors/new");
        }
        // String password = "123456"; // currently we are using fixed password, they are not chosen by the admin
      }
      return null;
    });

    post("/authors/:author_id/edit", (request,response) -> {

      HashMap<String,Object> model = new HashMap<String,Object>();
      String name = request.queryParams("newName");
      String role = request.queryParams("newRole");
      String bio = request.queryParams("newBio");
      String picture = request.queryParams("newPicture");
      String email = request.queryParams("newEmail");
      String facebook = request.queryParams("newFacebook");
      String twitter = request.queryParams("newTwitter");
      String username = request.queryParams("newUsername");
      int authorId = Integer.parseInt(request.params(":author_id"));
      boolean canCreateAuthor = Boolean.parseBoolean(request.queryParams("canCreateAuthor"));
      boolean canCreateArticle = Boolean.parseBoolean(request.queryParams("canCreateArticle"));
      boolean canEditAuthor = Boolean.parseBoolean(request.queryParams("canEditAuthor"));
      boolean canEditArticle = Boolean.parseBoolean(request.queryParams("canEditArticle"));
      boolean canDeleteArticle = Boolean.parseBoolean(request.queryParams("canDeleteArticle"));
      boolean canDeleteAuthor = Boolean.parseBoolean(request.queryParams("canDeleteAuthor"));
      Author newAuthor = new Author(name,role,bio,picture,email,facebook,twitter,username,Author.find(authorId).getPassword(),canCreateAuthor,canCreateArticle,canEditAuthor,canEditArticle,canDeleteArticle,canDeleteAuthor); // not very elegant because of the Author.get....

      newAuthor.edit(authorId);
      response.redirect("/authors");
      return null;
    });

    get("/authors/:author_id/edit", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/author-edit-form.vtl");
      int authorId = Integer.parseInt(request.params("author_id"));
      model.put("author", Author.find(authorId));
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    get("/authors/:author_id/changePwd", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      model.put("template", "/templates/change-pwd.vtl");
      int authorId = Integer.parseInt(request.params("author_id"));
      model.put("author", Author.find(authorId));
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post("/authors/:author_id/changePwd", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      int authorId = Integer.parseInt(request.params("author_id"));
      Author author = Author.find(authorId);
      String oldPwd = request.queryParams("oldPwd");
      byte[] salt = author.getSalt();
      String encryptedOldPassword = PasswordEncrypter.getSha1SecurePassword(oldPwd,salt);
      String newPwd = request.queryParams("newPwd");
      String confNewPwd = request.queryParams("confNewPwd");
      Boolean pswChangedsuccessfully;
      if (author.getPassword().equals(encryptedOldPassword)) {
        author.editPassword(newPwd);
        pswChangedsuccessfully = true;
      } else {
        pswChangedsuccessfully = false;
      }
      model.put("author", author);
      model.put("pswChangedsuccessfully",pswChangedsuccessfully);
      model.put("template", "/templates/change-pwd.vtl");
      return new ModelAndView(model, "/templates/layout.vtl");
    }, new VelocityTemplateEngine());

    post("/authors/:author_id/delete", (request,response) -> {
      int authorId = Integer.parseInt(request.params("author_id"));
      Author.find(authorId).delete();
      response.redirect("/authors");
      return null;
    });

  }


  // public void getBooleanAttributeFromSessionAndPutInModel(String attributeKey) {
  //   boolean attribute = request.session().attribute(attributeKey);
  //   model.put(attributeKey, attribute);
  // }

}
