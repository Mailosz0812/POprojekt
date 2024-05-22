public class Biurko extends Mebel{
    public Biurko(String nazwa,String data,String stanPrzedmiotu,int szerokosc,int wysokosc,int dlugosc){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
    }
    public Biurko(Biurko b){
        super(b);
    }
}
