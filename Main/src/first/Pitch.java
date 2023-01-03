package first;

import java.util.Random;

public class Pitch {

    //Variables
    static Fraction[][] field; //represents the playing field
    int size; //indicates the size of the playing field

    //Methods
    public Pitch(int size) {
        this.size = size;
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
    private static int gcd(int a, int b) {
        return (b == 0) ? a :gcd(b, a%b);
    }

    public int getSize() {
        return size;
    }

    public static int getNumerator(int numerator, int denominator) {
        return field[numerator][denominator].getNumerator();
    }
    public static int getDenominator(int numerator, int denominator) {
        return field[numerator][denominator].getDenominator();
    }

    public static void main(String[] args) {

    }
}


