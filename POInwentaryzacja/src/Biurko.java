public class Biurko extends Mebel{
    private static final long  serialVersionUID = 1L;
    public Biurko(String nazwa,String data,String stanPrzedmiotu,int szerokosc,int wysokosc,int dlugosc){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
    }
    public Biurko(Biurko b){
        super(b);
    }
    @Override
    public Biurko clone(){
        return new Biurko(this);
    }
}
