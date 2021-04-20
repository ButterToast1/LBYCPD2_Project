package lbycpc2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class createPostController {

    public TextArea textArea;

    public String postText;

    private Scanner x;

    public void openFile() {
        try {
            x = new Scanner(new File("src/Database/posts"));
            //y = new Formatter("C:\\Users\\User\\IdeaProjects\\LTOsoftware\\src\\assets\\accounts");
        }
        catch (Exception e) {
            System.out.println("could not find file");
        }
    }

    public void writeFile() throws IOException{
        File file = new File("src/Database/posts");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);

        File file2 = new File("src/Database/posts");
        FileWriter fw2 = new FileWriter(file2, true);
        PrintWriter pw2 = new PrintWriter(fw2);

        postText = textArea.getText() + " ";
        //confirmPassword = confirmPasswordField.getText();

        pw.print(postText);

        pw.close();
        pw2.close();
    }

    public void readFile() {
        postText = x.next();

        while(x.hasNext() && !postText.equals(textArea.getText())) {

            postText = x.next();
        }
    }

    public void closeFile() {
        x.close();
    }
}

