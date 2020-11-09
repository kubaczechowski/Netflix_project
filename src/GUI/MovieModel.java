package GUI;

import BE.Movie;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class MovieModel {
    private ObservableList moviesToBeViewed;
    private MovieManager movieManager;

    public MovieModel(){
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();
        try {
            moviesToBeViewed.addAll(movieManager.getAllMovies());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObservableList<Movie> getObservableMovies(){
        return moviesToBeViewed;
    }
}
