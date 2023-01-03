package first;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static Pitch GameField;
    public static void main(String[] args) {
        GameField = new Pitch(8);
        printField();
    }
    //Needs an option to show W/B and empty fields.
    public static void printField(){
        //String fieldSpacer =      "|-------------------------------------------------|";
        String fieldSpacer =      "##################################################################";
        //String fieldSpacingLine = "|                                                 |";
        //String fieldNormalLine =  "|  000   111   222   333   444   555   666   777  |";
        int numerator, denominator;

        System.out.println(fieldSpacer);
        for (int i = 0; i < GameField.getSize(); i++) {

            //create row with numerator
            System.out.print("#   ");
            for (int j = 0; j < GameField.getSize(); j++) {
                numerator =  GameField.getNumerator(i,j);
                if(numerator < 100) { System.out.print(" " + numerator + "  #  ");}
                else {System.out.print(numerator + "  #  ");}
            }
            System.out.println("");

            //create row with fraction separator
            System.out.print("#   ");
            for (int j = 0; j < GameField.getSize(); j++) {
                System.out.print("———  #  ");
            }
            System.out.println("");

            //create row with denominator
            System.out.print("#   ");
            for (int j = 0; j < GameField.getSize(); j++) {
                denominator =  GameField.getDenominator(i,j);
                if(denominator < 100) { System.out.print(" " + denominator + "  #  ");}
                else {System.out.print(denominator + "  #  ");}
            }
            System.out.println("");
            System.out.println(fieldSpacer);
        }
    }
}