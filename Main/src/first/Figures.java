package first;

public abstract class Figures implements Printable{
    public abstract String getName();
    @Override
    public String print(){
        return "   \n "+getName()+" \n   ";
    }
    //Variablen
    Fraction points; //Points as a fraction, to counter inaccuracy of double and float
    int x,y; //Position on the board
    //Methoden

    //Move
    public void move(int x,int y){
        //TODO this is just a placeholder
    }

}
