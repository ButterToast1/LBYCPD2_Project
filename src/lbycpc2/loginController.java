package lbycpc2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ResourceBundle;
import java.util.Scanner;

import java.net.URL;

public class loginController implements Initializable {

    public TextField emailTextField;
    public PasswordField passwordField;

    public String email;
    public String password;
    public String emailFinder;
    public String passwordFinder;

    private Scanner x;


    //FROM YOUTUBE VIDEO
    @FXML
    private Button cancelButton;
    @FXML
    private Label statusLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File ("src/Assets/tempLogo.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File ("src/Assets/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    public void cancelButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void loginButtonAction(ActionEvent event) throws IOException{
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("feed.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Ngpmctct_2346");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            //TEST
            // email = emailTextField.getText();
            // password = passwordField.getText();

            email = emailTextField.getText();
            password = passwordField.getText();

            //This is to give these objects a temporary value
            emailFinder = "1";
            passwordFinder = "2";

            System.out.println("the email is: " + email);
            System.out.println("the password is: " + password);

            while (resultSet.next() && !email.equals(emailFinder)) {
                emailFinder = resultSet.getString("email_address");
                passwordFinder = resultSet.getString("password");
            }

            System.out.println("the email found is: " + email);
            System.out.println("the password found is: " + password);

            if (emailTextField.getText().equals(emailFinder) && passwordField.getText().equals(passwordFinder)){
                window.setScene(mainMenuScene);
                window.show();
                System.out.println("variable emailTextField is: " + email + " while password is: "+ password);
            }

            else {
                statusLabel.setVisible(true);
                statusLabel.setText("Incorrect username or password.");
            }
            //END TEST


            while (resultSet.next()) {
                System.out.println(resultSet.getString("email_address"));
            }

            connection.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readSQLfile() {

    }






    //ORIGINAL functions for going to main menu using local database (text files)
    public void menuButton(ActionEvent event) throws IOException {

        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        openFile();
        readFile();
        closeFile();

        if (emailTextField.getText().equals(email) && passwordField.getText().equals(password)){
            window.setScene(mainMenuScene);
            window.show();
            System.out.println("variable emailTextField is: " + email + " while password is: "+ password);
        }

        else {
            statusLabel.setVisible(true);
            statusLabel.setText("Incorrect username or password.");
        }
    }

    public void openFile() {
        try {
            x = new Scanner(new File("src/Database/accounts"));
        }

        catch (Exception e) {
            System.out.println("could not find file");
        }
    }

    public String readFile() throws IOException {
        File file1 = new File("src/Database/profile");
        FileWriter fw = new FileWriter(file1, true);
        PrintWriter pw = new PrintWriter(fw);

        File file2 = new File("src/Database/conditions");
        FileWriter fw2 = new FileWriter(file2, true);
        PrintWriter pw2 = new PrintWriter(fw2);

        email = x.next();
        password = x.next();

        while (x.hasNext() && !email.equals(emailTextField.getText())) {
            email = x.next();
            password = x.next();
        }

        pw.println(email);
        pw.close();

        pw2.println("false");
        pw2.close();

        return email;
    }

    public void closeFile() {x.close(); }
}
