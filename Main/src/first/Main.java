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
    public static int size = 8; //size of the playing field, standard 8

    public static void main(String[] args) {
        //Start - Aufgaben Regeln
        //Anlegen Spielfiguren
        //Anlegen Feld
        GameField = new Pitch();
        //Ausgabe Feld
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
        //Aufforderung Eingabe
        //Frage wer anfängt - W oder B
        //Eingabe W/B
        //Frage wohin Spieler ziehen möchte (N,O,S,W oder NW(W) oder SW(B)
        //Eingabe Richtung
        //bewegt sich, wenn möglich, fehler und wiederholung, wenn nicht möglich
        //Einsammeln Punkte
        //Prüfen, ob gewonnen
        //
    }

    //Shows the playing field
    //Needs an option to show W/B and empty fields.
    public static void printField(){
        turn++;

        int numerator, denominator;

        String fieldSpacerStart  =      "┌───────┬───────┬───────┬───────┬───────┬───────┬───────┬───────┐";
        String fieldSpacerMiddle =      "├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤";
        String fieldSpacerEnd    =      "└───────┴───────┴───────┴───────┴───────┴───────┴───────┴───────┘";


        System.out.println(fieldSpacerStart);
        for (int i = 0; i < size; i++) {

            //create row with numerator
            System.out.print("│  ");
            for (int j = 0; j < size; j++) {
                numerator =  GameField.getNumerator(i,j);
                if(numerator < 100) { System.out.print(" " + numerator + "  │  ");}
                else {System.out.print(numerator + "  │  ");}
            }
            System.out.println();

            //create row with fraction separator
            System.out.print("│  ");
            for (int j = 0; j < size; j++) {
                System.out.print("———  │  ");
            }
            System.out.println();

            //create row with denominator
            System.out.print("│  ");
            for (int j = 0; j < size; j++) {
                denominator =  GameField.getDenominator(i,j);
                if(denominator < 100) { System.out.print(" " + denominator + "  │  ");}
                else {System.out.print(denominator + "  │  ");}
            }
            System.out.println();
            if (i < size - 1) { System.out.println(fieldSpacerMiddle);}
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
                    figure=turn%2==0 ? whiteFigure : blackFigure; //fairly simple change to a switch case if u wanted more players
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