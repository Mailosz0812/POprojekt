public class Szafka extends Mebel{
    public Szafka(String nazwa,String data,String stanPrzedmiotu, int szerokosc,int dlugosc,int wysokosc){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
    }
    public Szafka(Szafka s){
        super(s);
    }
    protected Szafka clone(){
        return new Szafka(this);
    }
}
