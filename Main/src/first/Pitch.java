package first;

import java.math.BigInteger;
import java.util.Random;

import static first.Main.size;
/**
 * @author  Alex Riedel, Thomas Erbes, Tim Sommer
 * @version 1, 10.1.2023
 **/
public class Pitch {

    //Variables
    protected static Printable[][] field; //represents the playing field

    //Methods
    public Pitch() {
        field = new Printable[size][size];
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
        int numerator, denominator, gcDivisor;
        Random random = new Random();
        //create random number between 10 and 999
        numerator = 10 + random.nextInt(989);

        //with check that numerator is bigger (assignment)
        denominator = 10 + random.nextInt(numerator - 9);
        //with check that numerator is bigger (assignment)


        //truncate fraction
        gcDivisor = gcd(numerator, denominator);
        numerator = numerator / gcDivisor;
        denominator = denominator / gcDivisor;

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

    public BigInteger getNumerator(int x, int y) {
        Printable printable = field[x][y];
        if(printable.isFraction()){
            return ((Fraction)field[x][y]).getNumerator();
        }
        return BigInteger.ZERO;
    }
    public BigInteger getDenominator(int x, int y) {
        Printable printable = field[x][y];
        if(printable.isFraction()){
            return ((Fraction)field[x][y]).getDenominator();
        }
        return BigInteger.ZERO;
    }
    public Fraction getFieldPoints(int x, int y) {
        Printable printable = field[x][y];
        if(printable.isFraction()){
            Fraction points = (Fraction) field[x][y];
            field[x][y] = new Fraction(BigInteger.ZERO); //empty field so that it can't be claimed again
            return points;
        }
        return new Fraction(0,1);
    }
}


