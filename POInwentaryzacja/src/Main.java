import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.List;
import java.util.Stack;

public class Main extends Application {

    Stage window;
    BorderPane layout;
    GridPane widokSal;
    ScrollPane scrollPane;
    HBox hbox;
    Scene scene;

    public static void main(String[] args){
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Budynek tmpb1 = Budynek.Deserialize();
        if(tmpb1 == null){
            tmpb1 = new Budynek(1);
        }
        Budynek b1 = tmpb1;
        widokSal = new GridPane();
        window = stage;
        layout = new BorderPane();

//        Tworzenie ScrollPane aby strona była scrollowalna kiedy treść nie mieści się w standardowym oknie
        scrollPane = new ScrollPane(widokSal);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        layout.setStyle("-fx-font-size: 15px;");

//        Główny menu bar
        MenuBar mainmenu = new MenuBar();

//        Elementy głównego menu
        Menu Sale = new Menu("Sala");
        Menu przedmiot = new Menu("Przedmiot");
        Button saveButton = new Button("Zapisz stan aplikacji");
        saveButton.setOnAction(e -> b1.Serialize());

//        Dodawanie menu items i ustawianie ich funkcji
        MenuItem dodajSale = new MenuItem("Dodaj Sale");
        dodajSale.setOnAction(e -> {
            Sala.dodajDisplay(b1);
            updateGrid(b1);
        });
        MenuItem usunSale = new MenuItem("Usun Sale");
        usunSale.setOnAction(e -> {
            Sala.usunDisplay(b1);
            updateGrid(b1);
        });
        Menu dodajPrzedmiot = new Menu("Dodaj Przedmiot");
        MenuItem drukarka = new MenuItem("Drukarka");
        drukarka.setOnAction(e -> {Drukarka.displayAddDrukarka(b1.getSale());});
        MenuItem biurko = new MenuItem("Biurko");
        biurko.setOnAction(e -> {
            Biurko.display(b1.getSale());
        });
        MenuItem komputer = new MenuItem("Komputer");
        komputer.setOnAction(e -> {
            Komputer.displayAddKomputer(b1.getSale());
        });

        MenuItem monitor = new MenuItem("Monitor");
        monitor.setOnAction(e -> Monitor.displayAddMonitor(b1.getSale()));
        MenuItem mysz = new MenuItem("Mysz");
        mysz.setOnAction(e -> Mysz.displayAddMysz(b1.getSale()));
        MenuItem projektor = new MenuItem("Projektor");
        projektor.setOnAction(e -> Projektor.displayAddProjektor(b1.getSale()));
        MenuItem stol = new MenuItem("Stol");
        stol.setOnAction(e -> Stol.displayAddStol(b1.getSale()));
        MenuItem szafka = new MenuItem("Szafka");
        szafka.setOnAction(e -> Szafka.displayAddSzafka(b1.getSale()));
        MenuItem tablica = new MenuItem("Tablica");
        tablica.setOnAction(e -> Tablica.displayAddStol(b1.getSale()));
        MenuItem krzeslo = new MenuItem("Krzeslo");
        krzeslo.setOnAction(e -> Krzeslo.displayAddKrzeslo(b1.getSale()));
        MenuItem usunPrzedmiot = new MenuItem("Usuń");
        usunPrzedmiot.setOnAction(e -> Przedmiot.usunPrzedmiotDisplay(b1.getSale()));
        MenuItem przeniesPrzedmiot = new MenuItem("Przenies Przedmiot");

//        Wyszukiwarka przedmiotów
        TextField szukaj = new TextField();
        Button szukajButton = new Button("Szukaj");
        ChoiceBox<String> typWyszukiwania = new ChoiceBox<>();
        typWyszukiwania.getItems().addAll("nazwa","typ");
        typWyszukiwania.setValue("nazwa");
        szukajButton.setOnAction(e -> szukaj(b1.getSale(),typWyszukiwania.getValue(),szukaj.getText()));

//        Dodawanie poszczególnych menu items do menu
        Sale.getItems().addAll(dodajSale,usunSale);
        przedmiot.getItems().addAll(dodajPrzedmiot,usunPrzedmiot,przeniesPrzedmiot);
        dodajPrzedmiot.getItems().addAll(drukarka,biurko,komputer,monitor,mysz,projektor,stol,szafka,tablica,krzeslo);
        mainmenu.getMenus().addAll(Sale,przedmiot);

//        GridPane do widoku sal
        widokSal.setHgap(15);
        widokSal.setVgap(15);
        widokSal.setPadding(new Insets(10,10,10,10));
        widokSal.setAlignment(Pos.TOP_CENTER);
        
//        Ustawianie sceny głównej
        HBox.setHgrow(mainmenu, Priority.ALWAYS);
        HBox.setHgrow(saveButton, Priority.NEVER);
        hbox = new HBox(10,mainmenu,szukaj,szukajButton,typWyszukiwania,saveButton);
        layout.setTop(hbox);
        layout.setCenter(scrollPane);
        scene = new Scene(layout,950,750);
        window.setScene(scene);
        window.setResizable(false);
        updateGrid(b1);
        window.show();
    }

//    Metoda updateGrid odswieza widok sal w siatce po dodaniu lub usunieciu nowej sali
    private void updateGrid(Budynek b1){
        widokSal.getChildren().clear();

        int Columns = 5;
        int row = 1;
        int col = 0;

        for (Sala sala : b1.getSale()) {
            Button but = new Button(sala.toString());
            but.setOnAction(e -> layout.setCenter(sala.display(this,widokSal,b1)));
            but.setPrefSize(100,100);
            widokSal.add(but,col,row);
            col++;
            if(col > Columns){
                col = 0;
                row++;
            }

        }
    }
    public void previousLayout(GridPane previousGrid){
        layout.setCenter(previousGrid);
    }
    public void szukaj(List<Sala> s, String poCzym, String query) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Wyniki wyszukiwania");
        Scene scene;
        // ListView do wyświetlania wyników
        ListView<String> wynikiListView = new ListView<>();
        Label errorMessage = new Label("");

