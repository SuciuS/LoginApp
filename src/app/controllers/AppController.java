package app.controllers;

import app.helpers.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label fullname;
    @FXML
    private Label email;
    @FXML
    private Label dob;

    double x = 0, y =0;

    @FXML
    void pressed(MouseEvent event) {

        x = event.getSceneX();
        y = event.getSceneY();

    }

    @FXML
    void dragged(MouseEvent event) {

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);

    }

    @FXML
    public void logout(MouseEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/app/views/login.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(Singleton.getInstance().getUsername());
        email.setText(Singleton.getInstance().getEmail());
        fullname.setText(Singleton.getInstance().getFullname());
        dob.setText(Singleton.getInstance().getDob());
    }
}
