package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import model.Users;

public class Home {

    @FXML
    private Button btnGastos;

    @FXML
    private Button btnIngresos;

    @FXML
    private Label lblSaldo;

    @FXML
    private Label lblWelcomeUsuario;

    @FXML
    private Pane paneMenu;

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




}
