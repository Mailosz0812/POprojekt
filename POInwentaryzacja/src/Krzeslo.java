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
// Klasa Krzeslo dziedzicząca z klasy Mebel
public class Krzeslo extends Mebel {
    private static final long  serialVersionUID = 1L;
    private String kolor;
    // Konstruktor klasy Krzeslo
    public Krzeslo(String nazwa,String data,String stanPrzedmiotu, String szerokosc,String dlugosc,String wysokosc,String kolor){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
        setKolor(kolor);
    }
    // Konstruktor kopiujący
    public Krzeslo(Krzeslo k1){
        super(k1);
        this.kolor = k1.kolor;
    }
    // Metoda klonująca obiekt Krzeslo
    @Override
    public Krzeslo clone(){
        return new Krzeslo(this);

    }
    // Nadpisana metoda toString
    @Override
    public String toString() {
        return "Krzeslo{nazwa='" + this.nazwa + "', stan=" + this.stanPrzedmiotu + "}";
    }
    public String toString_2() {
        return "Krzeslo{nazwa='" + this.nazwa + "',\nstan=" + this.stanPrzedmiotu + ",\ndata zakupu=" + this.data + ",\nszerokosc=" + this.szerokosc + ",\nwysokosc=" + this.wysokosc + ",\ndlugosc=" + this.dlugosc + ",\nkolor=" + this.kolor + "}";
    }
    // Metoda ustawiająca kolor
    public void setKolor(String kolor){
        if(!kolor.matches("^[a-zA-Z]+$")){
            throw new IllegalArgumentException("Kolor składa się tylko z liter :)");
        }
        else{
            this.kolor = kolor;
        }
    }
    // Statyczna metoda do wyświetlania okna dodawania nowego krzesła
    public static void displayAddKrzeslo(List<Sala> s){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10,10,10,10));

    // Dodawanie pól i przycisków do formularza
        Label name = new Label("Nazwa przedmiotu");
        TextField inputName = new TextField();
        Label dataZakupu = new Label("Data zakupu");
        TextField inputDataZakupu = new TextField();
        inputDataZakupu.setPromptText("YYYY-MM-DD");
        Label stan = new Label("Stan przedmiotu");
        ChoiceBox<String> stanPrzedmiotu = new ChoiceBox<>();
        stanPrzedmiotu.getItems().addAll("wspaniały","dobry","kiepski");
        stanPrzedmiotu.setValue("wspaniały");
        Label szerokosc = new Label("Szerokość");
        TextField inputSzerokosc = new TextField();
        Label wysokosc = new Label("Wysokość");
        TextField inputwysokosc = new TextField();
        Label dlugosc = new Label("Długość");
        TextField inputDlugosc= new TextField();
        Label kolor = new Label("Podaj kolor");
        TextField inputKolor = new TextField();
        Button closeButton = new Button("Zamknij");
        Label numerSali = new Label("Podaj numer sali docelowej");
        ChoiceBox<Integer> inputNumerSali = new ChoiceBox<>();
        for (Sala sala : s) {
            inputNumerSali.getItems().add(sala.getNumer());
        }
        // Akcja dla przycisku zamknięcia
        closeButton.setOnAction(e -> window.close());
        Button submitButton = new Button("Dodaj przedmiot");
        Label errorMessage = new Label("");
        // Akcja dla przycisku dodania przedmiotu
        submitButton.setOnAction(e -> {
            errorMessage.setText("");
            String nazwa = inputName.getText();
            String data = inputDataZakupu.getText();
            String stanPrzedmiotuValue = stanPrzedmiotu.getValue();
            String szerokoscValue = inputSzerokosc.getText();
            String wysokoscValue = inputwysokosc.getText();
            String dlugoscvalue = inputDlugosc.getText();
            String kolorValue = inputKolor.getText();
            try{
                if(inputNumerSali.getValue() != null) {
                    for (Sala sala : s) {
                        if (sala.getNumer() == inputNumerSali.getValue()) {
                            Krzeslo krzeslo = new Krzeslo(nazwa, data, stanPrzedmiotuValue, szerokoscValue, wysokoscValue, dlugoscvalue,kolorValue);
                            sala.dodajPrzedmiotnastan(krzeslo);
                            sala.dodajPrzedmiotAktualny(krzeslo);
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
        // Pozycjonowanie elementów w widoku siatki
        GridPane.setConstraints(name,0,0);
        GridPane.setConstraints(inputName,1,0);
        GridPane.setConstraints(dataZakupu,0,1);
        GridPane.setConstraints(inputDataZakupu,1,1);
        GridPane.setConstraints(stan,0,2);
        GridPane.setConstraints(stanPrzedmiotu,1,2);
        GridPane.setConstraints(szerokosc,0,3);
        GridPane.setConstraints(inputSzerokosc,1,3);
        GridPane.setConstraints(dlugosc,0,4);
        GridPane.setConstraints(inputDlugosc,1,4);
        GridPane.setConstraints(wysokosc,0,5);
        GridPane.setConstraints(inputwysokosc,1,5);
        GridPane.setConstraints(kolor,0,6);
        GridPane.setConstraints(inputKolor,1,6);
        GridPane.setConstraints(numerSali,0,7);
        GridPane.setConstraints(inputNumerSali,1,7);
        GridPane.setConstraints(submitButton,0,8);
        GridPane.setConstraints(closeButton,1,8);
        GridPane.setConstraints(errorMessage,3,7);

        // Dodawanie elementów do widoku siatki i konfiguracja wyświetlania okna
        layout.getChildren().addAll(name,inputName,dataZakupu,inputDataZakupu,stan,stanPrzedmiotu,szerokosc,inputSzerokosc,dlugosc,inputDlugosc,wysokosc,inputwysokosc,kolor,inputKolor,numerSali,inputNumerSali,submitButton,closeButton,errorMessage);


        Scene scene = new Scene(layout,650,600);
        window.setScene(scene);
        window.setResizable(false);
        window.showAndWait();

    }
}
