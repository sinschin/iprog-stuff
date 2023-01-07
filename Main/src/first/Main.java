package first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static String FigureNames ="WBACDEFGHIJKLMNOPQRSTUVXYZ";
    static Pitch GameField;
    static Thread inputHandler = null;
    static ArrayList<Figures> figuresList = new ArrayList<>();
    static int turn = 0;
    public static int size = 8; //size of the playing field, standard 8

    public static void main(String[] args) {
        GameField = new Pitch();
        figuresList.add(Figures.genFigure());
        figuresList.add(Figures.genFigure());
        startGame();
        printField();
    }

    //Shows the playing field
    //TODO an option to show W/B and empty fields (currently the would be shown as 0/1).
    public static void printField(){
        turn++;

        int numerator, denominator;

        final String fieldSpacerStart  =      "┌───────┬───────┬───────┬───────┬───────┬───────┬───────┬───────┐";
        final String fieldSpacerMiddle =      "├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤";
        final String fieldSpacerEnd    =      "└───────┴───────┴───────┴───────┴───────┴───────┴───────┴───────┘";
        final String fieldNormal       =      "│  000  │  111  │  222  │  333  │  444  │  555  │  666  │  777  │";
        final String fieldNormalSeparator=    "│  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │";


        System.out.println(fieldSpacerStart);
        HashMap<Pos,Printable> allFields = new HashMap<>();
        for (int i = 0; i < Pitch.field.length; i++) {
            Printable[] allX = Pitch.field[i];
            for (int i1 = 0; i1 < allX.length; i1++) {
                allFields.put(new Pos(i,i1),allX[i1]);
            }
        }
        for (int i = 0; i < Pitch.field.length; i++) {
            Printable[] allX = Pitch.field[i];
            String row0 = fieldNormal;
            String row1 = fieldNormal;
            String row2 = fieldNormal;
            for (int j = 0; j < allX.length; j++) {
                String[] printOutSplit = allX[j].print().split("\n");
                row0=row0.replace(j+""+j+""+j,printOutSplit[0]);
                row1=row1.replace(j+""+j+""+j,printOutSplit[1]);
                row2=row2.replace(j+""+j+""+j,printOutSplit[2]);
            }
            System.out.println(row0);
            System.out.println(row1);
            System.out.println(row2);
            if (i !=size-1) { System.out.println(fieldSpacerMiddle);}
        }

//        for (int i = 0; i < size; i++) {
//            String row0 = fieldNormal;
//            String row1 = fieldNormalSeparator;
//            String row2 = fieldNormal;
////            DecimalFormat decimalFormat = new DecimalFormat("###");
//            //replaces the row with the numerators
//            for (int j = 0; j < size; j++) {
//                numerator =  GameField.getNumerator(i,j);
//                row0=row0.replace(j+""+j+""+j,String.format("%03d", numerator));
//            }
//
//            //replaces the row with the denominators
//            for (int j = 0; j < size; j++) {
//                denominator =  GameField.getDenominator(i,j);
//                row2=row2.replace(j+""+j+""+j,String.format("%03d", denominator));
//            }
//            System.out.println(row0);
//            System.out.println(row1);
//            System.out.println(row2);
//            if (i !=size-1) { System.out.println(fieldSpacerMiddle);}
//        }
        System.out.println(fieldSpacerEnd);
    }
    public static void startGame(){
        if(inputHandler!=null)inputHandler.interrupt();
        inputHandler = new Thread(()->{
            try {
                final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String inp_cmd = "";
                while((inp_cmd = br.readLine()) != null){
                    Figures figure = null;
                    figure=figuresList.get(turn%figuresList.size());
                    int xDelta = 0;
                    int yDelta = 0;
                    for (char c : inp_cmd.toCharArray()) {
                        switch (c){
                            default:continue; //in case of invalid input
                            case 'n':xDelta=0;yDelta=1;break;
                            case 's':xDelta=0;yDelta=-1;break;
                            case 'o':xDelta=1;yDelta=0;break;
                            case 'w':xDelta=-1;yDelta=0;break;
                        }
                    }
                    figure.move(clamp(xDelta,-1,1),clamp(yDelta,-1,1));
                    printField();
                }
            }catch (Exception e){
                if(e instanceof InvalidMoveException){
                    System.out.println(((InvalidMoveException)e).getMessage());
                }
                e.printStackTrace();
            }
        },"async inp listner");
        inputHandler.start();

    }
    public static int clamp(int cValue, int min, int max) {
        return Math.max(min, Math.min(max, cValue));
    }
    //gives the player the points that the field he is standing on holds
    public void gainPoints(Figures figure) {
        figure.points.addition(GameField.getFieldPoints(figure.x, figure.y));
    }
}