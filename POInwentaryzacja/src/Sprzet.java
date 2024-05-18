import java.time.LocalDate;

public class Sprzet extends Przedmiot{
    private LocalDate ostatniaKonserwacja;
    private int numerSeryjny;
    private String producent;

    public Sprzet(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja){
        super(nazwa,data,stanPrzedmiotu);
        this.numerSeryjny = numerSeryjny;
        setProducent(producent);
        setOstatniaKonserwacja(ostatniaKonserwacja);
    }

    public void setProducent(String producent){
        if(!producent.matches("^[a-zA-Z]+$")){
            System.out.println("Nazwa producenta sklada sie tylko z liter");
        }
        else{
            this.producent = producent;
        }

    }

    public void setOstatniaKonserwacja(String ostatniaKonserwacja) {
        if(!ostatniaKonserwacja.matches("^(?:(?!0000)\\d{4})-(?:(?:0[1-9]|1[0-2]))-(?:(?:0[1-9]|1\\d|2\\d|3[01]))$\n")){
            System.out.println("Podano zły format daty");
        }
        else{
            this.ostatniaKonserwacja = LocalDate.parse(ostatniaKonserwacja);
        }

    }
}
