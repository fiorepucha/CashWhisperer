package controller;

import com.google.gson.*;
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

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
    private PasswordField pswdRegistrerContrasenya;



    @FXML
    void transicionLogin(ActionEvent event) { //la imagen se moverá hasta el borde izquierdo de la ventana
        lnkSignUp.setMouseTransparent(true);

        TranslateTransition imgTransition = new TranslateTransition(Duration.millis(500), imgLogin);
        imgTransition.setByX(-300);
        imgTransition.play();
        imgTransition.setOnFinished(e -> lnkSignUp.setMouseTransparent(false));

    }


    @FXML
    void transicionLoginInversa(ActionEvent event) {
        lnkLogin.setMouseTransparent(true);
        TranslateTransition imgTransition = new TranslateTransition(Duration.millis(500), imgLogin);
        imgTransition.setByX(300);
        imgTransition.play();
        imgTransition.setOnFinished(e -> lnkLogin.setMouseTransparent(false));
    }

    @FXML
    void login(ActionEvent event) {
        if(txtLoginUsername.getText().isEmpty() || pswdContrasenya.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, rellene todos los campos");
            alert.showAndWait();

        }else {
            //comprobar si el usuario y la contrasenya estan en el json
            Users user = null;
            for (Users u : Users.getUsers()) {
                if (txtLoginUsername.getText().equals(u.getUsername()) && pswdContrasenya.getText().equals(u.getPassword())) {
                    user = u;
                }

            }
            try{
                FileReader reader = new FileReader("users.json");
                JsonParser parser = new JsonParser();
                JsonElement jsonElement = parser.parse(reader);
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                boolean encontrado = false;
                for (JsonElement json : jsonArray) {
                    JsonObject jsonObject = json.getAsJsonObject();
                    if (jsonObject.get("username").getAsString().equals(txtLoginUsername.getText()) && jsonObject.get("password").getAsString().equals(pswdContrasenya.getText())) {
                        encontrado = true;
                        user = new Users(jsonObject.get("username").getAsString(), jsonObject.get("password").getAsString());
                    }
                }
                if (encontrado){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/home.fxml"));
                    Parent root;

                    try {
                        root = loader.load();
                        HomeController homeController = loader.getController();
                        homeController.setUsuarioActual(user);
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Usuario o contrasenya incorrectos");
                    alert.showAndWait();
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void registrar(ActionEvent event) {
        String username = txtRegistrerUsername.getText();
        String password = pswdRegistrerContrasenya.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, rellene todos los campos");
            alert.showAndWait();
            return;
        }

        try {
            Users user = new Users(username, password);
            List<Users> users = new ArrayList<>();
            users.add(user);
            FileReader reader = new FileReader("users.json");
            JsonParser parser = new JsonParser();
            JsonElement json = parser.parse(reader);
            JsonArray jsonArray = json.getAsJsonArray();

            Gson gson = new Gson();
            String nuevoUser = gson.toJson(user);
            JsonElement nuevoUserElement = new JsonParser().parse(nuevoUser);
            jsonArray.add(nuevoUserElement);

            FileWriter writer = new FileWriter("users.json");
            gson.toJson(jsonArray, writer);
            writer.close();

            txtLoginUsername.setText(txtRegistrerUsername.getText());
            pswdContrasenya.setText(pswdRegistrerContrasenya.getText());
            transicionLoginInversa(event);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgLogin.setVisible(true);
    }
}
