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

    public static void main(String[] args){
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {
        Budynek b1 = new Budynek(1);
        widokSal = new GridPane();
        window = stage;
        layout = new BorderPane();
        layout.setStyle("-fx-font-size: 15px;");

//        Główny menu bar
        MenuBar mainmenu = new MenuBar();

//        Elementy głównego menu
        Menu Sale = new Menu("Sala");
        Menu Inwentaryzacja = new Menu("Inwentaryzacja");
        Menu Przedmiot = new Menu("Przedmiot");

//        Dodawanie opcji do kolejnych elementow menu i przypisywanie do nich funkcji
        MenuItem dodajSale = new MenuItem("Dodaj Sale");
        dodajSale.setOnAction(e -> {
            Sala.dodajDisplay(b1);
            updateGrid(b1.getSale());
        });
        MenuItem usunSale = new MenuItem("Usun Sale");
        MenuItem generujRaport = new MenuItem("Generuj Raport");
        MenuItem generujZestawienie = new MenuItem("Generuj Zestawienie");
        MenuItem dodajPrzedmiot = new MenuItem("Dodaj Przedmiot");
        MenuItem usunPrzedmiot = new MenuItem("Usun Przedmiot");
        MenuItem przeniesPrzedmiot = new MenuItem("Przenies Przedmiot");
        Sale.getItems().addAll(dodajSale,usunSale);
        Inwentaryzacja.getItems().addAll(generujRaport,generujZestawienie);
        Przedmiot.getItems().addAll(dodajPrzedmiot,usunPrzedmiot,przeniesPrzedmiot);

        mainmenu.getMenus().addAll(Sale,Inwentaryzacja,Przedmiot);

//        GridPane do widoku sal
        widokSal.setHgap(10);
        widokSal.setVgap(10);
        widokSal.setPadding(new Insets(10,10,10,10));

        
//        Ustawianie sceny głównej
        layout.setTop(mainmenu);
        layout.setCenter(widokSal);
        Scene scene = new Scene(layout,600,500);
        window.setScene(scene);
        window.show();
    }
    private void updateGrid(List<Sala> sale){
        widokSal.getChildren().clear();

        int Columns = 4;
        int row = 0;
        int col = 0;

        for (Sala sala : sale) {

            Button but = new Button(sala.toString());
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

















