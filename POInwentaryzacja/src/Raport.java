import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Raport implements Serializable {
    private static final long  serialVersionUID = 1L;
    private final int numer;
    private final LocalDate data;
    private final List<Przedmiot> przedmioty;
    private static int numerIteracja = 1;

    public Raport(LocalDate data,List<Przedmiot> przedmioty) {
        this.numer = numerIteracja++;
        this.data = data;
        this.przedmioty = new ArrayList<>(przedmioty);
    }
}
