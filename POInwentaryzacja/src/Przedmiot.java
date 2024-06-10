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
import java.util.NoSuchElementException;
// Klasa abstrakcyjna Przedmiot
public abstract class Przedmiot implements Cloneable, Serializable {
    private static final long  serialVersionUID = 1L;
    protected final String nazwa;
    private LocalDate dataZakupu;
    protected String stanPrzedmiotu;
    private final int id;
    private static int licznikId = 1;

    // Konstruktor inicjalizujący obiekt Przedmiot
    protected Przedmiot(String nazwa, String data, String stanPrzedmiotu){
        this.id = licznikId++;
        setDataZakupu(data);
        this.nazwa = nazwa;
        setStanPrzedmiotu(stanPrzedmiotu);
    }
    // Konstruktor kopiujący
    protected Przedmiot(Przedmiot p){
        this.nazwa = p.nazwa;
        this.id = p.id;
        this.dataZakupu = p.dataZakupu;
        this.stanPrzedmiotu = p.stanPrzedmiotu;

    }
    // Abstrakcyjna metoda klonująca
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
    // Ustawia stan przedmiotu po sprawdzeniu poprawności wartości
    public void setStanPrzedmiotu(String stan){
        if(!(stan.equals("kiepski") || stan.equals("dobry") || stan.equals("wspaniały") )){
            throw new IllegalArgumentException("Stan moze byc tylko kiepski, dobry lub wspaniały");
        }
        else{
            this.stanPrzedmiotu = stan;
        }
    }
    // Zwraca stan przedmiotu
    public String getStanPrzedmiotu() {
        return stanPrzedmiotu;
    }
    // Zwraca unikalny identyfikator przedmiotu
    public int getId() {
        return id;
    }
    // Sprawdza, czy przedmiot jest równy innemu przedmiotowi na podstawie identyfikatora
    public boolean equals(Przedmiot p){
        return this.id == p.id;
    }
    // Sprawdza, czy przedmiot jest nowy (zakupiony w ciągu ostatnich 50 dni)
    public boolean czyNowy(){
        LocalDate l = LocalDate.now();
        return l.isBefore(this.dataZakupu.plusDays(50));
    }
    // Wyświetla okno dialogowe do usunięcia przedmiotu z listy sal
    public static void usunPrzedmiotDisplay(List<Sala> s)
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
        Label usunLabel = new Label("Skąd usunąć przedmiot");
        ChoiceBox<String> inputUsun = new ChoiceBox<>();
        inputUsun.getItems().addAll("Usun ze stanu","Usun aktualny");
        Button closeButton = new Button("Zamknij");
        closeButton.setOnAction(e -> window.close());
        Button usunButton = new Button("Usun przedmiot");
        Label errorMessage = new Label("");
        Przedmiot.setchoosingLogic(s,inputNumerSali,inputPrzedmiot,inputUsun);
        Przedmiot.setusunButton(s,usunButton,errorMessage,inputNumerSali,inputPrzedmiot,inputUsun);
        GridPane.setConstraints(numersali,0,0);
        GridPane.setConstraints(inputNumerSali,1,0);
        GridPane.setConstraints(usunLabel,0,1);
        GridPane.setConstraints(inputUsun,1,1);
        GridPane.setConstraints(przedmiot,0,2);
        GridPane.setConstraints(inputPrzedmiot,1,2);
        GridPane.setConstraints(closeButton,0,3);
        GridPane.setConstraints(usunButton,1,3);
        GridPane.setConstraints(errorMessage,3,3);
        layout.getChildren().addAll(numersali,inputNumerSali,przedmiot,inputPrzedmiot,usunLabel,inputUsun,closeButton,usunButton,errorMessage);
        Scene scene = new Scene(layout,650,600);
        window.setScene(scene);
        window.showAndWait();
    }
    // Zwraca nazwe przedmiotu
    public String getNazwa() {
        return this.nazwa;
    }
    // Aktualizuje listę przedmiotów w zależności od wybranego pola inputUsun
    private static void updatePrzedmiotList(ChoiceBox<Przedmiot> inputPrzedmiot, String usunValue, Sala sala) {
        inputPrzedmiot.getItems().clear();
        if ("Usun ze stanu".equals(usunValue)) {
            inputPrzedmiot.getItems().addAll(sala.getPrzedmioty());
        } else if ("Usun aktualny".equals(usunValue)) {
            inputPrzedmiot.getItems().addAll(sala.getStanAkutalny());
        }
    }
    private static void setchoosingLogic(List<Sala> s,ChoiceBox<Integer> inputNumerSali,ChoiceBox<Przedmiot> inputPrzedmiot,ChoiceBox<String> inputUsun){
        for (Sala sala : s) {
            inputNumerSali.getItems().add(sala.getNumer());
        }
        inputNumerSali.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> {
            if(newValue != null){
                inputPrzedmiot.getItems().clear();
                for (Sala sala : s) {
                    if(sala.getNumer() == newValue){
                        Przedmiot.updatePrzedmiotList(inputPrzedmiot,inputUsun.getValue(),sala);
                        break;
                    }
                }
            }

        });

        inputUsun.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            // Aktualizuj listę przedmiotów w zależności od wybranego pola inputUsun
            updatePrzedmiotList(inputPrzedmiot, newValue, s.get(inputNumerSali.getValue() - 1));
        });
    }
    private static void setusunButton(List<Sala> s, Button usunButton, Label errorMessage, ChoiceBox<Integer> inputNumerSali, ChoiceBox<Przedmiot> inputPrzedmiot, ChoiceBox<String> inputUsun) {
        usunButton.setOnAction(e -> {
            errorMessage.setText("");
            Integer salaNumer = inputNumerSali.getValue();
            Przedmiot p1 = inputPrzedmiot.getValue();
            String usunValue = inputUsun.getValue();

            try {
                for (Sala sala : s) {
                    if (sala.getNumer() == salaNumer) {
                        if ("Usun ze stanu".equals(usunValue)) {
                            sala.usunZeStanu(p1);
                        } else {
                            sala.usunAktualny(p1);
                        }
                        // Aktualizuj listę przedmiotów po usunięciu
                        updatePrzedmiotList(inputPrzedmiot, usunValue, sala);
                        break;
                    }
                }
            } catch (NoSuchElementException e1) {
                errorMessage.setText(e1.getMessage());
            } catch (NullPointerException e1) {
                errorMessage.setText("Wybierz sale");
            }
        });
    }
}
