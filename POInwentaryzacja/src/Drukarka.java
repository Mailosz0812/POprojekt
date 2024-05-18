public class Drukarka extends Sprzet{
    double stanPapieru;
    public Drukarka(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja,double stanPapieru){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);
        this.stanPapieru = stanPapieru;
    }
}
