package first;

import java.util.Random;
import static first.Main.size;

public abstract class Figures implements Printable{
    public abstract String getName();
    @Override
    public String print(){
        return "   \n "+getName()+" \n   ";
    }
    //Variablen
    Fraction points; //Points as a fraction, to counter inaccuracy of double and float
    int x, y; //Position on the board
    //Methoden
    public Figures() {
        points = new Fraction(0);
        setStartPosition();
    }
    //Start position
    private void setStartPosition() {
        Random r = new Random();

        x = 0 + r.nextInt(size - 1);
        y = 0 + r.nextInt(size - 1);
    }
    //Move
    public void move(int x1,int y1) throws Exception {
        if(x + x1 > size - 1 || x + x1 < 0 || y + y1 > size - 1 || y + y1 < 0)
        {
            throw new InvalidMoveException("Feld überschritten... erneute Eingabe: ");
        }

        x += x1;
        y += y1;
    }

}
