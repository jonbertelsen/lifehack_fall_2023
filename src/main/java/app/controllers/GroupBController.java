package app.controllers;

import app.entities.GroupBMovie;
import app.persistence.ConnectionPool;
import app.persistence.GroupBMapper;
import app.persistence.GroupBMapperCustomizable;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class GroupBController
{
    private static List<String> genreList = new ArrayList<>();
    private static List<String> ignoredGenreList = new ArrayList<>();
    public static List<String> genres;
    public static void getMovieResults(Context ctx, ConnectionPool connectionPool){
        List<String> genreList = ctx.sessionAttribute("genrelist");
        List<String> ignoredGenreList = ctx.sessionAttribute("ignoredgenrelist");
        float randomness = Float.parseFloat(ctx.sessionAttribute("randomness"));
        GroupBMapper bMap = new GroupBMapperCustomizable(10,25);
        List<GroupBMovie> movieList  = bMap.getMovies(connectionPool, genreList, ignoredGenreList, randomness, false);
        ctx.attribute("movies", movieList);
        ctx.render("/groupBResultScreen.html");
    }
    public static void updateGenreList(Context ctx,ConnectionPool connectionPool){
        String genre = ctx.formParam("genre");
        if(genre !=null && ignoredGenreList != null && !ignoredGenreList.contains(genre) && !genreList.contains(genre)){
            genreList.add(genre);
        }
        renderScearhSite(ctx,connectionPool);
    }
    public static void renderChoosenGenre(Context ctx, ConnectionPool connectionPool) {
        renderScearhSite(ctx, connectionPool);
    }
    public static void removeSearchParameters(Context ctx, ConnectionPool connectionPool) {
        String action = ctx.formParam("action");
        String genre = ctx.formParam("name");
        switch (action){
            case "removegenre" -> {
                genreList.remove(genre);
            }
            case "removeignoregenre" -> {
                ignoredGenreList.remove(genre);
            }
        }
        renderScearhSite(ctx, connectionPool);
    }
    private static void renderScearhSite(Context ctx, ConnectionPool connectionPool){
        ctx.sessionAttribute("genrelist", genreList);
        ctx.sessionAttribute("ignoredgenrelist", ignoredGenreList);
        if(ctx.formParam("randomness")!=null){
            ctx.sessionAttribute("randomness", ctx.formParam("randomness"));
        }else if(ctx.sessionAttribute("randomness") == null) {
            ctx.sessionAttribute("randomness", 10.0);
        }
        if(genres == null){
            genres = GroupBMapperCustomizable.getStaticDistinctGenres(connectionPool);
        }
        ctx.attribute("genres", genres);
        ctx.render("groupB.html");
    }

    public static void renderStart(Context ctx) {
        ctx.sessionAttribute("randomness", 5.0);
        ctx.render("/groupB.html");
    }

    public static void updateIgnoreList(Context ctx, ConnectionPool connectionPool) {
        String ignoreGenre = ctx.formParam("ignoregenre");
        if(ignoreGenre!=null && genreList != null && !ignoredGenreList.contains(ignoreGenre) && !genreList.contains(ignoreGenre)){
            ignoredGenreList.add(ignoreGenre);
        }
        renderScearhSite(ctx, connectionPool);
    }
}
