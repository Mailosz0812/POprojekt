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
    public void przeniesPrzedmiot(Przedmiot p,int numerSaliPoczatkowej,int numerSaliKoncowej){
        for (Sala sala : Sale) {
            if(sala.getNumer() == numerSaliPoczatkowej){
                sala.usunAktualny(p.getId());
            } else if (sala.getNumer() == numerSaliKoncowej) {
                sala.dodajPrzedmiotnastan(new Przedmiot(p));
            }
        }
    }

}
