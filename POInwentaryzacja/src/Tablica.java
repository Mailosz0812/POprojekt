public class Tablica extends Mebel{
    private static final long  serialVersionUID = 1L;
    public Tablica(String nazwa,String data,String stanPrzedmiotu, String szerokosc,String dlugosc,String wysokosc){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
    }
    public Tablica(Tablica t){
        super(t);
    }
    @Override
    public Tablica clone(){
        return new Tablica(this);
    }
}
