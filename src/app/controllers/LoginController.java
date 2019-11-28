package app.controllers;

import app.helpers.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pf_password;

    double x = 0, y = 0;

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
    void login(MouseEvent event) throws SQLException, IOException {

        String username = tf_username.getText();
        String password = pf_password.getText();

        Connection connection = DbConnect.getInstance().getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users where username = '" + username +  "' and" +
                " password = '" + password + "'");


        if (resultSet.next()) {
            String usernameText = tf_username.getText();
            if(!usernameText.isEmpty())
                Singleton.getInstance().setUsername(usernameText);

            String emailText = resultSet.getString("email");
            if(!emailText.isEmpty())                                         //getting username, email, full name and date of birth from login screen to app screen
                Singleton.getInstance().setEmail(emailText);

            String fullname = resultSet.getString("fullname");
            if(!fullname.isEmpty())
                Singleton.getInstance().setFullname(fullname);

            String dob = resultSet.getString("date");
            if(!dob.isEmpty())
                Singleton.getInstance().setDob(dob);





            System.out.println("Successfully logged in");

            Parent root = FXMLLoader.load(getClass().getResource("/app/views/app.fxml"));

            Node node = (Node) event.getSource();

            Stage stage = (Stage) node.getScene().getWindow();

            stage.setScene(new Scene(root));
        }

        connection.close();


    }

    @FXML
    void signup(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/app/views/signup.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));


    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
