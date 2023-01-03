package first;

public interface Printable {
    int posX=Integer.MAX_VALUE;
    int posY=Integer.MAX_VALUE;

    short printSizeX();

    short printSizeY();

    String print();
}
