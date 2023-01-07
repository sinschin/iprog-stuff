package first;

import java.math.BigDecimal;
import java.math.BigInteger;
/**
* @version 4, 13.12.2022
* @author Tim Sommer, Thomas Erbes
**/
import java.util.Scanner;

public class MyIO {
    
    private MyIO() {}

    static private Scanner sc = new Scanner(System.in);

    //Einlesen von unterschiedlichen Datentypen
    //String
    public static String promptAndRead (String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
    //byte
    public static byte readByte (String prompt) {
        System.out.print(prompt);
        return Byte.parseByte(sc.nextLine().trim());
    }
    //short
    public static short readShort (String prompt) {
        System.out.print(prompt);
        return Short.parseShort(sc.nextLine().trim());
    }
    //int
    public static int readInt (String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(sc.nextLine().trim());
    }
    //bigInteger
    public static BigInteger readBigInt (String prompt) {
        System.out.print(prompt);
        return new BigInteger(sc.nextLine().trim());
    }
    //float
    public static float readFloat (String prompt) {
        System.out.print(prompt);
        return Float.parseFloat(sc.nextLine().trim());
    }
    //double
    public static double readDouble (String prompt){
        System.out.print(prompt);
        return Double.parseDouble(sc.nextLine().trim());
    }
    //long
    public static long readLong (String prompt) {
        System.out.print(prompt);
        return Long.parseLong(sc.nextLine().trim());
    }
    //bigDecimal
    public static BigDecimal readBigDecimal (String prompt) {
        System.out.print(prompt);
        return new BigDecimal(sc.nextLine().trim());
    }
    //Fractions
    public static Fraction readFraction(String prompt) {
        while(true) {
            try {      
                System.out.print(prompt);   
                String input = sc.nextLine();   
                String[] parts = input.split("/");
                int zaehler = Integer.parseInt(parts[0]);
                int nenner = Integer.parseInt(parts[1]);
                return new Fraction(zaehler, nenner);
            } catch (Exception e) {
            System.out.println("Fehler");
            }
        }
    }
    //Ausgaben
    public static void write (String s) {
        System.out.print(s);
    }
    public static void writeln (String s) {
        System.out.println(s);
    }
    //Eigene Ergänzung
    public static void writeInt (int i) {
        System.out.print(i);
    }
    //Eigene Ergänzung
    public static void writelnInt (int i) {
        System.out.println(i);
    }
    public static void writelnDouble (double i) {
        System.out.println(i);
    }
}
