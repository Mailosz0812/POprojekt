import java.time.LocalDate;

import static java.time.LocalDate.parse;
// Klasa abstrakcyjna Sprzet
public abstract class Sprzet extends Przedmiot{
    private static final long  serialVersionUID = 1L;
    protected LocalDate ostatniaKonserwacja;
    protected final String numerSeryjny;
    protected String producent;
    // Konstruktor klasy Sprzet
    protected Sprzet(String nazwa,String data,String stanPrzedmiotu,String numerSeryjny,String producent,String ostatniaKonserwacja){
        super(nazwa,data,stanPrzedmiotu);
        this.numerSeryjny = numerSeryjny;
        setProducent(producent);
        setOstatniaKonserwacja(ostatniaKonserwacja);
    }
    // Konstruktor kopiujący
    protected Sprzet(Sprzet s){
        super(s);
        this.numerSeryjny = s.numerSeryjny;
        this.producent = s.producent;
        this.ostatniaKonserwacja = s.ostatniaKonserwacja;
    }
    protected abstract String toString_2();
    // Metoda abstrakcyjna do klonowania obiektu
    @Override
    protected abstract Sprzet clone();
    // Metoda ustawiająca producenta, sprawdza poprawność formatu
    public void setProducent(String producent){
        if(!producent.matches("^[A-Za-z]*$")){
            throw new IllegalArgumentException("Nazwa producenta składa się tylko z liter");
        }
        else{
            this.producent = producent;
        }

    }
    // Metoda ustawiająca datę ostatniej konserwacji, sprawdza poprawność formatu
    public void setOstatniaKonserwacja(String ostatniaKonserwacja) {
        if(!ostatniaKonserwacja.matches("^(?:(?!0000)\\d{4})-(?:(?:0[1-9]|1[0-2]))-(?:(?:0[1-9]|1\\d|2\\d|3[01]))$")){
            throw new IllegalArgumentException("Podano zły format daty");
        }
        else{
            this.ostatniaKonserwacja = parse(ostatniaKonserwacja);
        }

    }
}
