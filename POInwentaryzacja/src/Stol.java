public class Stol extends Mebel{
    private int ileOsob;

    public Stol(String nazwa,String data,String stanPrzedmiotu, int szerokosc,int dlugosc,int wysokosc,int ileOsob){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
        this.ileOsob = ileOsob;
    }
}
