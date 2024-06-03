package controller;

import com.google.gson.Gson;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Users;

import java.io.FileWriter;
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
    private TextField txtLoginUsername;

    @FXML
    private TextField txtRegistrerUsername;

    @FXML
    private PasswordField pswdContrasenya;


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
        if(txtLoginUsername.getText().isEmpty() || pswdContrasenya.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, rellene todos los campos");
            alert.showAndWait();

        }else {
            Users user = new Users(txtLoginUsername.getText(), pswdContrasenya.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/home.fxml"));
            Parent root;

            try {
                root = loader.load();
                Home homeController = loader.getController();
                homeController.setUsuarioActual(user);
                System.out.println("nombre: "+user.getUsername());
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Registrar(ActionEvent event) {
        Users user = new Users(txtLoginUsername.getText(), pswdContrasenya.getText());

        Gson gson = new Gson();
        String json = gson.toJson(user);

        try {
            FileWriter writer = new FileWriter("usuarios.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgLogin.setVisible(true);
    }
}
