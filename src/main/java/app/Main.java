package app;

import app.config.ThymeleafConfig;
import app.controllers.GroupBController;
import app.controllers.UserController;
import app.persistence.ConnectionPool;
import app.persistence.GroupBMapper;
import app.persistence.GroupBMapperCustomizable;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.List;

public class Main
{

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "lifehack";

    public static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);


    public static void main(String[] args)
    {
        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            JavalinThymeleaf.init(ThymeleafConfig.templateEngine());
        }).start(7070);

        // Routing

        /*app.get("/", ctx ->  ctx.render("index.html"));
        // user routes
        app.post("/login", ctx -> UserController.login(ctx, connectionPool));
        app.get("/createuser", ctx -> ctx.render("createuser.html"));
        app.post("/createuser",ctx -> UserController.createuser(ctx, connectionPool ));
        app.get("/logout", ctx -> UserController.logout(ctx));*/
        app.get("/",ctx -> GroupBController.renderChoosenGenre(ctx, connectionPool));
        app.post("/update1", ctx -> GroupBController.updateGenreList(ctx,connectionPool));
        app.post("/update2", ctx -> GroupBController.updateIgnoreList(ctx,connectionPool));
        app.post("/update3", ctx -> GroupBController.renderChoosenGenre(ctx,connectionPool));
        app.post("/groupB.html",ctx -> GroupBController.removeSearchParameters(ctx,connectionPool));
        app.get("/actionBtn",ctx -> GroupBController.getMovieResults(ctx, connectionPool));
        app.get("/menu",ctx-> GroupBController.renderStart(ctx));
    }
}