import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
// Klasa reprezentująca raport
public class Raport implements Serializable {
    private static final long  serialVersionUID = 1L;
    private final int numer;
    private final LocalDate data;
    private final List<Przedmiot> przedmioty;
    private static int numerIteracja = 1;
    // Konstruktor inicjalizujący obiekt Raport z podaną datą i listą przedmiotów
    public Raport(LocalDate data,List<Przedmiot> przedmioty) {
        this.numer = numerIteracja++;
        this.data = data;
        this.przedmioty = new ArrayList<>(przedmioty);
    }
    public String toString(){
        return "Raport: "+data;
    }
    // Wyświetla okno raportu
    public void displayRaport(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Raport nr " + numer);

        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);

        Label numerLabel = new Label("Numer raportu: " + numer);
        Label dataLabel = new Label("Data wygenerowania: " + data);

        ListView<String> przedmiotyListView = new ListView<>();
        ObservableList<String> przedmiotyItems = FXCollections.observableArrayList();

        for (Przedmiot przedmiot : przedmioty) {
            przedmiotyItems.add(przedmiot.toString());
        }
        przedmiotyListView.setItems(przedmiotyItems);

        GridPane.setConstraints(numerLabel, 0, 0);
        GridPane.setConstraints(dataLabel, 0, 1);
        GridPane.setConstraints(przedmiotyListView, 0, 2);

        layout.getChildren().addAll(numerLabel, dataLabel, przedmiotyListView);

        Scene scene = new Scene(layout, 650, 600);
        window.setScene(scene);
        window.showAndWait();
    }

}
