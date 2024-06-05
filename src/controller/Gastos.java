package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Categorias;
import model.Gasto;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import model.Categorias;

public class Gastos implements Initializable {

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

    @FXML
    private ComboBox<Categorias> cboxCategorias;

    @FXML
    private Button btnAnyadirGasto;

    @FXML
    void agregarComa(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        String cadenaSinEuros="";
        if (cadena.equals("0€") || cadena.equals("0.0€")) {
            lblNumeroUsuarioGastos.setText("0.0€");
        }else{
            cadenaSinEuros = cadena.substring(0, cadena.length()-1);
            lblNumeroUsuarioGastos.setText(cadenaSinEuros + ".€");

        }

    }

    @FXML
    void agregarDigito0(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,0);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void agregarDigito1(ActionEvent event) {
       String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,1);
        lblNumeroUsuarioGastos.setText(cadena);
    }

    @FXML
    void agregarDigito2(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,2);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void agregarDigito3(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,3);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void agregarDigito4(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,4);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void agregarDigito5(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,5);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void agregarDigito6(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,6);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void agregarDigito7(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,7);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void agregarDigito8(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,8);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void agregarDigito9(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = agregarDigito(cadena,9);
        lblNumeroUsuarioGastos.setText(cadena);

    }

    @FXML
    void eliminarDigito(ActionEvent event) {
        String cadena = lblNumeroUsuarioGastos.getText();
        cadena = cadena.substring(0, (cadena.length() - 2));
        lblNumeroUsuarioGastos.setText(cadena+"€");

    }
    @FXML
    void aniadeGasto(ActionEvent event) {

       // System.out.println(gasto.getCantidad());
        //System.out.println(gasto.getCategoria());

        //comprobar que el usuario elija una categoria
        if (cboxCategorias.getValue() == null || lblNumeroUsuarioGastos.getText().equals("0.0€") || lblNumeroUsuarioGastos.getText().equals("0€")) {
            if (cboxCategorias.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Debe elegir una categoría");
                alert.showAndWait();
            }

            //comprobar que el usuario elija una cantidad
            if (lblNumeroUsuarioGastos.getText().equals("0.0€") || lblNumeroUsuarioGastos.getText().equals("0€")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Debe introducir una cantidad");
                alert.showAndWait();
            }

        }else {
            Gasto gasto = new Gasto(lblNumeroUsuarioGastos.getText(),cboxCategorias.getValue().toString(),usuarioActual);
            System.out.println(gasto.getCantidad());
            System.out.println(gasto.getCategoria());
        }

    }


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cboxCategorias.getItems().addAll(Categorias.values());
    }

    public static String agregarDigito(String cadena, int digito) {
        if (cadena.equals("0€") || cadena.equals("")) {
            return digito + "€";
        } else {
            String cadenaSinEuros="";
            cadenaSinEuros = cadena.substring(0, cadena.length() - 1);
            return cadenaSinEuros + digito + "€";
        }
    }
}

