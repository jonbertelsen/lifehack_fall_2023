package app.controllers;

import app.entities.Team;
import app.persistence.ConnectionPool;
import io.javalin.http.Context;
import ognl.enhance.ContextClassLoader;

import java.util.Random;

public class GroupAController {

    private static Team team = new Team();
    private static Random random = new Random();


    private static int getRandomStudent() {
        return random.nextInt(team.getListOfTeam().size() + 1);
    }

    public static void getStudentsName(Context ctx, ConnectionPool connectionPool) {
        int result = getRandomStudent();
        ctx.attribute("partnerName",team.getListOfTeam().get(result).toString());
        ctx.render("groupA-find-result.html");
    }
}
