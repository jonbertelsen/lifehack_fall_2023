package app.controllers;

import app.entities.Team;

import java.util.Random;

public class GroupAController {

    Team team = new Team();
    Random random = new Random();

    public GroupAController() {

    }

    private int getRandomStudent() {
        return random.nextInt(team.getListOfTeam().size() + 1);
    }

    public String getStudentsName() {
        int result = getRandomStudent();
        return team.getListOfTeam().get(result).toString();
    }

}
