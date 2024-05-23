import java.time.LocalDate;

public abstract class Przedmiot implements Cloneable{
    private String nazwa;
    private LocalDate dataZakupu;
    private String stanPrzedmiotu;
    private final int id;
    private static int licznikId = 1;


    protected Przedmiot(String nazwa, String data, String stanPrzedmiotu){
        this.id = licznikId++;
        setDataZakupu(data);
        setNazwa(nazwa);
        setStanPrzedmiotu(stanPrzedmiotu);
    }
    protected Przedmiot(Przedmiot p){
        this.nazwa = p.nazwa;
        this.id = p.id;
        this.dataZakupu = p.dataZakupu;
        this.stanPrzedmiotu = p.stanPrzedmiotu;

    }
    @Override
    protected abstract Przedmiot clone();
    protected void setDataZakupu(String dataZakupu) {
        if(!dataZakupu.matches("^(?:(?!0000)\\d{4})-(?:(?:0[1-9]|1[0-2]))-(?:(?:0[1-9]|1\\d|2\\d|3[01]))$\n")){
            System.out.println("Podano zły format daty");
        }
        else{
            this.dataZakupu = LocalDate.parse(dataZakupu);
        }
    }
    public void setNazwa(String nazwa){
        if(!nazwa.matches("^[a-zA-Z]+$")){
            System.out.println("Nazwa zawiera tylko znaki alfabetyczne");
        }

    }
    public void setStanPrzedmiotu(String stan){
        if(!(stan.equals("kiepski") || stan.equals("dobry") || stan.equals("wspanialy") )){
            System.out.println("Stan przedmiotu moze byc tylko: kiepski, dobry lub wspanialy.");
        }
        else{
            this.stanPrzedmiotu = stan;
        }
    }
    public String getStanPrzedmiotu() {
        return stanPrzedmiotu;
    }

    public int getId() {
        return id;
    }

    public boolean equals(Przedmiot p){
        return this.id == p.id;
    }
    public boolean czyNowy(){
        LocalDate l = LocalDate.now();
        return l.isBefore(this.dataZakupu.plusDays(50));
    }
}
