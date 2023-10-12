package app;

import app.config.ThymeleafConfig;
import app.controllers.GroupFController;
import app.controllers.UserController;
import app.persistence.ConnectionPool;
import app.persistence.GroupFCalculator;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main
{

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "lifehack";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args)
    {
        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            JavalinThymeleaf.init(ThymeleafConfig.templateEngine());
        }).start(7070);

        // Routing

        app.get("/", ctx ->  ctx.render("index.html"));
        // user routes
        app.post("/login", ctx -> UserController.login(ctx, connectionPool));
        app.get("/createuser", ctx -> ctx.render("createuser.html"));
        app.post("/createuser",ctx -> UserController.createuser(ctx, connectionPool ));
        app.get("/logout", ctx -> UserController.logout(ctx));
        app.get("/softdrinks", ctx -> GroupFController.softdrink(ctx, connectionPool));
        //app.get("/softdrinks", ctx -> GroupFController.sugarCalculator(ctx, connectionPool));
        app.post("/softdrinks", ctx -> {
            int d_id = Integer.parseInt(ctx.formParam("d_id"));
            String result = GroupFCalculator.calculator(d_id, connectionPool);
            System.out.println("Result: " + result); // Add this line for debugging
            ctx.attribute("result", result);
            ctx.redirect("/softdrinks");
        });
    }
}