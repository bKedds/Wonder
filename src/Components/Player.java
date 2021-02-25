/**
 * Title: Player.java Date: 2020-01-30
 *
 * @author Ben Keddie 3499200
 * A class for constructing player objects with various
 * parameters. Assuming jdk 11.0 or later Change to directory containing
 * source code. Compile and run: see Game.java
 */
package Components;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Player extends Inventory {
    private String playerName = "";
    private String name1 = "Alice";
    private String name2 = "Alister";
    private int health = 100;
    private Location location;
    private int points = 0;


    public int getPoints() {
        return points;
    }

    public String speak(){
        String speak = "You: Hello, my name is " + playerName + ".";
        return speak;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Player(Location location, String name, ItemList playerItems) throws FileNotFoundException {
        super(name,playerItems);
        this.readFile("instructions.txt");
        this.setName();
        this.greeting();
        this.readFile("start.txt");
        this.location = location;
        this.health = 100;
        this.points = points;
    }


    public Location getLocation() {
        return location;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String setName() {
        int i = 0;
        while (i == 0) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nPlease choose a gender, M or F");
            String gender = scanner.nextLine().toUpperCase();
            switch (gender) {
                case "F":
                case "FEMALE":
                    playerName = name1;
                    i = 1;
                    break;
                case "M":
                case "MALE":
                    playerName = name2;
                    i = 1;
                    break;

                default:
                    System.out.println("Invalid response, ");

            }

        }
        return playerName;
    }
    public void readFile (String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }

    }


    public void greeting(){
        System.out.println("\nHello, and welcome to Wonderland.\nFor the purposes of this game, we will call you "
                + playerName + ".\nYour current HP is " + health + ".\n");
    }
}
