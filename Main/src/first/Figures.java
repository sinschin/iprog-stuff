package first;

public abstract class Figures implements Printable{
    public abstract String getName();
    @Override
    public String print() {
        return null;
    }
    //Variablen
    static Fraction points; //Points as a fraction, to counter inaccuracy of double and float
    static int x,y; //Position on the board
    //Methoden
    //Move

}
