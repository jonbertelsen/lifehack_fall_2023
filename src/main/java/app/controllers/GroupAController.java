package app.controllers;

import app.entities.Team;
import app.persistence.ConnectionPool;
import io.javalin.http.Context;

import java.util.Random;

public class GroupAController {

    private static Team team = new Team();
    private static Random random = new Random();


    private static int getRandomStudent() {
        return random.nextInt(1, team.getListOfTeam().size()-1);
    }

    public static void getStudentsName(Context ctx, ConnectionPool connectionPool) {
        int result = getRandomStudent();
        String userName = ctx.formParam("username");

        try{
            ctx.attribute("username", userName);
        ctx.attribute("partnerName",team.getListOfTeam().get(result).toString());
        ctx.render("groupA-find-result.html");}
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void getFrontPage(Context ctx, ConnectionPool connectionPool){
        String teamName = team.getTeamName();

        try{
            ctx.attribute("teamname", teamName);
            ctx.render("groupA.html");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

}
