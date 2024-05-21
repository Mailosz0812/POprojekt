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
    public void dodajPrzedmiotnastan(Przedmiot p){
        stanAkutalny.add(new Przedmiot(p));
        przedmioty.add(new Przedmiot(p));
    }
    public void usunAktualny(int id){
        if(stanAkutalny.isEmpty()){
            System.out.println("Nie ma zadnych przedmiotow akutlanie na stanie lub przedmiot o podanym indeksie nie istnieje");
        }
        else{
            stanAkutalny.remove(id);
        }
    }
    public void usunZeStanu(int id){
        if(przedmioty.isEmpty()){
            System.out.println("Nie ma zadnych przedmiotow na stanie sali lub przedmiot o podanym indeksie nie istnieje");
        }
        else{
            przedmioty.remove(id);
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
    public List<Przedmiot> generujZestawienie(String kryterium){
        List<Przedmiot> zestawienie = new ArrayList<>();
        if(kryterium.equals("brakujace")){
            for(int i = 0; i < przedmioty.size(); i++){
                if(przedmioty.get(i).equals(stanAkutalny.get(i))){
                    zestawienie.add(przedmioty.get(i));
                }
            }
        }
        else if(kryterium.equals("nowe")){
            for(int i = 0; i < przedmioty.size(); i++) {
                if (!przedmioty.get(i).czyNowy()) {
                    zestawienie.add(przedmioty.get(i));
                }
            }
        }
        else{
            for(int i = 0; i < przedmioty.size(); i++) {
                if (!przedmioty.get(i).getStanPrzedmiotu().equals("kiepski")) {
                    zestawienie.add(przedmioty.get(i));
                }
            }
        }
        return zestawienie;
    }
}
