package first;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static Pitch GameField;
    static Thread inputHandler = null;
    static Figures whiteFigure = null;
    static Figures blackFigure = null;
    static int turn = 0;

    public static void main(String[] args) {
        GameField = new Pitch(8);
        startGame();
        printField();
    }
    //Needs an option to show W/B and empty fields.
    public static void printField(){
        turn++;
        String fieldSpacer = "|-------------------------------------|";
        String fieldSpacingLine = "|                                     |";
        String fieldNormalLine = "| 000 111 222 333 444 555 666 777 888 |";
        String[] groups = new String[8];

        int numerator, denominator;

        String fieldSpacerStart  =      "┌───────┬───────┬───────┬───────┬───────┬───────┬───────┬───────┐";
        String fieldSpacerMiddle =      "├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤";
        String fieldSpacerEnd    =      "└───────┴───────┴───────┴───────┴───────┴───────┴───────┴───────┘";


        System.out.println(fieldSpacerStart);
        for (int i = 0; i < GameField.getSize(); i++) {

            //create row with numerator
            System.out.print("│  ");
            for (int j = 0; j < GameField.getSize(); j++) {
                numerator =  GameField.getNumerator(i,j);
                if(numerator < 100) { System.out.print(" " + numerator + "  │  ");}
                else {System.out.print(numerator + "  │  ");}
            }
            System.out.println();

            //create row with fraction separator
            System.out.print("│  ");
            for (int j = 0; j < GameField.getSize(); j++) {
                System.out.print("———  │  ");
            }
            System.out.println();

            //create row with denominator
            System.out.print("│  ");
            for (int j = 0; j < GameField.getSize(); j++) {
                denominator =  GameField.getDenominator(i,j);
                if(denominator < 100) { System.out.print(" " + denominator + "  │  ");}
                else {System.out.print(denominator + "  │  ");}
            }
            System.out.println();
            if (i < GameField.getSize() - 1) { System.out.println(fieldSpacerMiddle);}
            else { System.out.println(fieldSpacerEnd);}
        }
    }
    public static void startGame(){
        if(inputHandler!=null)inputHandler.interrupt();
        inputHandler = new Thread(()->{
            try {
                final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String inp_cmd = "";
                while((inp_cmd = br.readLine()) != null){
                    Figures figure = null;
                    figure=turn%2==0 ? whiteFigure : blackFigure;
                    switch (inp_cmd){
                        default:return;
                        case "n":figure.move(0,1);return;
                        case "s":figure.move(0,-1);return;
                        case "o":figure.move(1,0);return;
                        case "w":figure.move(-1,0);return;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        },"async inp listner");
        inputHandler.start();

    }
}