package lbycpc2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.lang.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

public class viewProfileController implements Initializable {

    public String email;
    public String emailFinder;
    public String firstNameFinder;
    public String birthdateFinder;

    public Label fullNameLabel;
    public Label birthdateLabel;
    public Label emailLabel;

    private Scanner y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            y = new Scanner(new File("src/Database/profile"));
        } catch (Exception e) {
            System.out.println("could not find file");
        }

        readFile();
    }

    public void readFile() {


        email = y.next();

        while (y.hasNext()) {
            email = y.next();
        }

        System.out.println("the email is: " + email);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Ngpmctct_2346");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            emailFinder = "1";

            while (resultSet.next() && !email.equals(emailFinder)) {
                firstNameFinder = resultSet.getString("first_name");
                birthdateFinder = resultSet.getString("birthdate");
                emailFinder = resultSet.getString("email_address");
            }

            fullNameLabel.setText(firstNameFinder);
            birthdateLabel.setText(birthdateFinder);
            emailLabel.setText(emailFinder);

            statement.close();
            connection.close();
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



        y.close();
    }

    public void homeButtonAction(ActionEvent event) throws IOException{
        Parent feedParent = FXMLLoader.load(getClass().getResource("feed.fxml"));
        Scene feedScene = new Scene(feedParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(feedScene);
        window.show();
    }

    public void profileButtonAction(ActionEvent event) throws IOException{
        Parent profileParent = FXMLLoader.load(getClass().getResource("viewProfile.fxml"));
        Scene profileScene = new Scene(profileParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(profileScene);
        window.show();
    }

    public void logoutButtonAction(ActionEvent event) throws IOException{
        Parent logoutParent = FXMLLoader.load(getClass().getResource("logoutPrompt.fxml"));
        Scene logoutScene = new Scene(logoutParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(logoutScene);
        window.show();
    }

    public void createPostButtonAction(ActionEvent event) throws IOException{
        Parent createPostParent = FXMLLoader.load(getClass().getResource("createPost.fxml"));
        Scene createPostScene = new Scene(createPostParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(createPostScene);
        window.show();
    }


}
