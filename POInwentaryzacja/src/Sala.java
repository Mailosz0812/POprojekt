import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;


public class Sala implements Serializable {
    private static final long  serialVersionUID = 1L;
    private final int numer;
    private final List<Przedmiot> przedmioty = new ArrayList<>();
    private final List<Przedmiot> stanAkutalny = new ArrayList<>();
    private final List<Raport> raporty = new ArrayList<>();

    public Sala(int numer){
        this.numer = numer;
    }
    public void dodajPrzedmiotnastan(Przedmiot p){
        przedmioty.add(p.clone());
    }
    public void dodajPrzedmiotAktualny(Przedmiot p){
        stanAkutalny.add(p.clone());
    }
    public void usunAktualny(Przedmiot p1){
        if(stanAkutalny.isEmpty()){
            throw new NoSuchElementException("Nie ma więcej przedmiotów akutalnie na stanie");
        }
        else{
            stanAkutalny.remove(p1);
        }
    }

    public List<Przedmiot> getPrzedmioty() {
        return przedmioty;
    }

    public List<Przedmiot> getStanAkutalny() {
        return stanAkutalny;
    }

    public void usunZeStanu(Przedmiot p1){
        if(przedmioty.isEmpty()){
            throw new NoSuchElementException("Nie ma więcej przedmiotów na stanie");
        }
        else{
            przedmioty.remove(p1);
        }
    }
    public Raport generujRaport(){
        Raport r = new Raport(LocalDate.now(),this.stanAkutalny);
        raporty.add(r);
        return r;
    }
    public int getNumer(){
        return this.numer;
    }
    public List<Przedmiot> generujZestawienie(String kryterium) {
        if (kryterium == null) {
            throw new NullPointerException("Wybierz kryterium");
        }

        List<Przedmiot> zestawienie = new ArrayList<>();

        switch (kryterium) {
            case "nowe":
                for (Przedmiot przedmiot : stanAkutalny) {
                    if (przedmiot.czyNowy()) {
                        zestawienie.add(przedmiot);
                    }
                }
                break;
            default:
                for (Przedmiot przedmiot : stanAkutalny) {
                    if (przedmiot.getStanPrzedmiotu().equals("kiepski")) {
                        zestawienie.add(przedmiot);
                    }
                }
                break;
        }

        return zestawienie;
    }

