import java.time.LocalDate;

public class Przedmiot {
    private String nazwa;
    private LocalDate dataZakupu;
    private String stanPrzedmiotu;
    private int id;
    private static int licznikId = 1;

    public Przedmiot(String nazwa,String data,String stanPrzedmiotu){
        this.id = licznikId++;
        setDataZakupu(data);
        setNazwa(nazwa);
        setStanPrzedmiotu(stanPrzedmiotu);
    }
    public Przedmiot(Przedmiot p){
        this.id = p.id;
        this.dataZakupu = p.dataZakupu;
        this.stanPrzedmiotu = p.stanPrzedmiotu;

    }
    public void setDataZakupu(String dataZakupu) {
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
            System.out.println("Stan przedmiotu moze byc tylko: kiepski, dobry lub wspanialy");
        }
        else{
            this.stanPrzedmiotu = stan;
        }
    }

}
