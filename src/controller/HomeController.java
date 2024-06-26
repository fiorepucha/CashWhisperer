package controller;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Gasto;
import model.Ingreso;
import model.Movimiento;
import model.Users;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

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
    private ImageView imgHombreTriste;

    @FXML
    private Label lblWelcomeUsuario;

    @FXML
    private Pane paneMenu;

    @FXML
    private Label lblCurrentBalance;

    @FXML
    private Pane paneSaldo;

    @FXML
    private TableView<Movimiento> tableMovimientos;

    @FXML
    private Pane paneResumGastos;

    @FXML
    private Pane paneResumIngresos;

    @FXML
    private ImageView imgCerrarSesion;

    @FXML
    private TableColumn<?, ?> colCategoria;

    @FXML
    private TableColumn<?, ?> colImporte;

    private Users usuarioActual;

    private List<Gasto> gastos = new ArrayList<>();

    private List<Ingreso> ingresos = new ArrayList<>();
    private ObservableList<Movimiento> movimientos;


    public void setUsuarioActual(Users u1){
        this.usuarioActual = u1;
        lblWelcomeUsuario.setText(lblWelcomeUsuario.getText()+usuarioActual.getUsername());
        cargarGastos();
        cargarIngresos();
        cargarSaldo();
        actualizarTablaMovimientos();


    }

    private void actualizarTablaMovimientos() {
        movimientos.clear();
        for (Gasto g : gastos) {
            if (g.getUsuarioUsername().equals(usuarioActual.getUsername())) {
                movimientos.add(g);
            }
        }
        for (Ingreso i : ingresos) {
            if (i.getUsuarioUsername().equals(usuarioActual.getUsername())) {
                movimientos.add(i);
            }
        }
        tableMovimientos.setItems(movimientos);
    }




    @FXML
    void abrirVentanaGastos(ActionEvent event) {
        Users user = usuarioActual;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/gastos.fxml"));
        Parent root;

        try {
            root = loader.load();
            GastosController gastosController = loader.getController();
            gastosController.setUsuarioActual(user);
            gastosController.setGastos(gastos);
            gastosController.setIngresos(ingresos);
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
            IngresosController ingresosController = loader.getController();
            ingresosController.setUsuarioActual(user);
            ingresosController.setIngresos(ingresos);
            ingresosController.setGastos(gastos);
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Label getLblSaldo() {
        return lblSaldo;
    }

    public void setLblSaldo(Label lblSaldo) {
        this.lblSaldo.setText(lblSaldo.getText());
    }

    public Label getLblNumeroGastos() {
        return lblNumeroGastos;
    }

    public void setLblNumeroGastos(Label lblNumeroGastos) {
        this.lblNumeroGastos.setText(lblNumeroGastos.getText());
    }

    public Label getLblNumeroIngresos() {
        return lblNumeroIngresos;
    }

    public void setLblNumeroIngresos(Label lblNumeroIngresos) {
        this.lblNumeroIngresos.setText(lblNumeroIngresos.getText());
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
        guardarTotalGastos();
    }

    private void guardarTotalGastos() {
        double total = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        //recorre la lista de gastos con un for each
        for (Gasto g : gastos) {
            //comprueba si el gasto es del usuario actual
            if (!g.getUsuario().equals(this.usuarioActual.getUsername())) {
            }else {
                //obtiene la cantidad sin el simbolo de €
                String cantidadSinEuros = g.getCantidad().substring(0, g.getCantidad().length() - 1);

                //convierte la cadena a double
                double cantidad = Double.parseDouble(cantidadSinEuros);
                total += cantidad;
            }
        }
        lblNumeroGastos.setText(df.format(total)+"€");
    }

    public void setIngresos(List<Ingreso> ingresos) {
        this.ingresos = ingresos;
        guardarTotalIngresos();
    }

    private void guardarTotalIngresos() {
        double total = 0;
        DecimalFormat df = new DecimalFormat("#.##");

        //recorre la lista de gastos con un for each
        for (Ingreso g : ingresos) {
            //comprueba si el gasto es del usuario actual
            if (!g.getUsuario().equals(this.usuarioActual.getUsername())) {
            }else {
                //obtiene la cantidad sin el simbolo de €
                String cantidadSinEuros = g.getCantidad().substring(0, g.getCantidad().length() - 1);

                //convierte la cadena a double
                double cantidad = Double.parseDouble(cantidadSinEuros);
                total += cantidad;
            }
        }
        lblNumeroIngresos.setText(df.format(total)+"€");
    }

    private void cargarDatos() {
        try {
            Gson gson = new Gson();
            Type gastoListType = new TypeToken<ArrayList<Gasto>>(){}.getType();
            FileReader reader = new FileReader("gastos.json");
            gastos = gson.fromJson(reader, gastoListType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Gson gson = new Gson();
            Type ingresoListType = new TypeToken<ArrayList<Ingreso>>(){}.getType();
            FileReader reader = new FileReader("ingresos.json");
            ingresos = gson.fromJson(reader, ingresoListType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgHombreFeliz.setVisible(false);
        imgHombreTriste.setVisible(false);

        //iniciar las columnas de la tabla
        colImporte.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        movimientos = FXCollections.observableArrayList();
        //crear lista de movimientos y añadir algunos datos de
        cargarDatos(); // Cargar los datos de gastos aquí
        movimientos.addAll(gastos);
        movimientos.addAll(ingresos);
        tableMovimientos.setItems(movimientos);

        lblSaldo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Convertir el valor del saldo a un número
                double saldo = Double.parseDouble(newValue.replace("€", "").replace(",", "."));

                // Si el saldo es mayor que 0, mostrar la imagen feliz, sino mostrar la triste
                if (saldo > 0) {
                    imgHombreFeliz.setVisible(true);
                    imgHombreTriste.setVisible(false);
                } else {
                    imgHombreFeliz.setVisible(false);
                    imgHombreTriste.setVisible(true);
                }
            }
        });

    }


    private void cargarGastos() {
        DecimalFormat df = new DecimalFormat("#.##");
        if (usuarioActual == null) {
            System.out.println("usuarioActual es null");
            return;
        }

        if (gastos == null || gastos.isEmpty()) {
            System.out.println("La lista de gastos está vacía o es null");
            return;
        }

        double total = 0;
        for (Gasto g : gastos) {
            if (g.getUsuario().equals(this.usuarioActual.getUsername())) {
                total += Double.parseDouble(g.getCantidad().substring(0, g.getCantidad().length() - 1));
            }
        }

        lblNumeroGastos.setText(df.format(total) + "€");
    }

    private void cargarIngresos() {
        DecimalFormat df = new DecimalFormat("#.##");

        if (usuarioActual == null) {
            System.out.println("usuarioActual es null");
            return;
        }

        if (ingresos == null || ingresos.isEmpty()) {
            System.out.println("La lista de ingresos está vacía o es null");
            return;
        }

        double total = 0;
        for (Ingreso i : ingresos) {
            if (i.getUsuario().equals(this.usuarioActual.getUsername())) {
                total += Double.parseDouble(i.getCantidad().substring(0, i.getCantidad().length() - 1));
            }
        }

        lblNumeroIngresos.setText(df.format(total) + "€");
    }

    private void cargarSaldo() {
        String gastos;
        String ingresos;
        double total = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        gastos = lblNumeroGastos.getText().substring(0, lblNumeroGastos.getText().length() - 1);
        ingresos = lblNumeroIngresos.getText().substring(0, lblNumeroIngresos.getText().length() - 1);

        gastos = gastos.replace(",", ".");
        ingresos = ingresos.replace(",", ".");


        total = Double.parseDouble(gastos) + Double.parseDouble(ingresos);
        lblSaldo.setText(df.format(total) + "€");
    }

    @FXML
    void cerrarSesion(MouseEvent event) {
        // Cerrar la ventana actual (Home), o Gastos o Ingresos
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/login.fxml"));
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
}