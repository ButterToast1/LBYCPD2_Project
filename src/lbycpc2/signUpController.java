package lbycpc2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class signUpController {

    public TextField fNameTextField;
    public TextField lNameTextField;
    public TextField emailTextField;
    public PasswordField passwordField;
    public PasswordField confirmPasswordField;
    public Label statusLabel;

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String confirmPassword;

    private Scanner x;

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

        //firstName = fNameTextField.getText() + " ";
        //lastName = lNameTextField.getText() + " ";
        email = emailTextField.getText() + " ";
        password = passwordField.getText();
        //confirmPassword = confirmPasswordField.getText();

        pw.print(email);
        pw.println(password);

        pw.close();
        pw2.close();
    }

    public void readFile() {
        email = x.next();
        password = x.next();

        while(x.hasNext() && !email.equals(emailTextField.getText())) {

            email = x.next();
            password = x.next();
        }
    }

    public void closeFile() {
        x.close();
    }
}
