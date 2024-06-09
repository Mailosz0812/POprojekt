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

public class Drukarka extends Sprzet{
    private static final long  serialVersionUID = 1L;
    double stanPapieru;
    public Drukarka(String nazwa,String data,String stanPrzedmiotu,String numerSeryjny,String producent,String ostatniaKonserwacja,double stanPapieru){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);

    }
    public Drukarka(Drukarka d){
        super(d);
        this.stanPapieru = d.stanPapieru;
    }
    private void setStanpapieru(double d){

            if (!(d >= 0 && d <= 100)) {
                throw new IllegalArgumentException("Podaj prawidłową wartość stanu papieru");
            } else {
                this.stanPapieru = d;
            }
    }
    @Override
    public Drukarka clone(){
        return new Drukarka(this);
    }

     @Override
    public String toString() {
        return "Drukarka{nazwa='" + this.nazwa + "', stan=" + this.stanPrzedmiotu + "}";
    }
    public static void displayAddDrukarka(List<Sala> s){
        Stage window = new Stage();
        GridPane layout = new GridPane();
        window.initModality(Modality.APPLICATION_MODAL);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10,10,10,10));

//        Dodawanie pól i przycisków do formularza
        Label name = new Label("Nazwa przedmiotu");
        TextField inputName = new TextField();
        Label dataZakupu = new Label("Data zakupu");
        TextField inputDataZakupu = new TextField();
        inputDataZakupu.setPromptText("YYYY-MM-DD");
        Label stan = new Label("Stan przedmiotu");
        ChoiceBox<String> stanPrzedmiotu = new ChoiceBox<>();
        stanPrzedmiotu.getItems().addAll("wspaniały","dobry","kiepski");
        stanPrzedmiotu.setValue("wspaniały");
        Label konserwacja = new Label("Ostatnia konserwacja");
        TextField inputkonserwacja = new TextField();
        inputkonserwacja.setPromptText("YYYY-MM-DD");
        Label nrSeryjny = new Label("Numer Seryjny");
        TextField inputNrSeryjny = new TextField();
        Label producent = new Label("Producent");
        TextField inputProducent = new TextField();
        Label stanPapieru = new Label("Stan papieru (0-100)%");
        TextField inputStanPapieru = new TextField();
        Button closeButton = new Button("Zamknij");
        Label numerSali = new Label("Podaj numer sali docelowej");
        ChoiceBox<Integer> inputNumerSali = new ChoiceBox<>();
        for (Sala sala : s) {
            inputNumerSali.getItems().add(sala.getNumer());
        }
        closeButton.setOnAction(e -> window.close());
        Button submitButton = new Button("Dodaj przedmiot");
        Label errorMessage = new Label("");
        submitButton.setOnAction(e -> {
            errorMessage.setText("");
            String nazwaValue = inputName.getText();
            String datazakupValue = inputDataZakupu.getText();
            String stanPrzedmiotuValue = stanPrzedmiotu.getValue();
            String datakonserwacjaValue = inputkonserwacja.getText();
            String numerSeryjnyValue = inputNrSeryjny.getText();
            String producentValue = inputProducent.getText();
            try{
                double stanPapieruValue = Double.parseDouble(inputStanPapieru.getText());
                if(inputNumerSali.getValue() != null) {
                    for (Sala sala : s) {
                        if (sala.getNumer() == inputNumerSali.getValue()) {
                            Drukarka d = new Drukarka(nazwaValue,datazakupValue,stanPrzedmiotuValue,numerSeryjnyValue,producentValue,datakonserwacjaValue,stanPapieruValue);
                            sala.dodajPrzedmiotnastan(d);
                            sala.dodajPrzedmiotAktualny(d);
                            break;
                        }
                    }
                }
                else{
                    errorMessage.setText("Podaj numer sali");
                }
            }catch(NumberFormatException e1){
                errorMessage.setText("Wprowadz prawidłowe dane");
            }
            catch(IllegalArgumentException e1){
                errorMessage.setText(e1.getMessage());
            }

        });
        GridPane.setConstraints(name,0,0);
        GridPane.setConstraints(inputName,1,0);
        GridPane.setConstraints(dataZakupu,0,1);
        GridPane.setConstraints(inputDataZakupu,1,1);
        GridPane.setConstraints(stan,0,2);
        GridPane.setConstraints(stanPrzedmiotu,1,2);
        GridPane.setConstraints(konserwacja,0,3);
        GridPane.setConstraints(inputkonserwacja,1,3);
        GridPane.setConstraints(nrSeryjny,0,4);
        GridPane.setConstraints(inputNrSeryjny,1,4);
        GridPane.setConstraints(producent,0,5);
        GridPane.setConstraints(inputProducent,1,5);
        GridPane.setConstraints(stanPapieru,0,6);
        GridPane.setConstraints(inputStanPapieru,1,6);
        GridPane.setConstraints(numerSali,0,7);
        GridPane.setConstraints(inputNumerSali,1,7);
        GridPane.setConstraints(submitButton,0,8);
        GridPane.setConstraints(closeButton,1,8);
        GridPane.setConstraints(errorMessage,3,7);
        layout.getChildren().addAll(name,inputName,dataZakupu,inputDataZakupu,stan,stanPrzedmiotu,konserwacja,inputkonserwacja,nrSeryjny,inputNrSeryjny,producent,inputProducent,stanPapieru,inputStanPapieru,numerSali,inputNumerSali,submitButton,closeButton,errorMessage);
        Scene scene = new Scene(layout,650,600);
        window.setScene(scene);
        window.showAndWait();

    }
}
