package first;

public abstract class Figures implements Printable{
    public abstract String getName();
    @Override
    public abstract String print();
    //Variablen
    Fraction points; //Points as a fraction, to counter inaccuracy of double and float
    int x,y; //Position on the board
    //Methoden
    //Move

}
