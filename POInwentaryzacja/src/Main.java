import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
        Menu Inwentaryzacja = new Menu("Inwentaryzacja");
        Menu przedmiot = new Menu("Przedmiot");
        Button saveButton = new Button("Zapisz stan aplikacji");
        saveButton.setOnAction(e -> b1.Serialize());

//        Dodawanie menu items i ustawianie ich funkcji
        MenuItem dodajSale = new MenuItem("Dodaj Sale");
        dodajSale.setOnAction(e -> {
            Sala.dodajDisplay(b1);
            updateGrid(b1.getSale());
        });
        MenuItem usunSale = new MenuItem("Usun Sale");
        usunSale.setOnAction(e -> {
            Sala.usunDisplay(b1);
            updateGrid(b1.getSale());
        });
        MenuItem generujRaport = new MenuItem("Generuj Raport");
        MenuItem generujZestawienie = new MenuItem("Generuj Zestawienie");
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
        MenuItem usunPrzedmiot = new MenuItem("Usun Przedmiot");
        usunPrzedmiot.setOnAction(e -> {
            Przedmiot.usunPrzedmiotDisplay(b1.getSale());
        });
        MenuItem przeniesPrzedmiot = new MenuItem("Przenies Przedmiot");
        MenuItem zapisz = new MenuItem("Zapisz stan aplikacji");
        MenuItem wyszukajPrzedmiot = new MenuItem("Wyszukaj");

//        Dodawanie poszczególnych menu items do menu
        Sale.getItems().addAll(dodajSale,usunSale);
        Inwentaryzacja.getItems().addAll(generujRaport,generujZestawienie);
        przedmiot.getItems().addAll(dodajPrzedmiot,usunPrzedmiot,przeniesPrzedmiot);
        dodajPrzedmiot.getItems().addAll(drukarka,biurko,komputer,monitor,mysz,projektor,stol,szafka,tablica,krzeslo);

        mainmenu.getMenus().addAll(Sale,Inwentaryzacja,przedmiot);

//        GridPane do widoku sal
        widokSal.setHgap(10);
        widokSal.setVgap(10);
        widokSal.setPadding(new Insets(10,10,10,10));
        widokSal.setAlignment(Pos.TOP_CENTER);


        
//        Ustawianie sceny głównej
        HBox.setHgrow(mainmenu, Priority.ALWAYS);
        HBox.setHgrow(saveButton, Priority.NEVER);
        hbox = new HBox(mainmenu,saveButton);
        layout.setTop(hbox);
        layout.setCenter(scrollPane);
        scene = new Scene(layout,600,500);
        window.setScene(scene);

        updateGrid(b1.getSale());
        window.show();
    }

//    Metoda updateGrid odswieza widok sal w siatce po dodaniu lub usunieciu nowej sali
    private void updateGrid(List<Sala> sale){
        widokSal.getChildren().clear();

        int Columns = 4;
        int row = 1;
        int col = 0;

        for (Sala sala : sale) {
            Button but = new Button(sala.toString());
            but.setOnAction(e -> layout.setCenter(sala.display(this,widokSal)));
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

}

















