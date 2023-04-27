package first;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author  Alex Riedel, Thomas Erbes, Tim Sommer
 * @version 3, 10.1.2023
 **/
@SuppressWarnings("unused")
class Fraction extends Number implements Printable {
    private final BigInteger numerator;
    private final BigInteger denominator;

    public Fraction(BigInteger numerator, BigInteger denominator) {
        if (Objects.equals(denominator, BigInteger.ZERO)) {
            throw new RuntimeException(new IllegalArgumentException("denominator is zero"));
        }
        this.numerator = numerator.abs();
        this.denominator = denominator.abs();
    }

    public Fraction(BigInteger numerator) {
        this.numerator = numerator;
        this.denominator = BigInteger.ONE;
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = BigInteger.valueOf(numerator);
        this.denominator = BigInteger.valueOf(denominator);
    }

    public BigInteger getNumerator() {
        return this.numerator;
    }

    public BigInteger getDenominator() {
        return this.denominator;
    }

    public byte byteValue() {
        return (byte) this.doubleValue();
    }

    public double doubleValue() {
        return (numerator.doubleValue()) / (denominator.doubleValue());
    }

    public float floatValue() {
        return (float) this.doubleValue();
    }

    public BigInteger BigIntegerValue() {
        return numerator.divide(denominator);
    }

    @Override
    public int intValue() {
        return (int)doubleValue();
    }

    public long longValue() {
        return (long) this.doubleValue();
    }

    public short shortValue() {
        return (short) this.doubleValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Fraction)) {
            return false;
        }

        Fraction other = (Fraction) o;

        BigInteger thisNum = numerator.multiply(other.denominator);
        BigInteger otherNum = other.numerator.multiply(denominator);

        return thisNum.equals(otherNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }



    public Fraction addition(Fraction otherFraction) {
        BigInteger abNumerator, abDenominator, abGCD;
        BigInteger den = denominator;
        BigInteger otherDen = otherFraction.denominator;

        //bring to a mutual common denominator
        abNumerator= numerator.multiply(otherDen).add(otherFraction.numerator.multiply(den));
//        abNumerator = numerator * otherFraction.denominator + otherFraction.numerator * denominator;
        abDenominator=den.multiply(otherDen);
//        abDenominator = denominator * otherFraction.denominator;

        //shorten
        abGCD = getGCD(abNumerator, abDenominator);
        abNumerator = abNumerator.divide(abGCD);
        abDenominator = abDenominator.divide(abGCD);

        return new Fraction(abNumerator, abDenominator);
    }
    private static BigInteger getGCD(BigInteger a, BigInteger b) {
        return (Objects.equals(b, BigInteger.ZERO)) ? a :getGCD(b, a.mod(b));
    }
    protected Boolean isValid(){
        return !Objects.equals(numerator, BigInteger.ZERO) || !Objects.equals(denominator, BigInteger.ONE);
    }


    @Override
    public String print() {
        if(Objects.equals(numerator, BigInteger.ZERO) && Objects.equals(denominator, BigInteger.ONE))return "   \n   \n   ";
        String print_line = "000\n---\n111";
//        print_line= print_line.replace("---",new DecimalFormat("#,#").format(floatValue())); //just for debugging
        return print_line.replace("000",String.format("%03d", numerator)).replace("111",String.format("%03d", denominator));
    }

    @Override
    public Boolean isFraction() {
        return true;
    }
}
