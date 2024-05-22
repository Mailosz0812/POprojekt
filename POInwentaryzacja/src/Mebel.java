public class Mebel extends Przedmiot{
    protected int szerokosc;
    protected int wysokosc;
    protected int dlugosc;

    public Mebel(String nazwa,String data,String stanPrzedmiotu, int szerokosc,int dlugosc,int wysokosc){
        super(nazwa, data, stanPrzedmiotu);
        this.szerokosc = szerokosc;
        this.dlugosc = dlugosc;
        this.wysokosc = wysokosc;
    }
    public Mebel(Mebel m){
        super(m);
        this.wysokosc = m.wysokosc;
        this.szerokosc = m.szerokosc;
        this.dlugosc = m.dlugosc;
    }

}
