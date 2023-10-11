package app.persistence;

import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupFMapper {

    public static List<GroupFDrinks> getAllDrinks(ConnectionPool connectionPool) throws DatabaseException {
        List<GroupFDrinks> drinksList = new ArrayList<>();
        String sql = "select * from drinks";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int d_id = rs.getInt("d_id");
                    String name = rs.getString("name");
                    int sugar = rs.getInt("sugar");
                    drinksList.add(new GroupFDrinks(d_id, name, sugar));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in TaskMapper" + e);
        }
        return drinksList;
    }

    public static List<GroupFDrinks> getSugar(int d_id, ConnectionPool connectionPool) throws DatabaseException {
        List<GroupFDrinks> sugarContent = new ArrayList<>();
        String sql = "select sugar from drinks where d_id=?";

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, d_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    //int d_id = rs.getInt("d_id");
                    int sugar = rs.getInt("sugar");
                    sugarContent.add(new GroupFDrinks(sugar));
                }
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error in TaskMapper" + e);
        }
        return sugarContent;
    }
}
