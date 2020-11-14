package GUI.Controller;


import BE.Movie;
import BE.Rating;
import BE.User;
import GUI.Model.MovieModel;
import GUI.Model.UserModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //TableView movie
    @FXML
   private TableView<Movie> tableMovie;
    @FXML
    private TableColumn<Movie, Integer> columnId;
    @FXML
    private TableColumn<Movie, Integer> columnYear;
    @FXML
    private TableColumn<Movie, String> columnTitle;

    //searching field
   @FXML
   private TextField typeField;


    private MovieModel movieModel;
    private UserModel userModel;

    public Controller() {
       movieModel= new MovieModel();
       userModel = new UserModel();
    }

    public void searchAllMovies(ActionEvent event) {
        String text = typeField.getText();
       if(text != null)
       {
          movieModel.searchAllMovies(text);
       }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnId.setCellValueFactory( new PropertyValueFactory<Movie, Integer>("id"));
        columnYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
    }

    public void setModel(MovieModel movieModel) {
        this.movieModel = movieModel;
        tableMovie.setItems(movieModel.getObservableMovies());
    }

    public void UpdateMovie(ActionEvent event) throws IOException {
        // get the instance of the controller of the FXML loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/updateWindow.fxml"));
        Parent root = loader.load();

        //get controller from another class
       // UpdateWindowController updateWindowController = loader.getController();
        //updateWindowController.setModel(movieModel);
        //updateWindowController.sendMovie(tableMovie.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setTitle("update a movie");
        stage.setScene(new Scene(root));
        stage.show();

        //updateWindowController.changeTheMovie((Movie)moviesList.getSelectionModel().getSelectedItem());
        // meybe add listener instead of that

    }

    public void DeleteMovie(ActionEvent event) {
    }

    public void LoadAll(ActionEvent event) {
        movieModel.loadMovies();
        tableMovie.setItems(movieModel.getObservableMovies());
    }
    @FXML
    private void CreateMovieWindow(ActionEvent event) throws IOException {
        //loader insteada of static instance of controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/createWindow.fxml"));
        Parent root = loader.load();
        // get the instance of the controller of the FXML loader
        CreateWindowController createWindowController = loader.getController();
        createWindowController.setModel(movieModel);
        Stage stage = new Stage();
        stage.setTitle("Add new movies");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
