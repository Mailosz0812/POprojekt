import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Raport {
    private int numer;
    private LocalDate data;
    private List<Przedmiot> przedmioty = new ArrayList<>();
    private static int numerIteracja = 1;

    public Raport(LocalDate data,List<Przedmiot> przedmioty) {
        this.numer = numerIteracja++;
        this.data = data;
         setPrzedmioty(przedmioty);
    }
    public void setPrzedmioty(List<Przedmiot> p){
        for (Przedmiot przedmiot : p) {
            this.przedmioty.add(new Przedmiot(przedmiot));
        }
    }
}
