import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class Monitor extends Sprzet{
    private static final long  serialVersionUID = 1L;

    public Monitor(String nazwa,String data,String stanPrzedmiotu,String numerSeryjny,String producent,String ostatniaKonserwacja){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);
    }
    public Monitor(Monitor m){
        super(m);
    }
    @Override
    public Monitor clone(){
        return new Monitor(this);
    }

    @Override
    public String toString() {
        return "Monitor{nazwa='" + this.nazwa + "', stan=" + this.stanPrzedmiotu + "}";
    }
    public static void displayAddMonitor(List<Sala> s) {
//        Dodawanie zmiennych potrzebny do wyświetlenia okna i widoku formularza
        Stage window = new Stage();
        GridPane layout = new GridPane();
        window.initModality(Modality.APPLICATION_MODAL);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

//        Dodawanie pól formularza
        Label name = new Label("Nazwa przedmiotu");
        TextField inputName = new TextField();
        Label dataZakupu = new Label("Data zakupu");
        TextField inputDataZakupu = new TextField();
        inputDataZakupu.setPromptText("YYYY-MM-DD");
        Label stan = new Label("Stan przedmiotu");
        ChoiceBox<String> stanPrzedmiotu = new ChoiceBox<>();
        stanPrzedmiotu.getItems().addAll("wspaniały", "dobry", "kiepski");
        stanPrzedmiotu.setValue("wspaniały");
        Label konserwacja = new Label("Ostatnia konserwacja");
        TextField inputkonserwacja = new TextField();
        Label nrSeryjny = new Label("Numer Seryjny");
        TextField inputNrSeryjny = new TextField();
        Label producent = new Label("Producent");
        TextField inputProducent = new TextField();
        Button closeButton = new Button("Zamknij");
        Label numerSali = new Label("Podaj numer sali docelowej");
        ChoiceBox<Integer> inputNumerSali = new ChoiceBox<>();
        for (Sala sala : s) {
            inputNumerSali.getItems().add(sala.getNumer());
        }
        closeButton.setOnAction(e -> window.close());
        Button submitButton = new Button("Dodaj przedmiot");
        Label errorMessage = new Label("");
        submitButton.setOnAction(e -> {
            errorMessage.setText("");
            String nazwaValue = inputName.getText();
            String datazakupValue = inputDataZakupu.getText();
            String stanPrzedmiotuValue = stanPrzedmiotu.getValue();
            String datakonserwacjaValue = inputkonserwacja.getText();
            String numerSeryjnyValue = inputNrSeryjny.getText();
            String producentValue = inputProducent.getText();
            try{
                if(inputNumerSali.getValue() != null) {
                    for (Sala sala : s) {
                        if (inputNumerSali.getValue() == sala.getNumer()) {
                            Monitor m = new Monitor(nazwaValue, datazakupValue, stanPrzedmiotuValue, numerSeryjnyValue, producentValue, datakonserwacjaValue);
                            sala.dodajPrzedmiotnastan(m);
                            sala.dodajPrzedmiotAktualny(m);
                            break;
                        }
                    }
                }
                else{
                    errorMessage.setText("Podaj numer sali");
                }
            }catch(IllegalArgumentException e1){
                errorMessage.setText(e1.getMessage());
            }
        });

//        Pozycjonowanie elementów widoku siatki
        GridPane.setConstraints(name,0,0);
        GridPane.setConstraints(inputName,1,0);
        GridPane.setConstraints(dataZakupu,0,1);
        GridPane.setConstraints(inputDataZakupu,1,1);
        GridPane.setConstraints(stan,0,2);
        GridPane.setConstraints(stanPrzedmiotu,1,2);
        GridPane.setConstraints(konserwacja,0,3);
        GridPane.setConstraints(inputkonserwacja,1,3);
        GridPane.setConstraints(nrSeryjny,0,4);
        GridPane.setConstraints(inputNrSeryjny,1,4);
        GridPane.setConstraints(producent,0,5);
        GridPane.setConstraints(inputProducent,1,5);
        GridPane.setConstraints(numerSali,0,6);
        GridPane.setConstraints(inputNumerSali,1,6);
        GridPane.setConstraints(submitButton,0,7);
        GridPane.setConstraints(closeButton,1,7);
        GridPane.setConstraints(errorMessage,3,6);

        layout.getChildren().addAll(name,inputName,dataZakupu,inputDataZakupu,stan,stanPrzedmiotu,konserwacja,inputkonserwacja,nrSeryjny,inputNrSeryjny,producent,inputProducent,numerSali,inputNumerSali,submitButton,closeButton,errorMessage);
        Scene scene = new Scene(layout,650,600);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();
    }
}
