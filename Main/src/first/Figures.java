package first;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

import static first.Main.figuresList;
import static first.Main.size;
/**
 * @author  Alex Riedel, Thomas Erbes, Tim Sommer
 * @version 1, 10.1.2023
 **/
public class Figures implements Printable{

    public final String name;
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
        points = new Fraction(BigInteger.ZERO);
        setStartPosition();
    }
    //Start position
    private void setStartPosition() {
        Random r = new Random();

        //draws a random number between 0 and size-1 (normally 7)
        x = r.nextInt(size - 1);
        y = r.nextInt(size - 1);
        for (Figures figures : Main.figuresList) {
            if(new Pos(figures.x,figures.y).equals(new Pos(x,y))){
                setStartPosition();
                return;
            }
        }
        Pitch.field[x][y]=new Fraction(0,1); //just made so the move methode adds 0 points
        move(0,0);//just to refresh the board position
    }
    //Move
    public void move(int x1,int y1) {
        if(x + x1 > size - 1 || x + x1 < 0 || y + y1 > size - 1 || y + y1 < 0)
        {
            throw new InvalidMoveException("Field exceeded... try again: ");
        }
        boolean specialMove = Math.abs(x1)+Math.abs(y1) == 2;
        if((specialMove && !((name.equals("W") && x1==1&&y1==-1) || (name.equals("B") && x1==-1&&y1==1)))){
            throw new InvalidMoveException("Unable to do this special move try again: ");
        }
        Pos newPos = new Pos(x+x1,y+y1);
        for (Figures figures : Main.figuresList) {
            if(new Pos(figures.x,figures.y).equals(newPos)){
                throw new InvalidMoveException("Cant move on another player: ");
            }
        }
        //seems like according to this addition function x + 0/1 somehow is less than x and not x
        //so that is why this GREAT fix is here
        Fraction fieldPoints = Main.GameField.getFieldPoints(x + x1, y + y1);
        //we can kinda just skip is useless cpu intensive process if its 0 since It's something + 0
        if(!Objects.equals(fieldPoints.getNumerator(), BigInteger.ZERO)){
            try {
                points= points.addition(fieldPoints);
            }catch (ArithmeticException e){
                System.out.println("FIXME this is not even thrown in the statement");
            }

        }
        Pitch.field[x][y]=new Fraction(0,1);
        x += x1;
        y += y1;
        Pitch.field[x][y]=this;
    }
    public static Figures genFigure(){
        try {
            return new Figures(Main.FigureNames.charAt(figuresList.size())+"");
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("too many figures");
        }
        return null;
    }
}
