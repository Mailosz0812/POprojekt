public class Monitor extends Sprzet{
    private double rozmiarEkranu;

    public Monitor(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);
        this.rozmiarEkranu = rozmiarEkranu;
    }
    public Monitor(Monitor m){
        super(m);
        this.rozmiarEkranu = m.rozmiarEkranu;
    }
    @Override
    protected Monitor clone(){
        return new Monitor(this);
    }

}
