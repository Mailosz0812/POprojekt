public class Mebel extends Przedmiot{
    private int szerokosc;
    private int wysokosc;
    private int dlugosc;

    public Mebel(String nazwa,String data,String stanPrzedmiotu, int szerokosc,int dlugosc,int wysokosc){
        super(nazwa, data, stanPrzedmiotu);
        this.szerokosc = szerokosc;
        this.dlugosc = dlugosc;
        this.wysokosc = wysokosc;
    }

}
