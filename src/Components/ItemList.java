/**
 * Title: ItemList.java Date: 2020-01-30
 *
 * @author Ben Keddie 3499200
 * A class for constructing ItemList Array Lists with various
 * parameters. Assuming jdk 11.0 or later Change to directory containing
 * source code. Compile and run: see Game.java
 */
package Components;

import java.util.ArrayList;
public class ItemList extends ArrayList<GameItem>{ // Item list is arraylist of game items



    public GameItem thisItem(String name) { // get name of item and make it lowercase, return item name for later use
        GameItem item = null;
        String itemName = "";
        String nameLowCase = name.trim().toLowerCase();
        for (GameItem i : this) {
            itemName = i.getName().trim().toLowerCase();
            if (itemName.equals(nameLowCase)) {
                item = i;
            }
        }
        return item;
    }
}