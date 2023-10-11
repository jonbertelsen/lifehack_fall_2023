package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class GroupBController
{
    private static List<String> genreList = new ArrayList<>();
    private static List<String> ignoredGenreList = new ArrayList<>();
    public static void getMovieResults(Context ctx, ConnectionPool connectionPool){
        List<String> genreList = ctx.sessionAttribute("genrelist");
        List<String> ignoredGenreList = ctx.sessionAttribute("ignoredgenrelist");
        float rendomness = Float.parseFloat(ctx.formParam("randomness"));

    }
    private static void updateGenreList(Context ctx){
        String genre = ctx.formParam("genre");
        String ignoreGenre = ctx.formParam("ignoregenre");
        if(genre !=null || ignoreGenre!=null){
            genreList.add(genre);
            ignoredGenreList.add(ignoreGenre);
        }
        ctx.sessionAttribute("genrelist", genreList);
        ctx.sessionAttribute("ignoredgenrelist", ignoredGenreList);
        ctx.render("groupBtest.html");
    }
    public static void renderChoosenGenre(Context ctx, ConnectionPool connectionPool) {
        updateGenreList(ctx);
    }
}