    public String toString(){
        return Integer.toString(this.numer);
    }

//    Metoda określająca jak wyświetlac obiekt sali w GUI
public GridPane display(Main main, GridPane previousGrid,Budynek b1) {
    // Dodawanie ObservableList, aby ułatwić wyświetlanie listy przedmiotów na ekranie
    ObservableList<Przedmiot> stanSalilista = FXCollections.observableArrayList(this.przedmioty);
    ObservableList<Przedmiot> stanAktualnySalilista = FXCollections.observableArrayList(this.stanAkutalny);

    // Dodawanie elementów okna
    ListView<Przedmiot> stanSali = new ListView<>(stanSalilista);
    stanSali.setPrefSize(300, 500);
    ListView<Przedmiot> stanAktualnySali = new ListView<>(stanAktualnySalilista);
    stanAktualnySali.setPrefSize(300, 500);

    GridPane listy = new GridPane();
    listy.setHgap(20);
    listy.setVgap(20);
    listy.setPadding(new Insets(15, 15, 20, 20));

    // Dodawanie elementów zawartych w oknie
    Button closeButton = new Button("Zamknij");
    closeButton.setOnAction(e -> main.previousLayout(previousGrid));

    Button generujRaport = new Button("Generuj raport");
    generujRaport.setOnAction(e -> {
        generujRaport();
    });

    Label raportychoicelabel = new Label("Wybierz raport");
    ChoiceBox<Raport> raportychoice = new ChoiceBox<>();
    raportychoice.getItems().addAll(raporty);
    Label kryterium = new Label("Podaj kryterium wyszukiwania");
    ChoiceBox<String> inputkryterium = new ChoiceBox<>();
    inputkryterium.getItems().addAll("nowe", "kiepskie");
    Button wyswietlRaport = new Button("Wyświetl raport");
    setwyswietlraport(wyswietlRaport, raportychoice);
    Button generujZestawienie = new Button("Generuj zestawienie");
    setgenerujZestawienie(generujZestawienie, inputkryterium);
    Label errorMessage = new Label("");
    Label stanPrzedmioty = new Label("Przedmioty na stanie sali:");
    Label stanAktualny = new Label("Przedmioty aktualnie w sali:");
    Label przenoszenieLabel = new Label("Przenoszenie przedmiotów:");
    ChoiceBox<Przedmiot> przedmiotyChoiceBox = new ChoiceBox<>();
    przedmiotyChoiceBox.getItems().addAll(this.przedmioty);
    Button przeniesButton = new Button("Przenieś");
    Label numerSaliLabel = new Label("Podaj numer sali");
    ChoiceBox<Integer> inputNumerSali = new ChoiceBox<>();
    for (Sala sala : b1.getSale()) {
        inputNumerSali.getItems().add(sala.getNumer());
    }
    setprzeniesButton(errorMessage,przedmiotyChoiceBox,przeniesButton,inputNumerSali,b1);
    VBox box = new VBox();
    box.getChildren().add(errorMessage);

    VBox rightPane = new VBox(10);
    rightPane.setPadding(new Insets(10));
    rightPane.setAlignment(Pos.TOP_LEFT);
    rightPane.getChildren().addAll(generujRaport, raportychoicelabel, raportychoice, wyswietlRaport,
            kryterium, inputkryterium, generujZestawienie, przenoszenieLabel, przedmiotyChoiceBox,numerSaliLabel,inputNumerSali,
            przeniesButton, closeButton);

    // Dodawanie data-containers do layoutu zależnie od stanu list przedmiotów
    addElementsToGrid(listy, stanPrzedmioty, stanAktualny, stanSali, stanAktualnySali, box, errorMessage);

    listy.add(rightPane, 2, 0, 1, GridPane.REMAINING);  // Dodajemy przyciski po prawej stronie

    return listy;
}
    private void addElementsToGrid(GridPane listy, Label stanPrzedmioty, Label stanAktualny, ListView<Przedmiot> stanSali, ListView<Przedmiot> stanAktualnySali, VBox box,Label errorMessage) {
        if (this.przedmioty.isEmpty() && this.stanAkutalny.isEmpty()) {
            errorMessage.setText("W tej sali nie ma żadnych przedmiotów.");
            listy.add(stanPrzedmioty, 0, 0);
            listy.add(stanAktualny, 1, 0);
            listy.add(box, 0, 1, 2, 1);
        } else if (this.przedmioty.isEmpty()) {
            errorMessage.setText("Sala nie ma żadnych przedmiotów na stanie.");
            listy.add(stanPrzedmioty, 0, 0);
            listy.add(box, 0, 1);
            listy.add(stanAktualny, 1, 0);
            listy.add(stanAktualnySali, 1, 1);
        } else if (this.stanAkutalny.isEmpty()) {
            errorMessage.setText("Aktualnie w sali nie ma przedmiotów");
            listy.add(stanPrzedmioty, 0, 0);
            listy.add(stanSali, 0, 1);
            listy.add(stanAktualny, 1, 0);
            listy.add(box, 1, 1);
        } else {
            listy.add(stanPrzedmioty, 0, 0);
            listy.add(stanSali, 0, 1);
            listy.add(stanAktualny, 1, 0);
            listy.add(stanAktualnySali, 1, 1);
            listy.add(box,2,2);
        }
    }


    public static void dodajDisplay(Budynek b1){
//        Definiowanie okna aplikacji i zablokowanie innych okien oprócz aktualnego
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sala");

//        Definiowanie i ustawianie widoku siatki
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));

//        Definiowanie elemntow siatki
        Button closeButton = new Button("Zamknij");
        Button addSala = new Button("Dodaj Sale");
        closeButton.setOnAction(e -> window.close());
        Label salaLabel = new Label("Podaj numer nowej sali");
        Label errorMess = new Label();
        TextField inputNumber = new TextField();
        addSala.setOnAction(e -> {
            if(!isInt(inputNumber.getText())){
                errorMess.setText("Musisz wprowadzić liczbę");
            }
            else{
                int number = Integer.parseInt(inputNumber.getText());
                b1.dodajSale(new Sala(number));

            }
        });

