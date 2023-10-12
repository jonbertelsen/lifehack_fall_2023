package app.persistence;

import app.entities.GroupBMovie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupBMapperCustomizable implements GroupBMapper{
    final String DB = "public.movie";
    float genrePrio;
    float ryanPrio;

    public GroupBMapperCustomizable(float genrePrio, float ryanPrio){
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
        StringBuilder sqlB = new StringBuilder("select a.id, a.name from (select id, name, (");
        StringBuilder sqlWhere = new StringBuilder(" where 1=1");
        StringBuilder sqllikeness = new StringBuilder();
        sqllikeness.append("random()*").append(randomness);
        if(ryanInMovie){
            sqllikeness.append(" + case when 'gosling_present' then ").append(ryanPrio).append(" else 0 end");
        }
        if(genres != null && !genres.isEmpty()){
            for (String s: genres) {
                sqllikeness.append(" + case when genre like '%").append(s).append("%' then ").append(genrePrio).append(" else 0 end");
            }
        }
        sqllikeness.append(") as likeness");
        if(ignoredGenres != null && !ignoredGenres.isEmpty()){
            for (String s: ignoredGenres) {
                sqlWhere.append(" and not lower(genre) like '%").append(s).append("%'");
            }
        }

        sqlB.append(sqllikeness).append(" from ").append(DB).append(sqlWhere).append(") as a order by a.likeness desc limit 5;");
        String sql = sqlB.toString();
        try(Connection conn = connectionPool.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    results.add(new GroupBMovie(rs.getInt("id"),rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            System.out.println("sql: " + sql);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return results;
    }

    public List<String> getDistinctGenres(ConnectionPool connectionPool){
        List<String> results = new ArrayList<>();
        String sql = "select distinct genre from (select distinct SPLIT_part(a.genre,',', 1) as genre from public.movie as a where genre != '\\N' union select distinct split_part(b.genre, ',', 2) from public.movie as b where genre != '' union  select distinct split_part(c.genre, ',', 3) from public.movie as c where genre != '') where genre != ''";
        try(Connection conn = connectionPool.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    results.add(rs.getString("genre"));
                }
            }
        } catch (SQLException e) {
            System.out.println("sql: " + sql);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return results;
    }

    public static void main(String args[]){
        GroupBMapper map = new GroupBMapperCustomizable(25,0);
        String USER = "postgres";
        String PASSWORD = "postgres";
        String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
        String DB = "lifehack";
        ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);
        /*List<GroupBMovie> moves = map.getMovies(connectionPool, List.of("Drama"), List.of("Horror"), 5, false);
        for (GroupBMovie g: moves) {
            System.out.println(g.id + ": " + g.name);
        }*/
        List<String> genre = map.getDistinctGenres(connectionPool);
        for (String g: genre) {
            System.out.println(g);
        }
    }
}
