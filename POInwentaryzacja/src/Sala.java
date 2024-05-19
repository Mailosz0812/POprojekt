import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private int numer;
    private List<Przedmiot> przedmioty = new ArrayList<>();
    private List<Przedmiot> stanAkutalny = new ArrayList<>();
    private List<Raport> raporty = new ArrayList<>();

    public Sala(int numer){
        this.numer = numer;
    }

    public void dodajPrzedmiotaktualny(Przedmiot p){
        stanAkutalny.add(p);
    }
    public void dodajPrzedmiotnastan(Przedmiot p){
        przedmioty.add(p);
    }
    public void usunAktualny(int id){
        if(stanAkutalny.isEmpty()){
            System.out.println("Nie ma zadnych przedmiotow akutlanie na stanie lub przedmiot o podanym indeksie nie istnieje");
        }
        else{
            stanAkutalny.remove(id - 1);
        }
    }
    public Raport generujRaport(){
        Raport r = new Raport(LocalDate.now(),this.stanAkutalny);
        raporty.add(r);
        return r;
    }
    public int getNumer(){
        return this.numer;
    }


}
