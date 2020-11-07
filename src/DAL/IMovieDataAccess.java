package DAL;

import BE.Movie;

import java.io.IOException;
import java.util.List;

public interface IMovieDataAccess {
    public List<Movie> getAllMovies() throws IOException;
}