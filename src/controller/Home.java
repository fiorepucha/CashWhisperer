package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Users;

import java.io.IOException;

public class Home {

    @FXML
    private Button btnGastos;

    @FXML
    private Button btnIngresos;

    @FXML
    private Button btnHome;

    @FXML
    private Label lblSaldo;

    @FXML
    private Label lblResumenGastos;

    @FXML
    private Label lblResumenIngresos;

    @FXML
    private Label lblNumeroGastos;

    @FXML
    private Label lblNumeroIngresos;

    @FXML
    private ImageView imgHombreFeliz;

    @FXML
    private Label lblWelcomeUsuario;

    @FXML
    private Pane paneMenu;

    @FXML
    private Label lblCurrentBalance;

    @FXML
    private Pane paneSaldo;

    @FXML
    private TableView<?> tableMovimientos;

    @FXML
    private Pane paneResumGastos;

    @FXML
    private Pane paneResumIngresos;

    private Users usuarioActual;

    public void setUsuarioActual(Users u1){
        this.usuarioActual = u1;
        lblWelcomeUsuario.setText(lblWelcomeUsuario.getText()+usuarioActual.getUsername());
    }

    @FXML
    void abrirVentanaGastos(ActionEvent event) {
        Users user = usuarioActual;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/gastos.fxml"));
        Parent root;

        try {
            root = loader.load();
            Gastos gastosController = loader.getController();
            gastosController.setUsuarioActual(user);
            System.out.println("nombre: " + user.getUsername());
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void abrirVentanaIngresos(ActionEvent event) {
        Users user = usuarioActual;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ingresos.fxml"));
        Parent root;

        try {
            root = loader.load();
            Ingresos ingresosController = loader.getController();
            ingresosController.setUsuarioActual(user);
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
