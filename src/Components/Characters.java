/**
 * Title: Location.java Date: 2020-01-30
 *
 * @author Ben Keddie 3499200
 * A class for constructing character objects with various
 * parameters. Assuming jdk 11.0 or later Change to directory containing
 * source code. Compile and run: see Game.java
 */
package Components;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Characters extends Inventory{
    private String name;
    private String filename;
    private ItemList charList;
    private int interactions;

    public ArrayList<GameItem> getCharList() {
        return charList;
    }

    public int getInteractions() { // getter and setter methods for number of character interactions
        return interactions;
    }

    public void setInteractions(int interactions) {
        this.interactions = interactions;
    }

    public Characters(String name, String filename, ItemList charList, int interactions){ // constructor for characters
        super(name,charList);
        this.filename = filename;
        this.interactions = interactions;

    }

    public String getFilename() {
        return filename;
    }


    public void readSpeech1(String filename) throws FileNotFoundException { // method for reading out character dialogue
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        while (scan.hasNext("them:")) {
            System.out.println(scan.nextLine());
        }

    }


}
