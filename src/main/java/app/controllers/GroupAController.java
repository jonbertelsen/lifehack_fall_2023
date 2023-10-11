package app.controllers;

import app.entities.Team;
import io.javalin.http.Context;
import ognl.enhance.ContextClassLoader;

import java.util.Random;

public class GroupAController {

    Team team = new Team();
    Random random = new Random();

    public GroupAController() {

    }

    private int getRandomStudent() {
        return random.nextInt(team.getListOfTeam().size() + 1);
    }

    public void getStudentsName(Context ctx) {
        int result = getRandomStudent();
        ctx.attribute("partnerName",team.getListOfTeam().get(result).toString());
        ctx.render("groupA-find-result.html");
    }
}
