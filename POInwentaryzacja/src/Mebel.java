public abstract class Mebel extends Przedmiot{
    private static final long  serialVersionUID = 1L;
    private int szerokosc;
    private int wysokosc;
    private int dlugosc;

    public Mebel(String nazwa,String data,String stanPrzedmiotu, String szerokosc,String wysokosc,String dlugosc){
        super(nazwa, data, stanPrzedmiotu);
        setSzerokosc(szerokosc);
        setWysokosc(wysokosc);
        setDlugosc(dlugosc);
    }
    public Mebel(Mebel m){
        super(m);
    }

    public void setSzerokosc(String szerokosc) {
        if (!isInt(szerokosc)) {
            throw new IllegalArgumentException("Szerkość musi być liczbą");
        }
        this.szerokosc = Integer.parseInt(szerokosc);
    }

    public void setWysokosc(String wysokosc) {
        if(!isInt(wysokosc)){
            throw new IllegalArgumentException("Wysokość musi być liczbą");
        }
        this.wysokosc = Integer.parseInt(wysokosc);
    }

    public void setDlugosc(String dlugosc) {
        if(!isInt(dlugosc)){
            throw new IllegalArgumentException("Długość musi być liczbą");
        }
        this.dlugosc = Integer.parseInt(dlugosc);
    }

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