//        Pozycjonowanie elemntów w widoku siakti
        GridPane.setConstraints(salaLabel,0,0);
        GridPane.setConstraints(inputNumber,1,0);
        GridPane.setConstraints(closeButton,0,2);
        GridPane.setConstraints(errorMess,1,2);
        GridPane.setConstraints(addSala,2,0);
        gridPane.getChildren().addAll(salaLabel,inputNumber,closeButton,addSala,errorMess);

        Scene scene = new Scene(gridPane,600,500);
        window.setScene(scene);
        window.showAndWait();
    }
    private static boolean isInt(String input){
        try{
            int number = Integer.parseInt(input);
            return true;

        }catch(NumberFormatException e){
            return false;

        }
    }

    public static void usunDisplay(Budynek b1){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Sala");

//        Definiowanie i ustawianie widoku siatki
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10,10,10,10));

//        Definiowanie elemntow siatki
        Button closeButton = new Button("Zamknij");
        Button deleteSala = new Button("Usun Sale");
        closeButton.setOnAction(e -> window.close());
        Label salaLabel = new Label("Podaj numer sali do usunięcia");
        Label errorMess = new Label();
        TextField inputNumber = new TextField();
        deleteSala.setOnAction(e -> {
            if(!isInt(inputNumber.getText())){
                errorMess.setText("Wprowadzone dane nie są liczbą");
            } else if (!isThereaNumber(b1.getSale(),Integer.parseInt(inputNumber.getText()))) {
                errorMess.setText("Nie ma sali o takim numerze");
            } else{
                int num = Integer.parseInt(inputNumber.getText());
                b1.usunSale(num);
            }
        });
//        Pozycjonowanie elemntów w widoku siakti
        GridPane.setConstraints(salaLabel,0,0);
        GridPane.setConstraints(inputNumber,1,0);
        GridPane.setConstraints(closeButton,0,2);
        GridPane.setConstraints(errorMess,1,2);
        GridPane.setConstraints(deleteSala,2,0);
        gridPane.getChildren().addAll(salaLabel,inputNumber,closeButton,deleteSala,errorMess);

        Scene scene = new Scene(gridPane,600,500);
        window.setScene(scene);
        window.showAndWait();

    }

//    Metoda sprawdza czy w istnieje sala o numerze podanym przez uzytkownika
    private static boolean isThereaNumber(List<Sala> l1,int numer){
        for (Sala sala : l1) {
            if(sala.getNumer() == numer){
                return true;
            }
        }
        return false;
    }
    private void setwyswietlraport(Button wyswietlraport,ChoiceBox<Raport> raportChoiceBox){
        wyswietlraport.setOnAction(e -> {
            Raport selectedRaport = raportChoiceBox.getValue();
            if (selectedRaport != null) {
                selectedRaport.displayRaport();
            }
        });
    }
    private void setgenerujZestawienie(Button generujZestawienie,ChoiceBox<String> inputkryterium) {

        generujZestawienie.setOnAction(e -> {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Zestawienie Przedmiotów");

            GridPane layout = new GridPane();
            layout.setVgap(10);
            layout.setHgap(10);
            layout.setPadding(new Insets(10, 10, 10, 10));

            // Generuj zestawienie - tutaj możesz ustawić kryterium wg swoich potrzeb
            try {
                String kryteriumFinal = inputkryterium.getValue();
                List<Przedmiot> zestawienie = generujZestawienie(kryteriumFinal);
                ObservableList<Przedmiot> observableList = FXCollections.observableArrayList(zestawienie);

                ListView<Przedmiot> listView = new ListView<>(observableList);
                listView.setPrefSize(300, 500);

                layout.add(listView, 0, 0);

                Scene scene = new Scene(layout, 650, 600);
                window.setScene(scene);
                window.showAndWait();
            }catch(NullPointerException e1){
                Label errorMessage = new Label(e1.getMessage());
                layout.add(errorMessage,0,0);
                Scene scene = new Scene(layout, 650, 600);
                window.setScene(scene);
                window.showAndWait();
            }
        });
    }
    private void setprzeniesButton(Label errorMessage,ChoiceBox<Przedmiot> inputPrzedmiot,Button przeniesButton,ChoiceBox<Integer> inputNumer,Budynek b1){
        przeniesButton.setOnAction(e -> {
            try{
                b1.przeniesPrzedmiot(inputPrzedmiot.getValue(),this.numer,inputNumer.getValue());
            }catch(IllegalArgumentException e1){
                errorMessage.setText(e1.getMessage());
            }catch(NullPointerException e1){
                errorMessage.setText("Podaj numer sali docelowej");
            }
        });
    }




}
