package LBYCPD2;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


import java.io.IOException;


public class logoutPromptController {



    public void yesButtonAction(ActionEvent event) throws IOException{
        Parent loginParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(loginParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void noButtonAction(ActionEvent event) throws IOException {
        Parent feedParent = FXMLLoader.load(getClass().getResource("feed.fxml"));
        Scene feedScene = new Scene(feedParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(feedScene);
        window.show();
    }

}
