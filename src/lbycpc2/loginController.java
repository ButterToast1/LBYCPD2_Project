package lbycpc2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class loginController {

    public TextField emailTextField;
    public PasswordField passwordField;
    public Label statusLabel;

    public String email;
    public String password;

    private Scanner x;

    @FXML
    public void push(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Scene mainMenuScene = new Scene(tableViewParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        openFile();
        readFile();
        closeFile();
    }

    public void openFile() {
        try {
            x = new Scanner(new File("C:\\Users\\User\\IdeaProjects\\Farmer's Disease Control Software\\src\\Database\\accounts"));
        }

        catch (Exception e) {
            System.out.println("could not find file");
        }
    }

    public String readFile() throws IOException {
        File file1 = new File("C:\\Users\\User\\IdeaProjects\\Farmer's Disease Control Software\\src\\Database\\profile");
        FileWriter fw = new FileWriter(file1, true);
        PrintWriter pw = new PrintWriter(fw);

        File file2 = new File("C:\\Users\\User\\IdeaProjects\\Farmer's Disease Control Software\\src\\Database\\conditions");
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
