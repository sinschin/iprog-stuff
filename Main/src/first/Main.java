package first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Main {
    static String FigureNames ="WBACDEFGHIJKLMNOPQRSTUVXYZ";
    static Pitch GameField;
    static Thread inputHandler = null;
    static ArrayList<Figures> figuresList = new ArrayList<>();
    static int turn = 0;
    public static int size = 8; //size of the playing field, standard 8

    public static void main(String[] args) {
        GameField = new Pitch();
        for (int i = 0; i < 2; i++) {
            figuresList.add(Figures.genFigure());
        }
        startGame();
    }

    //Shows the playing field
    //TODO an option to show W/B and empty fields (currently the would be shown as 0/1).
    public static void printField(){
        turn++;

//        int numerator, denominator;

        final String fieldSpacerStart  =      "┌───────┬───────┬───────┬───────┬───────┬───────┬───────┬───────┐";  //only need to add ┬─────── to increase the size of the playing field
        final String fieldSpacerMiddle =      "├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤";  //only need to add ┼─────── to increase the size of the playing field
        final String fieldSpacerEnd    =      "└───────┴───────┴───────┴───────┴───────┴───────┴───────┴───────┘";  //only need to add ┴─────── to increase the size of the playing field
        final String fieldNormal       =      "│  xxx  │  xxx  │  xxx  │  xxx  │  xxx  │  xxx  │  xxx  │  xxx  │";  //only need to add │  xxx   to increase the size of the playing field
//        final String fieldNormalSeparator=    "│  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │";


        System.out.println(fieldSpacerStart);
        HashMap<Pos,Printable> allFields = new HashMap<>();
        for (int i = 0; i < Pitch.field.length; i++) {
            Printable[] allX = Pitch.field[i];
            for (int i1 = 0; i1 < allX.length; i1++) {
                allFields.put(new Pos(i,i1),allX[i1]);
            }
        }
        for(int y = 0;y< size  ;y++){
            String row0 = fieldNormal;
            String row1 = fieldNormal;
            String row2 = fieldNormal;
            for (int x = 0; x < size; x++) {
                String[] printOutSplit = allFields.get(new Pos(x,y)).print().split("\n");
                row0=row0.replaceFirst("xxx",printOutSplit[0]);
                row1=row1.replaceFirst("xxx",printOutSplit[1]);
//                row2=row2.replaceFirst("xxx",x+"/"+y); //just here for debugging
                row2=row2.replaceFirst("xxx",printOutSplit[2]);
            }
            System.out.println(row0);
            System.out.println(row1);
            System.out.println(row2);
            if (y !=size-1) { System.out.println(fieldSpacerMiddle);}
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
    private static Boolean nothingLeft(){
        for (int i = 0; i < Pitch.field.length; i++) {
            Printable[] allX = Pitch.field[i];
            for (int i1 = 0; i1 < allX.length; i1++) {
                Printable allX1 = allX[i1];
                if(allX1.isFraction()){
                    if(((Fraction)allX1).isValid()){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void startGame(){
        if(inputHandler!=null)inputHandler.interrupt();
        inputHandler = new Thread(()->{
            try {
                final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String inp_cmd = "";
                Figures figure = null;
                printField();
                figure=figuresList.get(turn%figuresList.size());
                System.out.println(figure.name+ "'s turn");
                while ((inp_cmd = br.readLine()) != null){
                    if(inp_cmd.equals(""))continue;
                    int xDelta = 0;
                    int yDelta = 0;
                    for (char c : inp_cmd.toCharArray()) {
                        switch (c){
                            default:continue; //in case of invalid input
//                            case 'n':xDelta+= 0;yDelta+=-1;break;
//                            case 's':xDelta+= 0;yDelta+= 1;break;
//                            case 'o':xDelta+= 1;yDelta+= 0;break;
//                            case 'w':xDelta+=-1;yDelta+= 0;break;
                            case 'w':xDelta+= 0;yDelta+=-1;break;
                            case 's':xDelta+= 0;yDelta+= 1;break;
                            case 'd':xDelta+= 1;yDelta+= 0;break;
                            case 'a':xDelta+=-1;yDelta+= 0;break;
                        }
                    }
                    try {
                        if(xDelta==0&&yDelta==0)throw new InvalidMoveException("Unknown input");
                        figure.move(clamp(xDelta,-1,1),clamp(yDelta,-1,1));
                        if(nothingLeft()){
                            System.out.println("and its over");
                            ArrayList<Figures> players =figuresList.stream().sorted(Comparator.comparingDouble(x->x.points.doubleValue())).collect(Collectors.toCollection(ArrayList::new));
                            for (int i = 0; i < players.size(); i++) {
                                System.out.println(i+". "+players.get(i).name+" "+players.get(i).points.floatValue());
                            }
                            System.exit(2);
                            break;
                        }
                        printField();
                        figure=figuresList.get(turn%figuresList.size());
                        ArrayList<Figures> players =figuresList.stream().sorted(Comparator.comparingDouble(x->x.points.doubleValue())).collect(Collectors.toCollection(ArrayList::new));
                        for (int i = 0; i < players.size(); i++) {
                            System.out.println(i+1+". "+players.get(i).name+" "+players.get(i).points.floatValue());
                        }
                        System.out.println(figure.name+ "'s turn with "+figure.points.floatValue());
                    }catch (InvalidMoveException ex){
                        System.out.println(ex.getMessage());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"async inp listner");
        inputHandler.start();

    }
    public static int clamp(int cValue, int min, int max) {
        return Math.max(min, Math.min(max, cValue));
    }
    //gives the player the points that the field he is standing on holds
    public static void gainPoints(Figures figure) {
        figure.points.addition(GameField.getFieldPoints(figure.x, figure.y));
    }
}