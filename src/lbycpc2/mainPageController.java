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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {

    public Label home;
    public Label login;
    public Label about;

    @FXML
    private Button cancelButton;
    @FXML
    private Label statusLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView plantingImageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File brandingFile = new File ("src/Assets/tempLogo.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File plantingFile = new File ("src/Assets/planting.png");
        Image plantingImage = new Image(plantingFile.toURI().toString());
        plantingImageView.setImage(plantingImage);
    }

    public void loginButton(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene loginScene = new Scene(loginParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void signUpButton(ActionEvent event) throws IOException {
        Parent signUpParent = FXMLLoader.load(getClass().getResource("signUp2.fxml"));
        Scene signUpScene = new Scene(signUpParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(signUpScene);
        window.show();
    }

    public void aboutButtonAction(ActionEvent event) throws IOException {

    }
}
