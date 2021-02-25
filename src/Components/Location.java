/**
 * Title: Location.java Date: 2020-01-30
 *
 * @author Ben Keddie 3499200
 * A class for constructing location objects with various
 * parameters. Assuming jdk 11.0 or later Change to directory containing
 * source code. Compile and run: see Game.java
 */
package Components;

import java.io.FileNotFoundException;

public class Location extends Inventory {
    private int n, s, w, e;
    private int id;
    private String filename;
    private ItemList list;
    private String name;
    private int visits;
    private String description;
    private Characters character;
    private int numberOfCharacters;


    public Location(int id, String name, String filename, int N, int S, int W, int E, ItemList list, String description, int numberOfCharacters, Characters character) throws FileNotFoundException {
        super(name, list); // constructor for locations containing many fields.
        this.description = description;
        this.name = name;
        this.filename = filename;
        this.id = id;
        this.n = N;
        this.s = S;
        this.w = W;
        this.e = E;
        this.name = name;
        this.visits = 0;
        this.list = list;
        this.character = character;
        this.numberOfCharacters = numberOfCharacters;
    }

    public ItemList getList() {
        return list;
    }

   public Characters getCharacter(){
        return character;
   }

    public String getDescription() {
        return description;
    }

    public ItemList getLocationItems() { // loop through itemlist and print items
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i).getName();
            System.out.println(item);
        }
        return list;
    }




    public String getName() { // getter methods
        return name;
    }

    public int getVisits() {
        return visits;
    }

    public void addExitToForrest() {
        this.s = 8;
    }

    public void addExitToHall() {
        this.e = 2;
    }


    public int getNumberOfCharacters() {
        return numberOfCharacters;
    }


    public String getFilename() {
        return filename;
    }


    public int getId() {
        return id;
    }



    public int getN() {
        return n;
    }



    public int getS() {
        return s;
    }


    public int getW() {
        return w;
    }



    public int getE() {
        return e;
    }



}






