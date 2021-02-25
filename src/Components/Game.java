/**
 * Title: Book.java Date: 2020-01-30
 *
 * @author Ben Keddie 3499200
 * A class for constructing Game objects and running the game.
 * Assuming jdk 11.0 or later Change to directory containing
 * source code.
 * Compile and run: cd into src/Components.
 * javac *.java
 * cd ..
 * java Components.Game
 */
/** Test Plan: see TESTPLAN.txt in WONDER folder.
*/
package Components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game{

    public Game(){

    };
            public static void run() throws IOException {
            BufferedReader in; // run method adapted from Huw Collingbourne, bitwisebooks.com
            String input;
            String output = "";
            Control control = new Control("controller");
            in = new BufferedReader(new InputStreamReader(System.in)); // read user input
            do {
                System.out.print("> "); //output contains >
                input = in.readLine(); // input is continously read
                output = control.run(input); //control runs the input
                System.out.println(output); // output is printed
            } while (!"q".equals(input)); // all of this while the input does not equal q for quit
        }

    public static void main(String[] args) throws IOException {
        Game newGame = new Game(); // create new game object
        Game.run(); // run game
    }}
