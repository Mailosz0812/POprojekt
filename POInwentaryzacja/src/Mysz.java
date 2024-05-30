public class Mysz extends Sprzet{
    private static final long  serialVersionUID = 1L;
    private boolean czyBezprzewodowa;

    public Mysz(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja,boolean czyBezprzewodowa){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);
        this.czyBezprzewodowa = czyBezprzewodowa;
    }
    public Mysz(Mysz m){
        super(m);
        this.czyBezprzewodowa = m.czyBezprzewodowa;
    }
    @Override
    public Mysz clone(){
        return new Mysz(this);
    }
}
