package app;

import app.config.ThymeleafConfig;
import app.controllers.*;
import app.persistence.ConnectionPool;
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
        }).start(7071);

        // Routing

        app.get("/", ctx -> ctx.render("index.html"));
        // user routes
        app.post("/login", ctx -> UserController.login(ctx, connectionPool));
        app.get("/createuser", ctx -> ctx.render("createuser.html"));

        app.post("/createuser", ctx -> UserController.createuser(ctx, connectionPool));
        app.get("/logout", ctx -> UserController.logout(ctx));

        // Group A: Java Partners in crime
        app.get("/groupA", ctx -> GroupAController.getFrontPage(ctx, connectionPool));
        app.post("/find-partner", ctx -> GroupAController.getStudentsName(ctx, connectionPool));

        // Group B: Movies
        app.post("/update1", ctx -> GroupBController.updateGenreList(ctx,connectionPool));
        app.post("/update2", ctx -> GroupBController.updateIgnoreList(ctx,connectionPool));
        app.post("/update3", ctx -> GroupBController.renderChoosenGenre(ctx,connectionPool));
        app.post("/groupB.html",ctx -> GroupBController.removeSearchParameters(ctx,connectionPool));
        app.get("/actionBtn",ctx -> GroupBController.getMovieResults(ctx, connectionPool));
        app.get("/menu",ctx-> GroupBController.renderChoosenGenre(ctx, connectionPool));

        // Group D: Coffee
        app.get("/coffee", ctx -> GroupDController.showPage(ctx));
        app.post("/coffeeprice", ctx -> GroupDController.getPrice(ctx));

        // Group E: Valuta
        app.post("/valuta", ctx -> GroupEController.getInput(ctx));
        app.get("/valuta", ctx -> GroupEController.valutaInit(ctx));

        // Group F: Softdrinks
        app.get("/softdrinks", ctx -> GroupFController.softdrink(ctx, connectionPool));
        app.post("/softdrinks", ctx -> GroupFController.calcResult(ctx, connectionPool));

        // Group M: Fee calculator
        app.get("/boderegner", ctx ->  ctx.render("groupM.html"));
        app.post("/boderegner",ctx -> GroupMController.fineCalc(ctx, connectionPool ));

    }
}
