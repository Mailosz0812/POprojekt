public class Projektor extends Sprzet{
    private static final long  serialVersionUID = 1L;
    private String rozdzielczosc;

    public Projektor(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja,String rozdzielczosc){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);
        setRozdzielczosc(rozdzielczosc);

    }
    public Projektor(Projektor p){
        super(p);
        this.rozdzielczosc = p.rozdzielczosc;
    }
    public void setRozdzielczosc(String rozdzielczosc){
        if(!rozdzielczosc.matches("^\\d+x\\d+$")){
            System.out.println("Rozdzielczosc podana w zlej formie");
        }
        else{
            this.rozdzielczosc = rozdzielczosc;
        }
    }
    @Override
    public Projektor clone(){
        return new Projektor(this);
    }
}
