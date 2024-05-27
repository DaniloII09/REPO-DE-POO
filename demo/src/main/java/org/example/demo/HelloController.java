package org.example.demo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class HelloController{
    @FXML
    private Spinner<Integer>papa;
    @FXML
    private Spinner<Integer>carne;
    @FXML
    private Spinner<Integer>pollo;
    @FXML
    private Spinner<Integer>vegetales;
    @FXML
    private TextField nombre;
    @FXML
    private RadioButton efectivo, tarjeta, emp, est;
    @FXML
    private Label cliente, pago, subtotalPapa, subtotalCarne, subtotalPollo, subtotalVegetales, total;

    private MediaPlayer mediaPlayer;

    @FXML
    private Button playButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button stopButton;



    @FXML
    private void handlePlay() {
        mediaPlayer.play();
    }

    @FXML
    private void handlePause() {
        mediaPlayer.pause();
    }

    @FXML
    private void handleStop() {
        mediaPlayer.stop();
    }

    double precioPapa = 1.25;
    double precioCarne = 2.25;
    double precioPollo = 1.75;
    double precioVegetales = 0.75;
    public void clienteAction(MouseEvent mouseEvent) {
    }
    public void pagoAction(MouseEvent mouseEvent) {
    }
    public void nombreAction(ActionEvent actionEvent) {
    }

    @FXML
    public void initialize() {
        ToggleGroup options = new ToggleGroup();
        ToggleGroup options1 = new ToggleGroup();
        emp.setToggleGroup(options);
        est.setToggleGroup(options);
        efectivo.setToggleGroup(options1);
        tarjeta.setToggleGroup(options1);

        SpinnerValueFactory<Integer> valueFactoryPapa = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        papa.setValueFactory(valueFactoryPapa);

        SpinnerValueFactory<Integer> valueFactoryCarne = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        carne.setValueFactory(valueFactoryCarne);

        SpinnerValueFactory<Integer> valueFactoryPollo = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        pollo.setValueFactory(valueFactoryPollo);

        SpinnerValueFactory<Integer> valueFactoryVegetales = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        vegetales.setValueFactory(valueFactoryVegetales);

        //ChangeListener
        papa.valueProperty().addListener(new ChangeListener<Integer>(){

            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                subtotalPapa.setText("$" + Double.toString(precioPapa * t1));
            }
        });

        carne.valueProperty().addListener(new ChangeListener<Integer>(){

            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                subtotalCarne.setText("$" + Double.toString(precioCarne * t1));
            }
        });

        pollo.valueProperty().addListener(new ChangeListener<Integer>(){

            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                subtotalPollo.setText("$" + Double.toString(precioPollo * t1));
            }
        });

        vegetales.valueProperty().addListener(new ChangeListener<Integer>(){

            @Override
            public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                subtotalVegetales.setText("$" + Double.toString(precioVegetales * t1));
            }
        });

        URL mediaUrl = getClass().getResource("/org/Sounds");
        Media mediaError = new Media(mediaUrl.toExternalForm());
        mediaPlayer = new MediaPlayer(mediaError);
    }
    @FXML
    public void rbEmpleadoAction(){
        if(emp.isSelected()){
            cliente.setText(emp.getText());
        }
    }
    @FXML
    public void rbEstudianteAction(){
        if(est.isSelected()){
            cliente.setText(est.getText());
        }
    }
    @FXML
    public void rbEfectivoAction(){
        if(efectivo.isSelected()){
            pago.setText(efectivo.getText());
        }
    }
    @FXML
    public void rbTarjetaAction(){
        if(tarjeta.isSelected()){
            pago.setText(tarjeta.getText());
        }
    }
    @FXML
    private void handlebtnLimpiarAction() {
        papa.getValueFactory().setValue(0);
        carne.getValueFactory().setValue(0);
        pollo.getValueFactory().setValue(0);
        vegetales.getValueFactory().setValue(0);
        nombre.clear();
        efectivo.isSelected();
        cliente.setText("");
        pago.setText("");
        tarjeta.setSelected(false);
        efectivo.setSelected(false);
        est.setSelected(false);
        emp.setSelected(false);
        total.setText("0.00");
    }
    @FXML
    private void handlebtnComprarAction() {
        if (nombre.getText().length() <= 6) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Nombre no valido");
            alert.setContentText("El nombre del cliente debe de ser de mas de 6 caracteres");
            alert.show();
            return;
        }

        double subtotalPapaValue = papa.getValue() * precioPapa;
        double subtotalCarneValue = carne.getValue() * precioCarne;
        double subtotalPolloValue = pollo.getValue() * precioPollo;
        double subtotalVegetalesValue = vegetales.getValue() * precioVegetales;

        if (subtotalPapaValue == 0 && subtotalCarneValue == 0 && subtotalPolloValue == 0 && subtotalVegetalesValue == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se ha seleccionado ningun producto");
            alert.setContentText("Ingrese un producto para realizar la compra");
            alert.show();
            return;
        }

        if (!efectivo.isSelected() && !tarjeta.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se ha seleccionado ningun metodo de pago");
            alert.setContentText("Seleccion un metodo de pago para continuar");
            alert.show();
            return;
        }

        if (!emp.isSelected() && !est.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("No se ha seleccionado un tipo de cliente");
            alert.setContentText("Seleccione un tipo de cliente para continuar");
            alert.show();
            return;
        }

        double Total = subtotalPapaValue + subtotalCarneValue + subtotalPolloValue + subtotalVegetalesValue;
        total.setText(String.format("$%.2f", Total));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Compra Realizada");
        alert.setHeaderText("Gracias por su compra");
        alert.setContentText("Su compra ha sido realizada exitosamente "+ "\n" + "Total: $" + Total + "\n" + "Nombre: " + nombre.getText() + "\n" + "Forma de pago: " + pago.getText());
        alert.show();
    }

}

//ERROR CON CREDENCIALES XD