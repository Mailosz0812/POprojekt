public abstract class Mebel extends Przedmiot{
    private static final long  serialVersionUID = 1L;
    private int szerokosc;
    private int wysokosc;
    private int dlugosc;

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
    @Override
    protected abstract Mebel clone();

}
