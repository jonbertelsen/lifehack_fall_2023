package app.persistence;

import app.entities.GroupBMovie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupBMapperCustomizable implements GroupBMapper{
    float genrePrio;
    float ryanPrio;

    GroupBMapperCustomizable(float genrePrio, float ryanPrio){
        this.genrePrio = genrePrio;
        this.ryanPrio = ryanPrio;
    }

    /**
     * @param genres
     * Genres to Prioritize
     * @param ignoredGenres
     * Genres to blacklist
     * @param randomness
     * how random should it be?
     * @param ryanInMovie
     * is ryan gosling movies prioritiesed
     * @return
     * a list
     */
    @Override
    public List<GroupBMovie> getMovies(ConnectionPool connectionPool, List<String> genres, List<String> ignoredGenres, float randomness, boolean ryanInMovie) {
        List<GroupBMovie> results = new ArrayList<>();
        StringBuilder sqlB = new StringBuilder("select name from Movie");
        StringBuilder sqlWhere = new StringBuilder("where 1=1");
        StringBuilder sqlOrder = new StringBuilder("order by case");
        if(!genres.isEmpty()){
            sqlOrder.append(" ");
        }
        sqlOrder.append("name ASC");
        sqlB.append(sqlWhere).append(sqlOrder);
        String sql = sqlB.toString();
        try(Connection conn = connectionPool.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return results;
    }
}
