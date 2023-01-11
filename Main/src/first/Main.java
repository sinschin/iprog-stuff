package first;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
/**
 * @author  Alex Riedel, Thomas Erbes, Tim Sommer
 * @version 1, 10.1.2023
 **/
public class Main {
    public static final int size = 8; //size of the playing field, standard 8
    static final String FigureNames ="WBACDEFGHIJKLMNOPQRSTUVXYZ";
    static final int playerCount = 2; //standard 2, max 24; //but not recommended since then you could box in a player and the game would be stuck since that player has to move but cant move
//    static Thread inputHandler = null; //cus the other persons didn't understand multithreading
    static final ArrayList<Figures> figuresList = new ArrayList<>();
    static Pitch GameField;
    static int turn = 0;

    public static void main(String[] args) {
        showNameSign();
        showMenu();
    }
    public static void showNameSign() {
        MyIO.writeln("░,,▄█▄,.░░░░░░░░░░░░░░╒█░░░░░░░░░░░░░░░░░░░░░░░░░░░░╤▄██▄▄╕░░░░░░");
        MyIO.writeln("░░░███ ░░░░░░░░░░░░  ▀███▀ ░░░░░.▄ ░░░░░▄▄░░░░░░░░░░░▐█▀█░░░░░░░░");
        MyIO.writeln("░░\" ░ `░░░░░░░░░░░░░╜  ▀░░░░░░░░░▀█▄░░,█▀ ░░░░░░░░░░ ░░ ░░░░░░░░░");
        MyIO.writeln("░░░░░░░░░░█▄▀▀▀█▄█▀▀▀█░░░▀▀▀▀▀█▌░░░ ██▄█ ░░░▀█▄░,█▀░░░░░░░░░░░░░░");
        MyIO.writeln("░░░░░░░░░░█░░░░██░░░░█▌░░░,▄▄░█▌░░░░▄██▄░░░░░ ███ ░░░░░░░╒░░░░░░░");
        MyIO.writeln("░░░░░░░░░▐█░░░░██░░░░█░░█▀  ░░█▌░░▄█▀  ██ ░░░▄█▀█▄░░░░╒▄▄██▄╤░░░░");
        MyIO.writeln("░░░░░░░░░▐█░░░░█▌░░░░█░░▀█▄▄▄░█▌,██ ░░░░▀█▄▄█▀░░ █▌░░░░░█▀█▌░░░░░");

    }
    public static void showMenu() {
        while (true){
            MyIO.writeln("What do you want to do?");
            MyIO.writeln("1 - Quick start");
            MyIO.writeln("2 - Show rules");
//        MyIO.writeln("3 - Show highscore"); //kinda useless since it always wins at 53 points
            MyIO.writeln("Q - Exit program");
            switch (MyIO.promptAndRead("Your selection: ").toLowerCase()) {
                case "1" -> {
                    MyIO.writeln("Here we go!");
                    startGame();
                }
                case "2" -> {
                    showRules();
                    continue;
                }
//                case "3": /*showHighscore();*/
//                    break;
                case "q" -> {
                    MyIO.write("Program will be terminated");
                    for (int i = 0; i < 3; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ignored) {
                        }
                        MyIO.write(".");
                    }
                    System.exit(2);
                }
                default -> {
                    MyIO.writeln("Unknown input");
                    continue;
                }
            }
            return;
        }
    }

    //
    public static void showRules() {
        MyIO.writeln("\nHere are the rules:");
        final String movementRules = "Movement: \nType the keys w, a, s, d (=North, East, South, West), to move in the direction.";
        final String movement2Rules = "You can move diagonal with the keys no or sw (=Northeast, Southwest).";
        final String victoryRules = "Victory:\nCollect the most points of the fractions. \nThe game is over when the last available square has been accessed,\nor a player has more than 53 points.";

        System.out.println(movementRules + "\n" + movement2Rules + "\n\n" + victoryRules);
        MyIO.promptAndRead("\nPress enter to continue\n\n");
    }

    //Shows the playing field
    public static void printField(){
        turn++;

//        int numerator, denominator;

        final String fieldSpacerStart  =      "┌───────┬───────┬───────┬───────┬───────┬───────┬───────┬───────┐";  //only need to add ┬─────── to increase the size of the playing field
        final String fieldSpacerMiddle =      "├───────┼───────┼───────┼───────┼───────┼───────┼───────┼───────┤";  //only need to add ┼─────── to increase the size of the playing field
        final String fieldSpacerEnd    =      "└───────┴───────┴───────┴───────┴───────┴───────┴───────┴───────┘";  //only need to add ┴─────── to increase the size of the playing field
        final String fieldNormal       =      "│  xxx  │  xxx  │  xxx  │  xxx  │  xxx  │  xxx  │  xxx  │  xxx  │";  //only need to add │  xxx   to increase the size of the playing field
//        final String fieldNormalSeparator=    "│  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │  ---  │";


        System.out.println(fieldSpacerStart);
        HashMap<Pos,Printable> allFields = new HashMap<>();
        for (int i = 0; i < Pitch.field.length; i++) {
            Printable[] allX = Pitch.field[i];
            for (int i1 = 0; i1 < allX.length; i1++) {
                allFields.put(new Pos(i,i1),allX[i1]);
            }
        }
        for(int y = 0;y< size  ;y++){
            String row0 = fieldNormal;
            String row1 = fieldNormal;
            String row2 = fieldNormal;
            for (int x = 0; x < size; x++) {
                String[] printOutSplit = allFields.get(new Pos(x,y)).print().split("\n");
                row0=row0.replaceFirst("xxx",printOutSplit[0]);
                row1=row1.replaceFirst("xxx",printOutSplit[1]);
//                row2=row2.replaceFirst("xxx",x+"/"+y); //just here for debugging
                row2=row2.replaceFirst("xxx",printOutSplit[2]);
            }
            System.out.println(row0);
            System.out.println(row1);
            System.out.println(row2);
            if (y !=size-1) { System.out.println(fieldSpacerMiddle);}
        }

//        for (int i = 0; i < size; i++) {
//            String row0 = fieldNormal;
//            String row1 = fieldNormalSeparator;
//            String row2 = fieldNormal;
////            DecimalFormat decimalFormat = new DecimalFormat("###");
//            //replaces the row with the numerators
//            for (int j = 0; j < size; j++) {
//                numerator =  GameField.getNumerator(i,j);
//                row0=row0.replace(j+""+j+""+j,String.format("%03d", numerator));
//            }
//
//            //replaces the row with the denominators
//            for (int j = 0; j < size; j++) {
//                denominator =  GameField.getDenominator(i,j);
//                row2=row2.replace(j+""+j+""+j,String.format("%03d", denominator));
//            }
//            System.out.println(row0);
//            System.out.println(row1);
//            System.out.println(row2);
//            if (i !=size-1) { System.out.println(fieldSpacerMiddle);}
//        }
        System.out.println(fieldSpacerEnd);
    }
    private static Boolean nothingLeft(){
        for (int i = 0; i < Pitch.field.length; i++) {
            Printable[] allX = Pitch.field[i];
            for (Printable allX1 : allX) {
                if (allX1.isFraction()) {
                    if (((Fraction) allX1).isValid()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static void startGame(){
        try {
            GameField = new Pitch();
            figuresList.clear();
            for (int i = 0; i < playerCount; i++) {
                figuresList.add(Figures.genFigure());
            }
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inp_cmd;
            Figures figure;
            printField();
            turn=0;
            figure=figuresList.get(0);
            System.out.print(figure.name+ "'s turn ");
            switch (figure.name) {
                case "W" -> MyIO.write("| Use W, D, S, A or WD ");
                case "B" -> MyIO.write("| Use W, D, S, A or SA ");
                default -> MyIO.write("| Use W, D, S, A"); // rest of the figures don't have a special move
            }
            MyIO.writeln("+ enter | Type \"quit\" to exit");

            while ((inp_cmd = br.readLine()) != null){
                if(inp_cmd.equals(""))continue;
                int xDelta = 0,yDelta = 0;
                for (char c : inp_cmd.toCharArray()) {
                    switch (c){
                        default:continue; //in case of invalid input
//                        case 'n':xDelta+= 0;yDelta+=-1;break;
//                        case 's':xDelta+= 0;yDelta+= 1;break;
//                        case 'o':xDelta+= 1;yDelta+= 0;break;
//                        case 'w':xDelta+=-1;yDelta+= 0;break;
                        case 'w':xDelta+= 0;yDelta+=-1;break;
                        case 's':xDelta+= 0;yDelta+= 1;break;
                        case 'd':xDelta+= 1;yDelta+= 0;break;
                        case 'a':xDelta+=-1;yDelta+= 0;break;
                    }
                }
                try {
                    if(inp_cmd.equalsIgnoreCase("quit")){
                        System.exit(2);
                        break;
                    }
                    if(xDelta==0&&yDelta==0)throw new InvalidMoveException("Unknown input");
                    figure.move(clamp(xDelta,-1,1),clamp(yDelta,-1,1));
                    if(nothingLeft()){
                        System.out.println("all fields are empty!");
                        announceWinnerAndEndGame(null); // for performance reasons since its kinda useless to go through the list again (we do that again in this function)
                        break;
                    }
                    if (figure.points.floatValue() > 53.0f) {
                        announceWinnerAndEndGame(figure);
                        break;
                    } //check if points are reached
                    printField();
                    figure=figuresList.get(turn%figuresList.size());
                    ArrayList<Figures> players =figuresList.stream().sorted(Comparator.comparingDouble(x->x.points.doubleValue())).collect(Collectors.toCollection(ArrayList::new));
                    for (int i = 0; i < players.size(); i++) { //points list
                        System.out.println(players.size()-i+". "+players.get(i).name+" "+players.get(i).points.floatValue());
                    }
                    switch (figure.name) {
                        case "W" -> MyIO.write("| Use W, D, S, A or WD ");
                        case "B" -> MyIO.write("| Use W, D, S, A or SA ");
                        default -> MyIO.write("| Use W, D, S, A"); // rest of the figures don't have a special move
                    }
                    MyIO.writeln("+ enter | Type \"quit\" to exit");
                    System.out.println(figure.name+ "'s turn with "+figure.points.floatValue());

                }catch (InvalidMoveException ex){
                    System.out.println(ex.getMessage());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int clamp(int cValue, int min, int max) {
        return Math.max(min, Math.min(max, cValue));
    }
    public static void announceWinnerAndEndGame(Figures winner) {
        MyIO.writeln("We have a Winner!");
        ArrayList<Figures> sortedFigureList = figuresList.stream().sorted(Comparator.comparingDouble(x->x.points.doubleValue())).collect(Collectors.toCollection(ArrayList::new));
        if(winner==null)winner=sortedFigureList.get(sortedFigureList.size()-1);
        Figures finalWinner = winner;
        System.out.println(finalWinner.name + " wins with " + finalWinner.points.floatValue() + " Congratulations!");
        sortedFigureList.forEach(x->{
            if (!x.equals(finalWinner)) {
                System.out.println(x.name + " has " + x.points.floatValue());
            }
        });
        MyIO.writeln("");
        while (true){
            switch (MyIO.promptAndRead("Enter \"a\" to play again, \"m\" for the menu or \"q\" to quit: ").toLowerCase()) {
                case "a" -> startGame();
                case "m" -> showMenu();
                case "q" -> {
                    MyIO.writeln("Program will be terminated");
                    System.exit(2);
                }
                default -> {
                    MyIO.writeln("Unknown input");
                    continue;
                }
            }
            return;
        }
    }
    //gives the player the points that the field he is standing on holds
    //public static void gainPoints(Figures figure) {
    //    figure.points.addition(GameField.getFieldPoints(figure.x, figure.y));
    //}
}