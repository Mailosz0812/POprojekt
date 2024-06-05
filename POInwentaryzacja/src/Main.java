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
        Menu wiecej = new Menu("Więcej");
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
        MenuItem Drukarka = new MenuItem("Drukarka");
        MenuItem biurko = new MenuItem("Biurko");
        biurko.setOnAction(e -> {
            Biurko.display(b1.getSale());
        });
        MenuItem Komputer = new MenuItem("Komputer");
        MenuItem Monitor = new MenuItem("Monitor");
        MenuItem Mysz = new MenuItem("Mysz");
        MenuItem Projektor = new MenuItem("Projektor");
        MenuItem stol = new MenuItem("Stol");
        MenuItem Szafka = new MenuItem("Szafka");
        MenuItem Tablica = new MenuItem("Tablica");
        MenuItem Krzeslo = new MenuItem("Krzeslo");
        MenuItem usunPrzedmiot = new MenuItem("Usun Przedmiot");
        usunPrzedmiot.setOnAction(e -> {
            Przedmiot.usunPrzedmiotzeStanuDisplay(b1.getSale());
        });
        MenuItem przeniesPrzedmiot = new MenuItem("Przenies Przedmiot");
        MenuItem zapisz = new MenuItem("Zapisz stan aplikacji");
        MenuItem wyszukajPrzedmiot = new MenuItem("Wyszukaj");

//        Dodawanie poszczególnych menu items do menu
        Sale.getItems().addAll(dodajSale,usunSale);
        Inwentaryzacja.getItems().addAll(generujRaport,generujZestawienie);
        przedmiot.getItems().addAll(dodajPrzedmiot,usunPrzedmiot,przeniesPrzedmiot);
        dodajPrzedmiot.getItems().addAll(Drukarka,biurko,Komputer,Monitor,Mysz,Projektor,stol,Szafka,Tablica,Krzeslo);

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
        Scene scene = new Scene(layout,600,500);
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
            but.setOnAction(e -> sala.display());
            but.setPrefSize(100,100);
            widokSal.add(but,col,row);
            col++;
            if(col > Columns){
                col = 0;
                row++;
            }

        }

        
    }
}

















