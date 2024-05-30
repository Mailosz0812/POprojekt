import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Budynek implements Serializable {
    private static final long  serialVersionUID = 1L;
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
                sala.dodajPrzedmiotnastan(p);
            }
        }
    }
    public void Serialize(){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("stanAplikacji.ser"))){
            outputStream.writeObject(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static Budynek Deserialize(){
        File file = new File("stanAplikacji.ser");
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
