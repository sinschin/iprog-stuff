package first;

public class Main {
    static Pitch GameField;
    public static int size = 8; //size of the playing field, standard 8
    public static void main(String[] args) {
        GameField = new Pitch();
        printField();
    }

    //Shows the playing field
    //Needs an option to show W/B and empty fields.
    public static void printField(){
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
}