import java.util.ArrayList;
import java.util.List;

public class Budynek {
    private int numerBudynku;
    private List<Sala> Sale = new ArrayList<>();

    public Budynek(int numerBudynku){
        this.numerBudynku = numerBudynku;
    }
    public void dodajSale(Sala s){
        Sale.add(s);
    }

}
