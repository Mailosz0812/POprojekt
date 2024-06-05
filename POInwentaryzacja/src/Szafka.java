public class Szafka extends Mebel{
    private static final long  serialVersionUID = 1L;
    public Szafka(String nazwa,String data,String stanPrzedmiotu, String szerokosc,String dlugosc,String wysokosc){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
    }
    public Szafka(Szafka s){
        super(s);
    }
    @Override
    public Szafka clone(){
        return new Szafka(this);
    }
}
