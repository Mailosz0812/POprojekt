public class Mysz extends Sprzet{
    private boolean czyBezprzewodowa;

    public Mysz(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja,boolean czyBezprzewodowa){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);
        this.czyBezprzewodowa = czyBezprzewodowa;
    }
}
