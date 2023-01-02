package first;

/**
 * @author Tim Sommer, Thomas Erbes
 * @version 2, 13.12.2022
 **/
@SuppressWarnings("unused")
class Fraction extends Number {
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

    public boolean equals(Fraction frac) {
        return this.compareTo(frac) == 0;
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
}
