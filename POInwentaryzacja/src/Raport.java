import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Raport {
    private final int numer;
    private final LocalDate data;
    private List<Przedmiot> przedmioty;
    private static int numerIteracja = 1;

    public Raport(LocalDate data,List<Przedmiot> przedmioty) {
        this.numer = numerIteracja++;
        this.data = data;
        this.przedmioty = new ArrayList<>(przedmioty);
    }
}
