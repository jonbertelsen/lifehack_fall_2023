package app.controllers;

import app.entities.GroupFDrinks;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.GroupFCalculator;
import app.persistence.GroupFMapper;
import app.persistence.UserMapper;
import io.javalin.http.Context;

import java.util.List;

public class GroupFController
{
    private static int d_id;

    public static void login(Context ctx, ConnectionPool connectionPool)
    {
        String name = ctx.formParam("username");
        String password = ctx.formParam("password");
        try
        {
            User user = UserMapper.login(name, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            ctx.attribute("message", "Du er nu logget ind");
            ctx.render("index.html");
        }
        catch (DatabaseException e)
        {
            ctx.attribute("message", e.getMessage());
            ctx.render("index.html");
        }
    }

    public static void createuser(Context ctx, ConnectionPool connectionPool)
    {
        String name = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");

        // Validering af passwords - at de to matcher
        if (password1.equals(password2))
        {
            try
            {
                UserMapper.createuser(name, password1, connectionPool);
                ctx.attribute("message", "Du er nu oprette. Log på for at komme i gang.");
                ctx.render("index.html");

            }
            catch (DatabaseException e)
            {
                ctx.attribute("message", e.getMessage());
                ctx.render("createuser.html");
            }
        } else
        {
            ctx.attribute("message", "Dine password matcher ikke!");
            ctx.render("createuser.html");
        }

    }

    public static void logout(Context ctx)
    {
        // Invalidate session
        ctx.req().getSession().invalidate();
        ctx.redirect("/");
    }
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
