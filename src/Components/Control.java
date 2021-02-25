/**
 * Title: Control.java Date: 2020-01-30
 *
 * @author Ben Keddie 3499200
 * A class for constructing the control object with various
 * parameters. Control contains all pertinent game info and deals with user input.
 * Assuming jdk 11.0 or later Change to directory containing
 * source code. Compile and run: see Game.java
 */
package Components;

import java.io.*;
import java.util.*;

enum Direction {
    NORTH, SOUTH, EAST, WEST
}

public class Control {
    public static final int NOEXIT = -1;
    private final String name;
    private final ArrayList<Location> map;
    private final Player player1;
    private final ArrayList<String> commands = setCommands();
    private final ArrayList<String> dir = setDir();
    private final ArrayList<String> actions = setActions();
    private final ArrayList<String> items = setItems();
    ArrayList<Integer> visits = new ArrayList<>();

    public Control(String name) throws FileNotFoundException {
        this.name = name;

        // Create array lists of items and add items to the lists
        ItemList grandHallList = new ItemList();
        grandHallList.add(new GameItem("vial-b", 10, "A vial of mysterious liquid, marked with a B."));
        grandHallList.add(new GameItem("vial-s", 10, "A vial of mysterious liquid, marked with a S."));
        grandHallList.add(new GameItem("key", 10, "A rusty key."));

        ItemList forrestList = new ItemList();
        forrestList.add(new GameItem("umbrella", 10, "The umbrella that the rabbit dropped."));
        ItemList safeList = new ItemList();
        safeList.add(new GameItem("emerald", 50, "A green emerald."));
        safeList.add(new GameItem("health-potion", 25, "A magic potion"));
        ItemList rabbitList = new ItemList();
        rabbitList.add(new GameItem("heater-coil", 10, "A heater coil for some sort of vaporizer."));
        ItemList caterpillarList = new ItemList();
        caterpillarList.add(new GameItem("laser-pointer", 10, "A laser pointer."));
        ItemList cheshireList = new ItemList();
        cheshireList.add(new GameItem("cookies", 10, "A box of cookies, half eaten."));
        ItemList hattersList = new ItemList();
        hattersList.add(new GameItem("axe", 10, "A sharp axe."));
        hattersList.add(new GameItem("red-fidget-spinner", 100, "A beautiful red fidget spinner with hearts on it."));
        ItemList queensList = new ItemList();
        queensList.add(new GameItem("cell-phone", 100, "Your brand new phone that the rabbit stole."));
        queensList.add(new GameItem("cell-charger", 100, "A charger for your cell phone. "));
        ItemList playerItems = new ItemList();
        Characters Caterpillar = new Characters("The Caterpillar", "caterpillar.txt", caterpillarList, 0);
        Characters Rabbit = new Characters("White Rabbit", "rabbit.txt", rabbitList, 0);
        Characters Cheshire = new Characters("The Cheshire Cat", "cheshire.txt", cheshireList, 0);
        Characters Hatter = new Characters("Mad Hatter", "hatter.txt", hattersList, 0);
        Characters Queen = new Characters("Red Queen", "queen.txt", queensList, 0);
        ItemList houseList = new ItemList();
        ItemList flowerList = new ItemList();
        ItemList jungleList = new ItemList();
        ItemList teaList = new ItemList();
        ItemList domainList = new ItemList();

        // create map of locations and add locations to the map list
        this.map = new ArrayList<>();
        map.add(new Location(0, "Start point park", "start.txt", -1, -1, -1, 1, null, "", 0, null));
        map.add(new Location(1, "Grand Hall", "grandhall.txt", -1, -1, -1, -1, grandHallList, "a grand hall", 0, null));
        map.add(new Location(2, "Forrest", "forrest.txt", 5, -1, 1, 3, forrestList, "a forrest", 0, null));
        map.add(new Location(3, "Rabbit's House", "rabbitshouse.txt", 4, -1, 2, -1, houseList, "rabbit's house", 1, Rabbit));
        map.add(new Location(4, "Talking Flowers Field", "flowersfield.txt", -1, 3, 5, 9, flowerList, "talking flowers field", 1, Caterpillar));
        map.add(new Location(5, "Jungle", "jungle.txt", 6, 2, 7, 4, jungleList, "a jungle", 1, Cheshire));
        map.add(new Location(6, "Safe Room", "saferoom.txt", -1, -1, 7, 5, safeList, "a safe room", 0, null));
        map.add(new Location(7, "Mad Hatter's Tea Party", "teaparty.txt", 6, -1, -1, 5, teaList, "hatter's tea party", 1, Hatter));
        map.add(new Location(8, "Queen's Domain", "queendomain.txt", -1, -1, 2, -1, domainList, "the queen's domain", 1, Queen));
        map.add(new Location(9, "Quick Sand Pit", "pit.txt", -1, -1, -1, -1, null, "You fell into quick sand.", 0, null));
        player1 = new Player(map.get(0), "player1", playerItems);

    }
    // read commands file to set arraylist of valid commands
    public static ArrayList<String> setCommands() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("commands.txt"));
        ArrayList<String> commands = new ArrayList<String>();
        while (scan.hasNextLine()) {
            commands.add(scan.nextLine());
        }
        scan.close();
        return commands;
    }
    // read commands file to set arraylist of valid items
    public static ArrayList<String> setItems() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("items.txt"));
        ArrayList<String> items = new ArrayList<String>();
        while (scan.hasNextLine()) {
            items.add(scan.nextLine());
        }
        scan.close();
        return items;
    }
    // read commands file to set arraylist of valid directions
    public static ArrayList<String> setDir() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("dir.txt"));
        ArrayList<String> dir = new ArrayList<String>();
        while (scan.hasNextLine()) {
            dir.add(scan.nextLine());
        }
        scan.close();
        return dir;
    }
    // read commands file to set arraylist of valid actions
    public static ArrayList<String> setActions() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("actions.txt"));
        ArrayList<String> actions = new ArrayList<String>();
        while (scan.hasNextLine()) {
            actions.add(scan.nextLine());
        }
        scan.close();
        return actions;
    }


    public String getName() {
        return name;
    }
    // file reading method that prints to screen from file.
    public void readFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }

    }
    // speak method, output is a greeting
    public void speak() throws FileNotFoundException {
        Location l = player1.getLocation();
        Characters c = l.getCharacter();
        if (l.getNumberOfCharacters() == 0) { // if location contains no characters, output is below
            System.out.println("There is no one here to talk to.");
        } else if (l.getNumberOfCharacters() > 0 && c.getInteractions() == 0) { // if there are characters present and its the first time speaking with them
            System.out.println(player1.speak()); // speak
            c.readSpeech1(c.getFilename()); // they speak
            c.setInteractions(c.getInteractions() + 1); // add 1 to interactions counter
        } else if (l.getId() != 8 && l.getNumberOfCharacters() > 0 && c.getInteractions() > 0) { // if you arent in location 8, and its not the first interaction
            System.out.println("They have nothing else to say."); // print to output
            c.setInteractions(c.getInteractions() + 1);
        } else if (l.getId() == 8 && c.getInteractions() > 0) { // if you are in location 8, and interactions are greater than 0, game over
            System.out.println("The queen chopped off your head, game over. Your final score is " + player1.getPoints());
            System.exit(-1);
        }

    }

    void movePlayerTo(Player player1, Location location) { // set location of player when moved
        player1.setLocation(location);

    }

    int moveTo(Player player1, Direction dir) throws FileNotFoundException { // move to direction based on exit
        Location l = player1.getLocation(); //adapted from Huw Collingbourne, bitwisebooks.com
        int exit;


        switch (dir) {
            case NORTH:
                exit = l.getN();
                break;
            case SOUTH:
                exit = l.getS();
                break;
            case EAST:
                exit = l.getE();
                break;
            case WEST:
                exit = l.getW();
                break;
            default:
                exit = -1; // noexit - stay in same Location
                break;
        }
        if (exit != -1) {
            movePlayerTo(player1, map.get(exit));


        }
        return exit;
    }

    public int movePlayerTo(Direction dir) throws FileNotFoundException { // move player to location number

        return moveTo(player1, dir);
    }

    private void goN() throws FileNotFoundException {
        showRoomDescription(movePlayerTo(Direction.NORTH));

    }

    private void goS() throws FileNotFoundException {
        showRoomDescription(movePlayerTo(Direction.SOUTH));
    }

    private void goW() throws FileNotFoundException {
        showRoomDescription(movePlayerTo(Direction.WEST));
    }

    private void goE() throws FileNotFoundException {
        showRoomDescription(movePlayerTo(Direction.EAST));
    }

    public Player getPlayer() {
        return player1;
    }


    private void showStr(String s) {
        System.out.println(s);
    }

    private void showInventory() {
        System.out.println("Inventory: ");
        player1.readItems();

    }

    public List<String> wordlist(String input) { //adapted from Huw Collingbourne, bitwisebooks.com
        String delims = " \t,.:;?!\"'"; // take string input and make word list
        List<String> strlist = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(input, delims);
        String t;

        while (tokenizer.hasMoreTokens()) {
            t = tokenizer.nextToken();
            strlist.add(t);
        }
        return strlist;
    }

    public String ValidateCommand(List<String> wordlist) throws FileNotFoundException { // validate user commands based on test cases
        String msg = "";
        String command1 = wordlist.get(0);
        if (wordlist.size() == 1) {

            switch (command1) {
                case "n":
                case "north":
                    goN();
                    break;
                case "s":
                case "south":
                    goS();
                    break;
                case "e":
                case "east":
                    goE();
                    break;
                case "w":
                case "west":
                    goW();
                    break;
                case "inventory":
                case "i":
                case "invent":
                    showInventory();
                    break;
                case "h":
                case "help":
                    readFile("help.txt");
                    break;
                case "health":
                case "hp":
                    System.out.println("Your current hp is " + player1.getHealth());
                    break;
                case "score":
                    System.out.println("Your score is " + player1.getPoints());
                    break;
                case "speak":
                    speak();
                    break;
                default:
                    System.out.println("Enter valid command.");
            }
        } else if (wordlist.size() == 2 && actions.contains(command1)) { // if 2 words
            String action;
            String item;
            boolean error = false;
            action = wordlist.get(0);
            item = wordlist.get(1);
            if (!actions.contains(action)) { // check actions list for validity
                msg = action + " unknown command ";
                error = true;
            }
            if (!items.contains(item)) { // check items list for validity
                msg += (item + " unknown item ");
                error = true;
            }
            if (!error) {
                switch (action) {
                    case "take":
                        msg = takeItem(item);
                        break;
                    case "drop":
                        msg = dropItem(item);
                        break;
                    case "use":
                        msg = useItem(item);
                        break;
                    case "give":
                        msg = giveItem(item);
                        break;

                    default:
                        msg += " (not yet implemented)";
                        break;
                }
            }
            return msg;
        } else if (!dir.contains(command1)) {
            msg = command1 + "invalid command";
            return msg;
        } else if (wordlist.size() == 2) {
            String userCommands = wordlist.get(0) + " " + wordlist.get(1);
            System.out.println(userCommands);
            switch (userCommands) {
                case "go n":
                case "go north":
                    goN();
                    break;
                case "go s":
                case "go south":
                    goS();
                    break;
                case "go e":
                case "go east":
                    goE();
                    break;
                case "go w":
                case "go west":
                    goW();
                    break;
            }
        }
        return msg;
    }

    private void transferItem(GameItem i, ItemList takeFrom, ItemList addTo) { // transfer item from one itemlist to another
        takeFrom.remove(i);
        addTo.add(i);
    }

    public String giveItem(String itemName) throws FileNotFoundException { // transfer item from players list to characters list
        Location l = player1.getLocation();
        Characters c = l.getCharacter();
        String outString = "";
        GameItem i = player1.getItems().thisItem(itemName);
        if (itemName.equals("")) {
            outString = "You'll have to tell me which object you want to give!";
        } else if (i == null) {
            outString = "You haven't got one of those!";
        } else {
            switch (i.getName()) {
                case "vial-b":
                case "vial-s":
                case "health-potion":
                    System.out.println("You can't give this away.");
                    break;
                case "umbrella":
                    if (player1.getLocation().getId() == 3) {
                        transferItem(i, player1.getItems(), c.getItems());
                        System.out.println("You gave the umbrella to the rabbit.");
                        i = c.getItems().get(0);
                        transferItem(i, c.getItems(), player1.getItems());
                        System.out.println("You got a heater-coil from the rabbit.");
                    } else {
                        System.out.println("You can't give this away right now.");
                    }
                    break;
                case "heater-coil":
                    if (player1.getLocation().getId() == 4) {
                        transferItem(i, player1.getItems(), c.getItems());
                        System.out.println("You gave the heater-coil to the caterpillar.");
                        i = c.getItems().get(0);
                        transferItem(i, c.getItems(), player1.getItems());
                        System.out.println("You got a laser-pointer from the caterpillar and he slunk away.");
                        c = null;
                    } else {
                        System.out.println("You can't give this away right now.");
                    }
                    break;
                case "laser-pointer":
                    if (player1.getLocation().getId() == 5) {
                        transferItem(i, player1.getItems(), c.getItems());
                        System.out.println("You gave the laser-pointer to the cheshire cat.");
                        i = c.getItems().get(0);
                        transferItem(i, c.getItems(), player1.getItems());
                        System.out.println("You got a box of cookies from the cat and he disappeared.");
                        c = null;
                    } else {
                        System.out.println("You can't give this away right now.");
                    }
                    break;
                case "cookies":
                    if (player1.getLocation().getId() == 7) {
                        transferItem(i, player1.getItems(), c.getItems());
                        System.out.println("You gave the cookies to the mad hatter.");
                        i = c.getItems().get(0);
                        transferItem(i, c.getItems(), player1.getItems());
                        i = c.getItems().get(0);
                        transferItem(i, c.getItems(), player1.getItems());
                        System.out.println("You got an axe and a red-fidget-spinner from the hatter and he vanished.");
                        c = null;
                    } else {
                        System.out.println("You can't give this away right now.");
                    }
                    break;
                case "axe":
                    if (player1.getLocation().getId() == 2) {
                        System.out.println("You chopped the tree and opened an exit!");
                        player1.getLocation().addExitToForrest();
                    } else {
                        System.out.println("You can't use this here.");
                    }
                    break;
                case "red-fidget-spinner":
                    if (player1.getLocation().getId() == 8) {
                        transferItem(i, player1.getItems(), c.getItems());
                        System.out.println("You gave the red-fidget-spinner to the queen.");
                        i = c.getItems().get(0);
                        transferItem(i, c.getItems(), player1.getItems());
                        System.out.println("You got your cell phone back from the queen.");
                        c = null;
                    } else {
                        System.out.println("You can't give this away right now.");
                    }
                    break;
                case "emerald":
                    if (player1.getLocation().getId() != 8) {
                        System.out.println("You can't give this away here.");
                    } else if (player1.getLocation().getId() == 8) {
                        transferItem(i, player1.getItems(), c.getItems());
                        System.out.println("You gave the emerald to the queen, but she doesn't seem happy.");
                    }
            }
        }
        return outString;
    }

    public String takeItem(String itemName) { // take item laying around
        String outString = "";
        GameItem i = player1.getLocation().getItems().thisItem(itemName);
        if (i == null) {
            outString = "There is no " + itemName + " here!";
        } else {
            transferItem(i, player1.getLocation().getItems(), player1.getItems());
            outString = itemName + " taken!";
            player1.setPoints(player1.getPoints() + i.getValue());
        }
        return outString;
    }

    public String dropItem(String itemName) { //adapted from Huw Collingbourne, bitwisebooks.com
        String retStr = "";
        GameItem i = player1.getItems().thisItem(itemName);
        if (itemName.equals("")) {
            retStr = "You'll have to tell me which object you want to drop!";
        } else if (i == null) {
            retStr = "You haven't got one of those!";
        } else {
            transferItem(i, player1.getItems(), player1.getLocation().getItems());
            retStr = itemName + " dropped!";
        }
        return retStr;
    }

    public String useItem(String itemName) { // use Item test cases and outputs
        String retStr = "";
        GameItem i = player1.getItems().thisItem(itemName);
        if (itemName.equals("")) {
            retStr = "You'll have to tell me which object you want to use!"; // if no object specified
        } else if (i == null) {
            retStr = "You haven't got one of those!";
        } else {
            switch (i.getName()) {
                case "vial-b":
                    System.out.println("You grew bigger!");
                    break;
                case "vial-s":
                    System.out.println("You got smaller!");
                    break;
                case "key":
                    if (player1.getLocation().getId() == 1) {
                        System.out.println("You were able to unlock the door to the east!");
                        player1.getLocation().addExitToHall();
                    }
                    break;
                case "umbrella":
                    System.out.println("You opened the umbrella, careful you don't blow away!");
                    break;
                case "heater-coil":
                    System.out.println("You can't use this item, it is meant for someone else.");
                    break;
                case "health-potion":
                    player1.setHealth(player1.getHealth() + 10);
                    int newHp = player1.getHealth();
                    System.out.println("Your health went up to " + newHp + "!");
                    player1.getItems().remove("health-potion");
                    break;
                case "laser-pointer":
                    System.out.println("You used the laser pointer!");
                    break;
                case "cookies":
                    if (player1.getLocation().getId() != 7)
                        System.out.println("You better save these for later.");
                    break;
                case "axe":
                    if (player1.getLocation().getId() == 2) {
                        System.out.println("You chopped the tree and opened an exit to the south!");
                        player1.getLocation().addExitToForrest();
                    } else {
                        System.out.println("You can't use this here.");
                    }
                    break;
                case "red-fidget-spinner":
                    if (player1.getLocation().getId() != 8) {
                        System.out.println("This fidget spinner looks fun, but you can't use it here.");
                    } else {
                        System.out.println("You used the laser pointer!");

                    }
                    break;
                case "cell-phone":
                    System.out.println("Finally! You can check your Instagram. YOU WIN!\nGame Over. Thanks for playing Wonderland. Your final score is " + player1.getPoints());
                    System.exit(-1);
                    break;
                case "emerald":
                    System.out.println("This is a beautiful emerald but you can't use it.");
            }

        }
        return retStr;
    }


    public String ParseCommand(List<String> wordlist) throws FileNotFoundException { //adapted from Huw Collingbourne, bitwisebooks.com
        String msg = "";
        if (wordlist.size() > 0)
            msg = ValidateCommand(wordlist);
        return msg;
    }


    public void showRoomDescription(int locationNumber) throws FileNotFoundException { // show room descriptions based on test cases
        String s;
        String i;
        String c = "Characters here are: ";
        Location l = getPlayer().getLocation();


        int roomNumber = l.getId();
        if (player1.getLocation().getId() == 9) {

            readFile("pit.txt");
            System.out.println("Your final score: " + player1.getPoints());
            System.exit(-1);
        } else if (locationNumber == NOEXIT) {
            s = "You can't go in that direction!";
            showStr(s);
        } else if
        (Collections.frequency(visits, roomNumber) == 0 && l.getNumberOfCharacters() == 0 && l.getItems() != null) {
            readFile(l.getFilename());
            i = "Items laying around: ";
            showStr(i);
            l.getLocationItems();
            visits.add(roomNumber);
        } else if
        (Collections.frequency(visits, roomNumber) == 0 && l.getNumberOfCharacters() == 1) {
            readFile(l.getFilename());
            i = "Items laying around: ";
            showStr(i);
            l.getLocationItems();
            showStr(c);
            System.out.println(l.getCharacter().getName());
            visits.add(roomNumber);
        } else if (Collections.frequency(visits, roomNumber) > 0 && l.getNumberOfCharacters() == 0) {

            s = "You are in " + l.getDescription() + ". You have been here before.";
            i = "Items laying around: ";
            showStr(s);
            showStr(i);
            l.getLocationItems();

        } else if (Collections.frequency(visits, roomNumber) > 0 && l.getNumberOfCharacters() > 0) {

            s = "You are in " + l.getDescription() + ". You have been here before.";
            i = "Items laying around: ";
            c = "Characters here are: ";
            showStr(s);
            showStr(i);
            l.getLocationItems();
            showStr(c);
            System.out.println(l.getCharacter().getName());

        }


    }


    public String run(String str) throws FileNotFoundException { //adapted from Huw Collingbourne, bitwisebooks.com
        List<String> wordlist; // run method
        String s = "ok";
        String lowStr = str.trim().toLowerCase(); // changes all input to lowercase
        if (!lowStr.equals("q")) {
            if (lowStr.equals("")) {
                s = "You must enter a command";
            } else {
                wordlist = wordlist(lowStr);
                s = ParseCommand(wordlist);
            }
        }
        return s;
    }

}
