import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Klasa reprezentujaca budynek, ktory zawiera wiele sal
public class Budynek implements Serializable {
    private static final long  serialVersionUID = 1L;
    private final int numerBudynku;
    private final List<Sala> Sale = new ArrayList<>();
    // Konstruktor inicjalizujacy obiekt Budynek z podanym numerem budynku
    public Budynek(int numerBudynku){
        this.numerBudynku = numerBudynku;
    }
    // Dodaje salę do listy sal w budynku
    public void dodajSale(Sala s){
        Sale.add(s);
    }
    // Usuwa salę z listy sal na podstawie jej numeru
    public void usunSale(int numer){
        for (Sala sala : this.Sale) {
            if(sala.getNumer() == numer){
                this.Sale.remove(sala);
                break;
            }
        }
    }
    // Zwraca listę sal w budynku
    public List<Sala> getSale() {
        return Sale;
    }
    // Przenosi przedmiot z jednej sali do drugiej
    public void przeniesPrzedmiot(Przedmiot p, int numerSaliPoczatkowej, int numerSaliKoncowej){
        if(numerSaliKoncowej == numerSaliPoczatkowej){
            throw new IllegalArgumentException("Podaj prawidłowy numer sali");
        }
        for (Sala sala : Sale) {
            if(sala.getNumer() == numerSaliPoczatkowej){
                sala.usunZeStanu(p);
                for (Przedmiot p1 : sala.getStanAkutalny()) {
                    if(p.equals(p1)){
                        sala.usunAktualny(p1);
                        break;
                    }
                }
            } else if (sala.getNumer() == numerSaliKoncowej) {
                sala.dodajPrzedmiotnastan(p);
                sala.dodajPrzedmiotAktualny(p);
            }
        }
    }
    //  Serializuje obiekt Budynek do pliku "zapisAplikacji.ser"
    public void Serialize(){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("zapisAplikacji.ser"))){
            outputStream.writeObject(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    // Deserializuje obiekt Budynek z pliku "zapisAplikacji.ser"
    public static Budynek Deserialize(){
        File file = new File("zapisAplikacji.ser");
        if(!file.exists() || file.length() == 0){
            return null;
        }
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
            return (Budynek) inputStream.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }


}
