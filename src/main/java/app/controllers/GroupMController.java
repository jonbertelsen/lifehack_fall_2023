package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class GroupMController {
    public static void fineCalc(Context ctx, ConnectionPool connectionPool) {

        int speed = Integer.parseInt(ctx.formParam("speed"));
        int zone = Integer.parseInt(ctx.formParam("zone"));
            
        String sql = "select fee, \"extra_punishment\" from fees where (? >= fromkph) and (? <= tokph) and zone = ?";

        //Connecting to the database
        try (Connection connection = connectionPool.getConnection()) {

            //Prepared statement to prevent SQL injection
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, speed);
                ps.setInt(2, speed);
                ps.setInt(3, zone);
                ResultSet rs = ps.executeQuery();

                //If the speed is over 350, the user will get an error message
                if (speed > 350) {
                    throw new DatabaseException("UMULIGT! Din lille pis Aygo kan ikke køre så stærkt!");
                }
                //If the speed is under the zone, the user will get an error message
                if (speed <= zone) {
                    throw new DatabaseException("Du kører ikke for stærkt, så du får ikke en bøde.");
                }
                //The user will get the fee and the extra punishment if there is one in the database
                if (rs.next()) {
                    int fee = rs.getInt("fee");
                    String feeAddOn = rs.getString("extra_punishment");
                    ctx.attribute("fee", fee);
                    ctx.attribute("feeAddOn", feeAddOn);
                    ctx.render("groupM.html");

                } else {
                    //If the speed is not in the database, the user will get an error message
                    throw new DatabaseException("Den indtastede hastighed findes ikke, prøv igen.");
                }




            }
            //If no connection to the database can be made, the user will get an error message
        } catch (SQLException | DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("groupM.html");
        }
    }
}

