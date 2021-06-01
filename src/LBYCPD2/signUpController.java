package LBYCPD2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
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


public class signUpController implements Initializable {

    public TextField fNameTextField;
    public TextField lNameTextField;
    public TextField birthdateTextField;
    public TextField emailTextField;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String confirmPassword;
    public String birthdate;

    public String emailFinder;
    public String passwordFinder;
    public int customerIDFinder;

    private Scanner x;

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

    public void menuButtonAction(ActionEvent event) throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("feed.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            firstName = fNameTextField.getText();
            lastName = lNameTextField.getText();
            birthdate = birthdateTextField.getText();
            email = emailTextField.getText();
            password = passwordField.getText();

            String insertQuery = "INSERT INTO users VALUES (DEFAULT, '"+firstName+"', '"+lastName+"', '"+birthdate+"','"+password+"', '"+email+"')";
            statement.executeUpdate(insertQuery);
            System.out.println("Inserted");

            emailFinder = "1";

            //scans the customer-id and email
            readFile();

            //writes customer_id and email to text files
            writeFile();

            window.setScene(mainMenuScene);
            window.show();
            System.out.println("variable emailTextField is: " + email + " while password is: "+ password);

            statement.close();
            connection.close();
            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //OLD functions
    public void menuButton(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene mainMenuScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        File file = new File("src/Database/conditions");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);

        openFile();
        writeFile();
        readFile();
        closeFile();

        if (emailTextField.getText().equals(email) && passwordField.getText().equals(password)){
            pw.println("true");
            pw.close();

            window.setScene(mainMenuScene);
            window.show();
        }

        else {
            statusLabel.setVisible(true);
            statusLabel.setText("Incorrect username or password.");
        }
    }

    public void openFile() {
        try {
            x = new Scanner(new File("src/Database/accounts"));
            //y = new Formatter("C:\\Users\\User\\IdeaProjects\\LTOsoftware\\src\\assets\\accounts");
        }
        catch (Exception e) {
            System.out.println("could not find file");
        }
    }

    public void writeFile() throws IOException{
        File file = new File("src/Database/accounts");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);

        File file2 = new File("src/Database/profile");
        FileWriter fw2 = new FileWriter(file2, true);
        PrintWriter pw2 = new PrintWriter(fw2);

        File file3 = new File("src/Database/customer_id");
        FileWriter fw3 = new FileWriter(file3, true);
        PrintWriter pw3 = new PrintWriter(fw3);

        //firstName = fNameTextField.getText() + " ";
        //lastName = lNameTextField.getText() + " ";
        email = emailTextField.getText() + " ";
        password = passwordField.getText();
        //confirmPassword = confirmPasswordField.getText();

        pw.print(email);
        pw.println(password);

        pw2.println(email);

        pw3.println(customerIDFinder);


        pw.close();
        fw.close();
        pw2.close();
        fw2.close();
        pw3.close();
        fw3.close();
    }

    public void readFile() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/sZpaR7ogSu", "sZpaR7ogSu", "megoO8jjLA");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from users");

            emailFinder = "1";

            while (resultSet.next() && !email.equals(emailFinder)) {
                customerIDFinder = resultSet.getInt("customer_id");
                emailFinder = resultSet.getString("email_address");
            }

            connection.close();
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        x.close();
    }
}
