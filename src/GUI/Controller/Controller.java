package GUI.Controller;


import BE.Movie;
import BE.Rating;
import BE.User;
import GUI.Model.MovieModel;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView<Movie> moviesList;
    @FXML
    private ListView<Rating> ratingList;
    @FXML
    private ListView<User> userList;

    @FXML
    private Button searchButton, createButton, updateButton, deleteButton, enterButton;

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
          moviesList.getItems().setAll(movieModel.getFoundMovies(text));
       }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //here we use movieModel
        moviesList.setItems(movieModel.getObservableMovies());

        userList.setItems(userModel.getObservableUsers());
    }

    /*public Movie sendSelectedMovie()
    {
    return (Movie)
moviesList.getSelectionModel().getSelectedItem();
    }

     */


    public void UpdateMovie(ActionEvent event) throws IOException {
       // moviesList.getSelectionModel().getSelectedItem()

        // get the instance of the controller of the FXML loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/View/updateWindow.fxml"));
        Parent root = loader.load();

        //get controller from another class
        UpdateWindowController updateWindowController = loader.getController();
        //updateWindowController.changeTheMovie(
       //        moviesList.selectionModelProperty().getValue().getSelectedItem());
        Movie movie = moviesList.getSelectionModel().getSelectedItem();
       updateWindowController.sendMovie(movie);
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
