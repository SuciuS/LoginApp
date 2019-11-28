package app.controllers;

import app.helpers.DbConnect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_email;

    @FXML
    private PasswordField pf_password;

    @FXML
    private TextField tf_name;

    @FXML
    private DatePicker tf_date;

    @FXML
    void login(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/app/views/login.fxml"));

        Node node = (Node) event.getSource();

        Stage stage = (Stage) node.getScene().getWindow();

        stage.setScene(new Scene(root));
    }

    @FXML
    void signup(MouseEvent event) {

        Connection connection = DbConnect.getInstance().getConnection();

        try {

            String username = tf_username.getText();
            String email = tf_email.getText();
            String password = pf_password.getText();
            String fullname = tf_name.getText();
            String date;
            if (tf_date.getValue() == null)
                date = "null";
            else
                date = tf_date.getValue().toString();



            Statement statement = connection.createStatement();

            int status = 0;
            if (!(username.isEmpty()) && !(email.isEmpty()) && !(password.isEmpty()) && !(fullname.isEmpty()) && !(date == "null")) {
                status = statement.executeUpdate("insert into users (username,email,password,fullname,date) values('" + username + "','" + email + "','" + password + "','" + fullname + "','" + date +"')");
            }
                                                                            // won't let you register if any of the required fields are empty.
            if (status > 0) {
                System.out.println("User registered.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
