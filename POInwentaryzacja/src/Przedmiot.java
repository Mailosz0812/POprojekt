import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public abstract class Przedmiot implements Cloneable, Serializable {
    private static final long  serialVersionUID = 1L;
    private final String nazwa;
    private LocalDate dataZakupu;
    private String stanPrzedmiotu;
    private final int id;
    private static int licznikId = 1;


    protected Przedmiot(String nazwa, String data, String stanPrzedmiotu){
        this.id = licznikId++;
        setDataZakupu(data);
        this.nazwa = nazwa;
        setStanPrzedmiotu(stanPrzedmiotu);
    }
    protected Przedmiot(Przedmiot p){
        this.nazwa = p.nazwa;
        this.id = p.id;
        this.dataZakupu = p.dataZakupu;
        this.stanPrzedmiotu = p.stanPrzedmiotu;

    }
    @Override
    protected abstract Przedmiot clone();
    protected void setDataZakupu(String dataZakupu) {
        if(!dataZakupu.matches("^(?:(?!0000)\\d{4})-(?:(?:0[1-9]|1[0-2]))-(?:(?:0[1-9]|1\\d|2\\d|3[01]))$")){
            throw new IllegalArgumentException("Podano zły format daty");
        }
        else{
            this.dataZakupu = LocalDate.parse(dataZakupu);
        }
    }

    public void setStanPrzedmiotu(String stan){
        if(!(stan.equals("kiepski") || stan.equals("dobry") || stan.equals("wspaniały") )){
            throw new IllegalArgumentException("Stan moze byc tylko kiepski, dobry lub wspaniały");
        }
        else{
            this.stanPrzedmiotu = stan;
        }
    }
    public String getStanPrzedmiotu() {
        return stanPrzedmiotu;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Przedmiot p){
        return this.id == p.id;
    }
    public boolean czyNowy(){
        LocalDate l = LocalDate.now();
        return l.isBefore(this.dataZakupu.plusDays(50));
    }
    public static void usunPrzedmiotzeStanuDisplay(List<Sala> s)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        GridPane layout = new GridPane();
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10,10,10,10));
        Label numersali = new Label("Wybierz numer sali:");
        ChoiceBox<Integer> inputNumerSali = new ChoiceBox<>();
        Label przedmiot = new Label("Wybierz przedmiot z sali do usunięcia");
        ChoiceBox<Przedmiot> inputPrzedmiot = new ChoiceBox<>();
        Button closeButton = new Button("Zamknij");
        closeButton.setOnAction(e -> window.close());
        Button usunButton = new Button("Usun przedmiot");
        for (Sala sala : s) {
            inputNumerSali.getItems().add(sala.getNumer());
        }
        inputNumerSali.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> {
            if(newValue != null){
                inputPrzedmiot.getItems().clear();
                for (Sala sala : s) {
                    if(sala.getNumer() == newValue){
                        inputPrzedmiot.getItems().addAll(sala.getPrzedmioty());
                        break;
                    }
                }
            }

        });
        usunButton.setOnAction(e -> {
            Integer salaNumer = inputNumerSali.getValue();
            Przedmiot p1 = inputPrzedmiot.getValue();
            for (Sala sala : s) {
                if(sala.getNumer() == salaNumer){
                    sala.usunZeStanu(p1);
                }

            }
        });
        GridPane.setConstraints(numersali,0,0);
        GridPane.setConstraints(inputNumerSali,1,0);
        GridPane.setConstraints(przedmiot,0,1);
        GridPane.setConstraints(inputPrzedmiot,1,1);
        GridPane.setConstraints(closeButton,0,2);
        GridPane.setConstraints(usunButton,1,2);
        layout.getChildren().addAll(numersali,inputNumerSali,przedmiot,inputPrzedmiot,closeButton,usunButton);
        Scene scene = new Scene(layout,650,600);
        window.setScene(scene);
        window.showAndWait();
    }

}
