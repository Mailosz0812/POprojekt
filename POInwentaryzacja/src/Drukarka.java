public class Drukarka extends Sprzet{
    private static final long  serialVersionUID = 1L;
    double stanPapieru;
    public Drukarka(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja,double stanPapieru){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);
        this.stanPapieru = stanPapieru;
    }
    public Drukarka(Drukarka d){
        super(d);
        this.stanPapieru = d.stanPapieru;
    }
    @Override
    public Drukarka clone(){
        return new Drukarka(this);
    }
}
