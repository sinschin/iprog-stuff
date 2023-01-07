package first;

import java.util.Objects;

/**
 * @author Tim Sommer, Thomas Erbes
 * @version 3, 3.1.2023
 **/
@SuppressWarnings("unused")
class Fraction extends Number implements Printable {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new RuntimeException(new IllegalArgumentException("denominator is zero"));
        }
        this.numerator = Math.abs(numerator);
        this.denominator = Math.abs(denominator);
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public byte byteValue() {
        return (byte) this.doubleValue();
    }

    public double doubleValue() {
        return ((double) numerator) / ((double) denominator);
    }

    public float floatValue() {
        return (float) this.doubleValue();
    }

    public int intValue() {
        return (int) this.doubleValue();
    }

    public long longValue() {
        return (long) this.doubleValue();
    }

    public short shortValue() {
        return (short) this.doubleValue();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    public int compareTo(Fraction frac) {
        long t = (long) getNumerator() * frac.getDenominator();
        long f = (long) frac.getNumerator() * getDenominator();
        int result = 0;
        if (t > f) {
            result = 1;
        } else if (f > t) {
            result = -1;
        }
        return result;
    }

    public Fraction addition(Fraction otherFraction) {
        int abNumerator, abDenominator, abGCD;

        //bring to a mutual common denominator
        abNumerator = numerator * otherFraction.denominator + otherFraction.numerator * denominator;
        abDenominator = denominator * otherFraction.denominator;

        //shorten
        abGCD = getGCD(abNumerator, abDenominator);
        abNumerator = abNumerator / abGCD;
        abDenominator = abDenominator / abGCD;

        return new Fraction(abNumerator, abDenominator);
    }
    private static int getGCD(int a, int b) {
        return (b == 0) ? a :getGCD(b, a%b);
    }


    @Override
    public String print() {
        String print_line = "000\n---\n111";
        return print_line.replace("000",numerator+"").replace("111",denominator+"");
    }

    @Override
    public Boolean isFraction() {
        return true;
    }
}
