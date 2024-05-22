package controller;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class logincontroller implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnRegistrer;

    @FXML
    private ImageView imgLogin;

    @FXML
    private Hyperlink lnkSignUp;

    @FXML
    private Hyperlink lnkLogin;



    @FXML
    void transicionLogin(ActionEvent event) { //la imagen se moverá hasta el borde izquierdo de la ventana
        TranslateTransition imgTransition = new TranslateTransition(Duration.millis(500), imgLogin);
        imgTransition.setByX(-300);
        imgTransition.play();
    }


    @FXML
    void transicionLoginInversa(ActionEvent event) {
        TranslateTransition imgTransition = new TranslateTransition(Duration.millis(500), imgLogin);
        imgTransition.setByX(300);
        imgTransition.play();
    }

    @FXML
    void login(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/home.fxml"));
        Parent root;

        try {
            root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void Registrar(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgLogin.setVisible(true);
    }
}
