/**
 * Title: GameItem.java Date: 2020-01-30
 *
 * @author Ben Keddie 3499200 A class for constructing GameItem objects with various
 * parameters. Assuming jdk 11.0 or later Change to directory containing
 * source code. Compile and run: see Game.java
 */
package Components;

public class GameItem {
    private int value;
    private String description;
    private String name;


    public GameItem(String name, int value, String description) { // constructor for game item
        this.name = name;
        this.description = description;
        this.value = value;
    }


    public String getName() { // getter functions
        return name;
    }


    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
}
