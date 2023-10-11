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
        int speedLimit = Integer.parseInt(ctx.formParam("speedLimit"));

        String sql = "select * from \"speedLimit\" where speed = ?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, speed);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int fine = rs.getInt("Bødetakst");
                    String fineSurcharge1 = rs.getString("Klip_Frakendelse");
                    String fineSurcharge2 = rs.getString("Tillæg");
                    ctx.attribute("fine", fine);
                    ctx.attribute("fineSurcharge1", fineSurcharge1);
                    ctx.attribute("fineSurcharge2", fineSurcharge2);
                    ctx.render("gruppeM.html");

                } else {
                    throw new DatabaseException("Den indtastede hastighed findes ikke, prøv igen.");
                }
            }
        } catch (SQLException | DatabaseException e) {
            throw new RuntimeException(e);
        }
    }
}

