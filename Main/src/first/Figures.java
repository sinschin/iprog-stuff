package first;

import java.util.Random;
import static first.Main.size;

public class Figures implements Printable{

    public String name;
    @Override
    public String print(){
        return "   \n "+ name +" \n   ";
    }

    @Override
    public Boolean isFraction() {
        return false;
    }

    //Variablen
    Fraction points; //Points as a fraction, to counter inaccuracy of double and float
    int x, y; //Position on the board
    //Methoden
    public Figures(String name) {
        this.name = name;
        points = new Fraction(0);
        setStartPosition();
    }
    //Start position
    private void setStartPosition() {
        Random r = new Random();

        //draws a random number between 0 and size-1 (normally 7)
        x = 0 + r.nextInt(size - 1);
        y = 0 + r.nextInt(size - 1);
        move(0,0);//just to refresh the board position
    }
    //Move
    public void move(int x1,int y1) {
        if(x + x1 > size - 1 || x + x1 < 0 || y + y1 > size - 1 || y + y1 < 0)
        {
            throw new InvalidMoveException("Feld überschritten... erneute Eingabe: ");
        }
//        Fraction fieldPoints = Main.GameField.getFieldPoints(x + x1, y + y1);
        points= points.addition(Main.GameField.getFieldPoints(x + x1, y + y1));
        Main.GameField.field[x][y]=new Fraction(Integer.MAX_VALUE,Integer.MAX_VALUE);
        x += x1;
        y += y1;
        Main.GameField.field[x][y]=this;
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
