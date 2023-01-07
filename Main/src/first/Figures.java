package first;

import java.util.Random;
import static first.Main.size;

public class Figures implements Printable{

    public String name;
    @Override
    public String print(){
        return "   \n "+ name +" \n   ";
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

    public Figures(String name) {
        this.name = name;
    }
    public static Figures genFigure(){
        try {
            return new Figures(Main.FigureNames.charAt(figureId++)+"");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("too many figures");
        }
        return null;
    }
    //FIXME remove this and search for all figures
    private static short figureId = 0;
}
