package LBYCPD2;

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
    public int customerIDFinder;

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
            Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");
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
                customerIDFinder = resultSet.getInt("customer_id");
                emailFinder = resultSet.getString("email_address");
                passwordFinder = resultSet.getString("password");
            }

            System.out.println("the customer ID found is: " + customerIDFinder);
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

            readFile();
            //END TEST



            connection.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

        File file2 = new File("src/Database/customer_id");
        FileWriter fw2 = new FileWriter(file2, true);
        PrintWriter pw2 = new PrintWriter(fw2);

        pw.println(email);
        pw2.println(customerIDFinder);

        pw.close();
        fw.close();
        pw2.close();
        fw2.close();
        return email;
    }

    public void closeFile() {x.close(); }
}
