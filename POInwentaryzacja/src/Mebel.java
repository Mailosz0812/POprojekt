public abstract class Mebel extends Przedmiot{
    private static final long  serialVersionUID = 1L;
    protected int szerokosc;
    protected int wysokosc;
    protected int dlugosc;
    // Konstruktor inicjalizujący obiekt Mebel
    public Mebel(String nazwa,String data,String stanPrzedmiotu, String szerokosc,String wysokosc,String dlugosc){
        super(nazwa, data, stanPrzedmiotu);
        setSzerokosc(szerokosc);
        setWysokosc(wysokosc);
        setDlugosc(dlugosc);
    }
    // Konstruktor kopiujący
    public Mebel(Mebel m){
        super(m);
    }
    // Ustawia szerokość mebla po sprawdzeniu poprawności formatu
    protected abstract String toString_2();
    public void setSzerokosc(String szerokosc) {
        if (!isInt(szerokosc)) {
            throw new IllegalArgumentException("Szerkość musi być liczbą");
        }
        this.szerokosc = Integer.parseInt(szerokosc);
    }
    // Ustawia wysokość mebla po sprawdzeniu poprawności formatu
    public void setWysokosc(String wysokosc) {
        if(!isInt(wysokosc)){
            throw new IllegalArgumentException("Wysokość musi być liczbą");
        }
        this.wysokosc = Integer.parseInt(wysokosc);
    }
    // Ustawia długość mebla po sprawdzeniu poprawności formatu
    public void setDlugosc(String dlugosc) {
        if(!isInt(dlugosc)){
            throw new IllegalArgumentException("Długość musi być liczbą");
        }
        this.dlugosc = Integer.parseInt(dlugosc);
    }
    // Abstrakcyjna metoda klonująca
    @Override
    protected abstract Mebel clone();
    private boolean isInt(String text){
        try{
            int age = Integer.parseInt(text);
            return true;

        }catch(NumberFormatException e){
            return false;
        }
    }

}
