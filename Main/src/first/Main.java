package first;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    static Pitch GameField;
    static Thread inputHandler = null;
    static Figures whiteFigure = null;
    static Figures blackFigure = null;
    static int turn = 0;
    public static int size = 8; //size of the playing field, standard 8

    public static void main(String[] args) {
        GameField = new Pitch();
        whiteFigure=new Figures() {
            @Override
            public String getName() {
                return "W";
            }
        };
        blackFigure=new Figures() {
            @Override
            public String getName() {
                return "W";
            }
        };
        startGame();
        printField();
    }

    //Shows the playing field
    //Needs an option to show W/B and empty fields.
    public static void printField(){
        turn++;

        int numerator, denominator;

        final String fieldSpacerStart  =      "┌───────┬───────┬───────┬───────┬───────┬───────┬───────┬───────┐";
        final String fieldSpacerMiddle =      "├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤";
        final String fieldSpacerEnd    =      "└───────┴───────┴───────┴───────┴───────┴───────┴───────┴───────┘";
        final String fieldNormal       =      "│  000  │  111  │  222  │  333  │  444  │  555  │  666  │  777  │";
        final String fieldNormalSeparator=    "│  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │";


        System.out.println(fieldSpacerStart);
        for (int i = 0; i < size; i++) {
            String row0 = fieldNormal;
            String row1 = fieldNormalSeparator;
            String row2 = fieldNormal;
//            DecimalFormat decimalFormat = new DecimalFormat("###");
            //replaces the row with the numerators
            for (int j = 0; j < size; j++) {
                numerator =  GameField.getNumerator(i,j);
                row0=row0.replace(j+""+j+""+j,String.format("%03d", numerator));
//                System.out.print(numerator + "  │  ");
            }

            //replaces the row with the denominators
            for (int j = 0; j < size; j++) {
                denominator =  GameField.getDenominator(i,j);
                row2=row2.replace(j+""+j+""+j,String.format("%03d", denominator));
            }
            System.out.println(row0);
            System.out.println(row1);
            System.out.println(row2);
            if (i !=size-1) { System.out.println(fieldSpacerMiddle);}
        }
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
                    figure=turn%2==0 ? whiteFigure : blackFigure; //fairly simple change to a switch case if u wanted more players
                    for (char c : inp_cmd.toCharArray()) {
                        switch (c){
                            default:continue; //in case of invalid input
                            case 'n':figure.move(0,1);break;
                            case 's':figure.move(0,-1);break;
                            case 'o':figure.move(1,0);break;
                            case 'w':figure.move(-1,0);break;
                        }
                    }

                    printField();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"async inp listner");
        inputHandler.start();

    }
}