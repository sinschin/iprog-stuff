package first;

import java.util.Random;

import static first.Main.size;

public class Pitch {

    //Variables
    static Printable[][] field; //represents the playing field

    //Methods
    public Pitch() {
        field = new Fraction[size][size];
        generatePitch(size);
    }
        //GeneratePitch
    private static void generatePitch(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = createFraction();
            }
        }
    }
    //creates suitable fractions according to the assignment
    private static Fraction createFraction() {
        int numerator, denominator, gcdivisor;
        Random random = new Random();
        //FIXME remove this
        //create random number between 10 and 999
        numerator = 10 + random.nextInt(989);

        //with check that numerator is bigger (assignment)
        do {
            denominator = 10 + random.nextInt(989);
        } while (numerator < denominator);


        //truncate fraction
        gcdivisor = gcd(numerator, denominator);
        numerator = numerator / gcdivisor;
        denominator = denominator / gcdivisor;

        //check that numbers are at least in double digits
        if (numerator < 10 || denominator < 10) {
            return createFraction();
        }
        else {
            return new Fraction(numerator, denominator);
        }
    }

    //find the greatest common divisor
    private static int gcd(int a, int b) {
        return (b == 0) ? a :gcd(b, a%b);
    }

    public int getNumerator(int x, int y) {
        Printable printable = field[x][y];
        if(printable.isFraction()){
            return ((Fraction)field[x][y]).getNumerator();
        }
        return 0;
    }
    public int getDenominator(int x, int y) {
        Printable printable = field[x][y];
        if(printable.isFraction()){
            return ((Fraction)field[x][y]).getDenominator();
        }
        return 0;
    }
    public Fraction getFieldPoints(int x, int y) {
        Printable printable = field[x][y];
        if(printable.isFraction()){
            Fraction points = (Fraction) field[x][y];
            field[x][y] = new Fraction(0); //empty field so that it can't be claimed again
            return points;
        }
        return new Fraction(0,0);
    }
    public static void main(String[] args) {

    }
}


