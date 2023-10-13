package app.controllers;

import app.entities.GroupFDrinks;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.GroupFCalculator;
import app.persistence.GroupFMapper;
import io.javalin.http.Context;

import java.util.List;

public class GroupFController
{
    private static int d_id;

    public static void softdrink(Context ctx, ConnectionPool connectionPool) {

        try {
            List<GroupFDrinks> drinksList = GroupFMapper.getAllDrinks(connectionPool);
            ctx.attribute("drinksList", drinksList);
            ctx.render("groupF.html");
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

    }

    public static void calcResult(Context ctx, ConnectionPool connectionPool) throws DatabaseException {
        int d_id = Integer.parseInt(ctx.formParam("d_id"));
        String result = GroupFCalculator.calculator(d_id, connectionPool);
        System.out.println("Result: " + result); // Add this line for debugging
        ctx.attribute("result", result);
        ctx.render("groupFresult.html");
    }
}
