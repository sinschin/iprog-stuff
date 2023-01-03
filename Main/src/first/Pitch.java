package first;

import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;

public class Pitch {

    //Variables
    static Fraction[][] field; //represents the playing field
    int size; //indicates the size of the playing field

    //Methods
    public Pitch(int size) {

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
        if (numerator < 9 || denominator < 9) {
            return createFraction();
        }
        else {
            return new Fraction(numerator, denominator);
        }
    }

    //find the greatest common divisor
    public static int gcd(int a, int b) {
        return (b == 0) ? a :gcd(b, a%b);
    }
        //showPitch
        //setPlayers
}
