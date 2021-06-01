package lbycpc2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;

public class feedController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label userLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;


    public String email;
    public String postIDFinder;
    public String customerIDFinder;
    public String customerIDFinder2;
    public String descriptionFinder;
    public int postIDInt;

    public String firstNameFinder;
    public String lastNameFinder;

    private Scanner x;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // readFile();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from posts");

            Connection connection2 = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");
            Statement statement2 = connection2.createStatement();
            ResultSet resultSet2 = statement2.executeQuery("select * from users");

            while (resultSet.next()) {
                customerIDFinder = resultSet.getString("customer_id");
                descriptionFinder = resultSet.getString("description");
                postIDFinder = resultSet.getString("post_id");
                postIDInt = resultSet.getInt("post_id");
            }

            customerIDFinder2 = "test";
            while (resultSet2.next() && !customerIDFinder.equals(customerIDFinder2)) {
                customerIDFinder2 = resultSet2.getString("customer_id");
                firstNameFinder = resultSet2.getString("first_name");
                lastNameFinder = resultSet2.getString("last_name");
            }

            userLabel.setText(firstNameFinder + " " + lastNameFinder);
            descriptionLabel.setText(descriptionFinder);

            //TEST
            System.out.println(descriptionFinder);
            System.out.println(customerIDFinder);
            System.out.println(customerIDFinder2);
            System.out.println("The post ID is: " + postIDInt);
            System.out.println(firstNameFinder);
            System.out.println(lastNameFinder);
            // END TEST

            connection.close();
            statement.close();
            resultSet.close();

            connection2.close();
            statement2.close();
            resultSet2.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
    }

    public void nextPostButtonAction(ActionEvent event) throws IOException {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from posts");

            Connection connection2 = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");
            Statement statement2 = connection2.createStatement();
            ResultSet resultSet2 = statement2.executeQuery("select * from users");

            postIDInt = postIDInt - 1;
            Integer i = postIDInt;

            String y = "test";
            while (resultSet.next() && !i.toString().equals(y)) {
                y = resultSet.getString("post_id");
                customerIDFinder = resultSet.getString("customer_id");
                descriptionFinder = resultSet.getString("description");
            }

            customerIDFinder2 = "test";
            while (resultSet2.next() && !customerIDFinder.equals(customerIDFinder2)) {
                customerIDFinder2 = resultSet2.getString("customer_id");
                firstNameFinder = resultSet2.getString("first_name");
                lastNameFinder = resultSet2.getString("last_name");
            }
            
            userLabel.setText(firstNameFinder + " " + lastNameFinder);
            descriptionLabel.setText(descriptionFinder);

            //TEST
            System.out.println(i);
            System.out.println("The new postIDInt is: " + y);
            //END test

            connection.close();
            statement.close();
            resultSet.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
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
