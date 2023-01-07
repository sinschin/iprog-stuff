package first;

import java.math.BigInteger;
import java.text.DecimalFormat;
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
        BigInteger abNumerator, abDenominator, abGCD;
        BigInteger num = BigInteger.valueOf(numerator);
        BigInteger den = BigInteger.valueOf(denominator);
        BigInteger othernum = BigInteger.valueOf(otherFraction.numerator);
        BigInteger otherden = BigInteger.valueOf(otherFraction.denominator);

        //bring to a mutual common denominator
        abNumerator=num.multiply(otherden).add(othernum.multiply(den));
//        abNumerator = numerator * otherFraction.denominator + otherFraction.numerator * denominator;
        abDenominator=den.multiply(otherden);
//        abDenominator = denominator * otherFraction.denominator;

        //shorten
        abGCD = getGCD(abNumerator, abDenominator);
        abNumerator = abNumerator.divide(abGCD);
        abDenominator = abDenominator.divide(abGCD);

        return new Fraction(abNumerator.intValueExact(), abDenominator.intValueExact());
    }
    private static int getGCD(int a, int b) {
        return (b == 0) ? a :getGCD(b, a%b);
    }
    private static BigInteger getGCD(BigInteger a, BigInteger b) {
        return (Objects.equals(b, BigInteger.ZERO)) ? a :getGCD(b, a.mod(b));
    }
    protected Boolean isValid(){
        return numerator != 0 || denominator != 1;
    }


    @Override
    public String print() {
        if(numerator==0&&denominator==1)return "   \n   \n   ";
        String print_line = "000\n---\n111";
//        print_line= print_line.replace("---",new DecimalFormat("#,#").format(floatValue())); //just for debugging
        return print_line.replace("000",String.format("%03d", numerator)).replace("111",String.format("%03d", denominator));
    }

    @Override
    public Boolean isFraction() {
        return true;
    }
}
