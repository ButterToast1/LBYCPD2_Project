package LBYCPD2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.lang.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

public class createPostController implements Initializable {

    public TextArea textArea;

    public String description;
    public String email;
    public String emailFinder;
    public String firstNameFinder;
    public String lastNameFinder;
    public String output1;
    public String output2;

    public String customerID;

    public Label fullNameLabel;
    public Label emailLabel;

    private Scanner x;
    private Scanner y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            x = new Scanner(new File("src/Database/customer_id"));
            y = new Scanner(new File("src/Database/profile"));
        } catch (Exception e) {
            System.out.println("could not find file");
        }

        readFile();
    }

    public void readFile() {

        customerID = x.next();
        email = y.next();

        while (y.hasNext()) {
            email = y.next();
        }

        while (x.hasNext()) {
            customerID = x.next();
        }

        System.out.println("the email is: " + email);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            emailFinder = "1";

            while (resultSet.next() && !email.equals(emailFinder)) {
                firstNameFinder = resultSet.getString("first_name");
                lastNameFinder = resultSet.getString("last_name");
                emailFinder = resultSet.getString("email_address");
            }

            output1 = firstNameFinder.substring(0, 1).toUpperCase() + firstNameFinder.substring(1);
            output2 = lastNameFinder.substring(0, 1).toUpperCase() + lastNameFinder.substring(1);


            fullNameLabel.setText(output1 + " " + output2);

            statement.close();
            connection.close();
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        x.close();
        y.close();
    }

    public void createPostButtonAction(ActionEvent event) throws IOException {
        try {

            //connection from users
            Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");

            //connection from posts
            Connection connection2 = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");
            Statement statement2 = connection.createStatement();
            ResultSet resultSet2 = statement.executeQuery("select * from posts");

            description = textArea.getText();

            //TEST
            System.out.println(description);
            //END test

            // Adds input to database
            String insertQuery = "INSERT INTO posts VALUES (DEFAULT, '"+customerID+"', '"+description+"', NULL, '"+java.time.LocalDate.now()+"')";
            statement.executeUpdate(insertQuery);
            System.out.println("Inserted");

            statement.close();
            connection.close();
            resultSet.close();

            statement2.close();
            connection2.close();
            resultSet2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent feedParent = FXMLLoader.load(getClass().getResource("feed.fxml"));
        Scene feedScene = new Scene(feedParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(feedScene);
        window.show();
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

    public void postButtonAction(ActionEvent event) throws IOException{
        Parent createPostParent = FXMLLoader.load(getClass().getResource("createPost.fxml"));
        Scene createPostScene = new Scene(createPostParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(createPostScene);
        window.show();
    }



}

