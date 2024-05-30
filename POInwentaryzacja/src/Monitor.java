public class Monitor extends Sprzet{
    private static final long  serialVersionUID = 1L;
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
    public Monitor clone(){
        return new Monitor(this);
    }

}
