public class Komputer extends Sprzet{
    private static final long  serialVersionUID = 1L;
    private String procesor;
    private double pojRam;
    private double pojDysku;
    public Komputer(String nazwa,String data,String stanPrzedmiotu,int numerSeryjny,String producent,String ostatniaKonserwacja,String procesor,double pojDysku,double pojRam){
        super(nazwa, data, stanPrzedmiotu, numerSeryjny, producent, ostatniaKonserwacja);
        setProcesor(procesor);
        this.pojDysku = pojDysku;
        this.pojRam = pojRam;
    }
    public Komputer(Komputer k){
        super(k);
        this.pojRam = k.pojRam;
        this.pojDysku = k.pojDysku;
        this.procesor = k.procesor;
    }
    @Override
    public Komputer clone(){
        return new Komputer(this);
    }

    public void setProcesor(String procesor) {
        if(!procesor.matches("^[a-zA-Z0-9]+$")){
            System.out.println("Nazwa procesora nie moze zawierac znakow specjalnych");
        }
        else {
            this.procesor = procesor;
        }
    }
}
