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
        for (Figures figures : Main.figuresList) {
            if(new Pos(figures.x,figures.y).equals(new Pos(x,y))){
                setStartPosition();
                return;
            }
        }
        Main.GameField.field[x][y]=new Fraction(0,1);
        move(0,0);//just to refresh the board position
    }
    //Move
    public void move(int x1,int y1) {
        if(x + x1 > size - 1 || x + x1 < 0 || y + y1 > size - 1 || y + y1 < 0)
        {
            throw new InvalidMoveException("Field exceeded... try again: ");
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
        if(fieldPoints.getNumerator()!=0){
            try {
//                float before = points.floatValue();
//                Fraction bp = points;
                points= points.addition(fieldPoints);
//                float after =points.floatValue();
//                if(before>after){
//                    System.out.println("we lost points somehow?");
//                    bp.addition(fieldPoints);
//                }
            }catch (ArithmeticException e){
                System.out.println("well that's it\nFIXME this is broken due to us not being able to count that high (we can count higher but the problem is that the nominator or denominator is too big for an int) with that stupid useless and trash Fraction system \nTODO figure something out we might have to move the whole Fraction to a BigInt\nand that wound be a big blow to performance (not like we even remotely cared for that)");
            }

        }
        Main.GameField.field[x][y]=new Fraction(0,1);
        x += x1;
        y += y1;
        Main.GameField.field[x][y]=this;
    }
    public static Figures genFigure(){
        try {
            return new Figures(Main.FigureNames.charAt(figureId++)+"");
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("too many figures");
        }
        return null;
    }
    //FIXME remove this and search for all figures
    private static short figureId = 0;
}
