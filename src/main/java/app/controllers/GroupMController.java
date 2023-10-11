package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMController
{
    public static void  fineCalc(Context ctx, ConnectionPool connectionPool){
        int speed = Integer.parseInt(ctx.formParam("speed"));
        int zone = Integer.parseInt(ctx.formParam("zone"));


        String sql = "select fee, \"klip eller frakendelse\" from fees where (? >= fromkph) and (? <= tokph) and zone = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, speed);
                ps.setInt(2, speed);
                ps.setInt(3, zone);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int fee = rs.getInt("fee");
                    String feeAddOn = rs.getString("klip eller frakendelse");
                    ctx.attribute("fee", fee);
                    ctx.attribute("feeAddOn", feeAddOn);
                    ctx.render("groupM.html");

                } else {
                    throw new DatabaseException("Den indtastede hastighed findes ikke, prøv igen.");
                }
            }
        } catch (SQLException | DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}

