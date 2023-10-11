package app.persistence;

import app.entities.GroupBMovie;

import java.util.List;

public interface GroupBMapper {
    public List<GroupBMovie> getMovies(ConnectionPool connectionPool, List<String> genres, List<String> ignoredGenres, float randomness, boolean ryanInMovie);

}
