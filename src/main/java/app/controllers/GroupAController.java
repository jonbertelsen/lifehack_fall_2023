package app.controllers;

import app.entities.Team;
import app.persistence.ConnectionPool;
import io.javalin.http.Context;

import java.util.Random;

public class GroupAController {

    private static Team team = new Team();
    private static Random random = new Random();

    // Funktion til at hente en tilfældig studerendes indeks
    private static int getRandomStudent() {
        return random.nextInt(1, team.getListOfTeam().size() - 1);
    }

    // Metode til at håndtere anmodningen om at finde en studerendes navn
    public static void getStudentsName(Context ctx, ConnectionPool connectionPool) {
        int result = getRandomStudent();

        String userName = ctx.formParam("username");
        String inputLevel = ctx.formParam("java_level");

        try {
            // Sæt brugernavn og partnerens navn i konteksten (context)
            ctx.attribute("username", userName);
            ctx.attribute("java_level", inputLevel);
            ctx.attribute("partnerName", team.getListOfTeam().get(result).toString());


            // Vis resultatet ved at rendere en HTML-side
            ctx.render("groupA-find-result.html");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Metode til at håndtere anmodningen om forsiden for "Group A"
    public static void getFrontPage(Context ctx, ConnectionPool connectionPool) {
        String teamName = team.getTeamName();

        try {
            // Sæt holdnavnet i konteksten (context)
            ctx.attribute("teamname", teamName);

            // Vis forsiden for "Group A" ved at rendere en HTML-side
            ctx.render("groupA.html");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
