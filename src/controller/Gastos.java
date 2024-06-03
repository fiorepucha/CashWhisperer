package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Users;

import java.io.IOException;

public class Gastos {

    @FXML
    private Button btnGastos;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnIngresos;

    @FXML
    private Label lblWelcomeUsuario;

    @FXML
    private Pane paneMenu;

    @FXML
    private Pane paneNumeroGastos;

    @FXML
    private Label lblNumeroUsuarioGastos;

    @FXML
    private Button btnTeclado1;

    @FXML
    private Button btnTeclado2;

    @FXML
    private Button btnTeclado3;

    @FXML
    private Button btnTeclado4;

    @FXML
    private Button btnTeclado5;

    @FXML
    private Button btnTeclado6;

    @FXML
    private Button btnTeclado7;

    @FXML
    private Button btnTeclado8;

    @FXML
    private Button btnTeclado9;

    @FXML
    private Button btnTecladoComa;

    @FXML
    private Button btnTecladoDelete;

    private Users usuarioActual;


    public void setUsuarioActual(Users u1){
        this.usuarioActual = u1;
        lblWelcomeUsuario.setText(lblWelcomeUsuario.getText()+usuarioActual.getUsername());
    }

    @FXML
    void abrirVentanaHome(ActionEvent event) {
        Users user = usuarioActual;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/home.fxml"));
        Parent root;

        try {
            root = loader.load();
            Home controller = loader.getController();
            controller.setUsuarioActual(user);
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

