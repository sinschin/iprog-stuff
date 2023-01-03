package first;

public abstract class Figures implements Printable{

    @Override
    public short printSizeX() {
        return 3;
    }

    @Override
    public short printSizeY() {
        return 3;
    }
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
