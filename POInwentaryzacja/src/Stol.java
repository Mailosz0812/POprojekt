public class Stol extends Mebel{
    private static final long  serialVersionUID = 1L;
    private final int ileOsob;

    public Stol(String nazwa,String data,String stanPrzedmiotu, String szerokosc,String dlugosc,String wysokosc,int ileOsob){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
        this.ileOsob = ileOsob;
    }
    public Stol(Stol s){
        super(s);
        this.ileOsob = s.ileOsob;
    }

    @Override
    public Stol clone() {
        return new Stol(this);
    }
}