        // Próba uzyskania wyników wyszukiwania
        try {
            ObservableList<Przedmiot> wyniki = wynikiLista(s, poCzym, query);
            if (wyniki.isEmpty()) {
                VBox box = new VBox(errorMessage);
                errorMessage.setText("Nie ma takiego przedmiotu");
                box.setAlignment(Pos.CENTER);
                scene = new Scene(box,250,200);
            } else {
                // Dodanie wyników do ListView
                for (Przedmiot przedmiot : wyniki) {
                    wynikiListView.getItems().add(przedmiot.toString());
                }
                scene = new Scene(wynikiListView,650,600);
            }
            window.setScene(scene);
        } catch (IllegalArgumentException e1) {
            // Jeśli wystąpił błąd, wyświetl błąd wyswietla się komunikat
            VBox box = new VBox(errorMessage);
            errorMessage.setText(e1.getMessage());
            box.setAlignment(Pos.CENTER);
            scene = new Scene(box,250,200);
            window.setScene(scene);
        }
        window.showAndWait();
    }
    public ObservableList<Przedmiot> wynikiLista(List<Sala> s, String poCzym, String query) {
        ObservableList<Przedmiot> wyniki = FXCollections.observableArrayList();
        if (poCzym.equals("typ") && !checkqueryInput(query)) {
            throw new IllegalArgumentException("Nieprawidłowy typ przedmiotu");
        }
        for (Sala sala : s) {
            for (Przedmiot przedmiot : sala.getPrzedmioty()) {
                if ((poCzym.equals("typ") && checkClass(przedmiot, query)) ||
                        (poCzym.equals("nazwa") && przedmiot.getNazwa().equals(query))) {
                    wyniki.add(przedmiot);
                }
            }
        }

        return wyniki;
    }
    private boolean checkqueryInput(String query){
        if(query.equals("Sprzet") || query.equals("Mebel")){
            return true;
        }
        return false;
    }
    private boolean checkClass(Przedmiot p, String className) {
        try {
            Class<?> clazz = Class.forName(className);
            return clazz.isAssignableFrom(p.getClass());
        } catch (ClassNotFoundException e) {
            return false;
        }
    }


}

















