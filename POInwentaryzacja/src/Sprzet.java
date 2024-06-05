import java.time.LocalDate;

import static java.time.LocalDate.parse;

public abstract class Sprzet extends Przedmiot{
    private static final long  serialVersionUID = 1L;
    private LocalDate ostatniaKonserwacja;
    private final int numerSeryjny;
    private String producent;

    protected Sprzet(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja){
        super(nazwa,data,stanPrzedmiotu);
        this.numerSeryjny = numerSeryjny;
        setProducent(producent);
        setOstatniaKonserwacja(ostatniaKonserwacja);
    }
    protected Sprzet(Sprzet s){
        super(s);
        this.numerSeryjny = s.numerSeryjny;
        this.producent = s.producent;
        this.ostatniaKonserwacja = s.ostatniaKonserwacja;
    }
    @Override
    protected abstract Sprzet clone();

    public void setProducent(String producent){
        if(!producent.matches("^[a-zA-Z]+$")){
            System.out.println("Nazwa producenta sklada sie tylko z liter");
        }
        else{
            this.producent = producent;
        }

    }

    public void setOstatniaKonserwacja(String ostatniaKonserwacja) {
        if(!ostatniaKonserwacja.matches("^(?:(?!0000)\\d{4})-(?:(?:0[1-9]|1[0-2]))-(?:(?:0[1-9]|1\\d|2\\d|3[01]))$")){
            System.out.println("Podano zły format daty");
        }
        else{
            this.ostatniaKonserwacja = parse(ostatniaKonserwacja);
        }

    }
}
