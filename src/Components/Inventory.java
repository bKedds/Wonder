/**
 * Title: Inventory.java Date: 2020-01-30
 *
 * @author Ben Keddie 3499200
 * A class for constructing Inventory objects with various
 * parameters. Assuming jdk 11.0 or later Change to directory containing
 * source code. Compile and run: see Game.java
 */
package Components;

public class Inventory {
    private String name;
    private ItemList items = new ItemList();

    public Inventory(String name, ItemList il) { // constructor for inventory
        this.name = name;
        items = il;
    }

    public ItemList getItems() { // getter methods
        return items;
    }

    public String getName() {
        return name;
    }


    public ItemList readItems() { // print items and their description
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i).getName();
            String desc = items.get(i).getDescription();
            System.out.println(item + ": " + desc);
        }
        return items;
    }


}

