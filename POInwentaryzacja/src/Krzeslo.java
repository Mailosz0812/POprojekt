public class Krzeslo extends Mebel{
    private String kolor;
    public Krzeslo(String nazwa,String data,String stanPrzedmiotu, int szerokosc,int dlugosc,int wysokosc,String kolor){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
        setKolor(kolor);
    }
    public void setKolor(String kolor){
        if(!kolor.matches("^[a-zA-Z]+$")){
            System.out.println("Nazwa koloru zawiera tylko znaki alfabetyczne");
        }
        else{
            this.kolor = kolor;
        }
    }
}
