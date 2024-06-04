import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.window;

public class Sala implements Serializable {
    private static final long  serialVersionUID = 1L;
    private final int numer;
    private List<Przedmiot> przedmioty = new ArrayList<>();
    private List<Przedmiot> stanAkutalny = new ArrayList<>();
    private List<Raport> raporty = new ArrayList<>();

    public Sala(int numer){
        this.numer = numer;
    }
    public void dodajPrzedmiotnastan(Przedmiot p){
        stanAkutalny.add(p.clone());
        przedmioty.add(p.clone());
    }
    public void dodajPrzedmiotAktualny(Przedmiot p){
        stanAkutalny.add(p.clone());
    }
    public void usunAktualny(int id){
        if(stanAkutalny.isEmpty()){
            System.out.println("Nie ma zadnych przedmiotow akutlanie na stanie lub przedmiot o podanym indeksie nie istnieje");
        }
        else{
            stanAkutalny.remove(id);
        }
    }
    public void usunZeStanu(int id){
        if(przedmioty.isEmpty()){
            System.out.println("Nie ma zadnych przedmiotow na stanie sali lub przedmiot o podanym indeksie nie istnieje");
        }
        else{
            przedmioty.remove(id);
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
    public List<Przedmiot> generujZestawienie(String kryterium){
        List<Przedmiot> zestawienie = new ArrayList<>();
        if(kryterium.equals("brakujace")){
            for(int i = 0; i < przedmioty.size(); i++){
                if(przedmioty.get(i).equals(stanAkutalny.get(i))){
                    zestawienie.add(przedmioty.get(i));
                }
            }
        }
        else if(kryterium.equals("nowe")){
            for(int i = 0; i < przedmioty.size(); i++) {
                if (!przedmioty.get(i).czyNowy()) {
                    zestawienie.add(przedmioty.get(i));
                }
            }
        }
        else{
            for(int i = 0; i < przedmioty.size(); i++) {
                if (!przedmioty.get(i).getStanPrzedmiotu().equals("kiepskie")) {
                    zestawienie.add(przedmioty.get(i));
                }
            }
        }
        return zestawienie;
    }
    public String toString(){
        return Integer.toString(this.numer);
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


}
