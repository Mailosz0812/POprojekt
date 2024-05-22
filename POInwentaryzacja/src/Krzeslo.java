public class Krzeslo extends Mebel {
    private String kolor;
    public Krzeslo(String nazwa,String data,String stanPrzedmiotu, int szerokosc,int dlugosc,int wysokosc,String kolor){
        super(nazwa, data, stanPrzedmiotu, szerokosc, dlugosc, wysokosc);
        setKolor(kolor);
    }
    public Krzeslo(Krzeslo k1){
        super(k1);
        this.kolor = k1.kolor;
    }
    @Override
    protected Krzeslo clone(){
        return new Krzeslo(this);

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
