import java.util.ArrayList;

/**
 *  This class is the main class of the "Salving the World from COVID-19" application. 
 *  "Salving the World from COVID-19" is a very simple, text based adventure game. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author  Diego Bueno da Silva
 * @version 2020.05.04
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList items;
        
    /** Game() constructor
     * Create the game and initialise its internal map and itens
     */
    public Game() 
    {
        createItems();
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the items that the player can take.
     */
    private void createItems()
    {
        Item mask, vaultPassword, gloves, protectionGlasses, vaultKey, 
             handSanitizer, guineaPigs, covid19VaccineFormula, covid19Vaccine;
        
      
        // create the items 
        mask = new Item();
        vaultPassword = new Item();
        gloves = new Item();
        protectionGlasses = new Item();
        vaultKey = new Item();
        handSanitizer = new Item();
        guineaPigs = new Item();
        covid19VaccineFormula = new Item();
        covid19Vaccine = new Item();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
      
        /// 
// lab.setExit("north", outside); 
//lab.setExit("east", office);


        // create the rooms ( minimum 6 ROOMS )
        outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExits(null, theater, lab, pub, null, null);
        theater.setExits(null, null, null, outside, null, null);
        pub.setExits(null, outside, null, null, null, null);
        lab.setExits(outside, office, null, null, null, null);
        office.setExits(null, null, null, lab, null, null);

        currentRoom = outside;  // start game outside
        ///currentRoom = currentRoom.getExit("outside");
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the \"Salving the World from COVID-19\" game!");
        System.out.println("In order to save the world of the COVID-19 pandemic, the player needs to collect");
        System.out.println("the vaccine for COVID-19 in the building which has high health risk of contamination.");
        System.out.println("To access the Vault room, open the safe and pick up the vaccine, the player needs to get");
        System.out.println("the password in the IT room and the key in the Manager room. The player may pass for");
        System.out.println("the Testing room and Laboratory. However, if he/she access the Testing room or the downstairs");
        System.out.println("Laboratory without taking a mask in the Reception, a glove and a protection glasses in the PPE room,");
        System.out.println("he/she will die by COVID-19. In case the player does not access the Testing room and");
        System.out.println("the downstairs Laboratory, he/she does not have to take PPE. To win the game, the player"); 
        System.out.println("must pick up the COVID-19 vaccine and a hand sanitizer to wash his/her hands before leaving"); 
        System.out.println("the building. If the player picks up the COVID-19 vaccine and also the COVID-19 vaccine formula,");
        System.out.println("he/she will get extra award despite win the game.");

        System.out.println("Type 'help' if you need help.");
     
        System.out.println(currentRoom.getLongDescription());
     
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the building.");
        System.out.println();

        System.out.println("Your command words are:");
        //System.out.println("Your command words are:"); parser.showCommands();
        System.out.println("   go quit help");

    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;

//Room nextRoom = currentRoom.getExit(direction);

        if(direction.equals("north")) {
            nextRoom = currentRoom.getNorthExit();
            // nextRoom = currentRoom.getExit("nextRoom");
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.getEastExit(); //currentRoom.getExit("nextRoom");
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.getSouthExit(); //currentRoom.getExit("nextRoom");
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.getWestExit(); // currentRoom.getExit("nextRoom");
        }

        if(direction.equals("up")) {
            nextRoom = currentRoom.getUpExit(); // currentRoom.getExit("nextRoom");
        }

        if(direction.equals("up")) {
            nextRoom = currentRoom.getDownExit(); // currentRoom.getExit("nextRoom");
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }


    /**  
     *
     * This method is deprecated by Programming exercise 2 solution
     *
     * "getRoomExitsAndDescription" print the current room
     * that the player is in.
     * This method does not receive parameter either return values.
     *
    private void getRoomExitsAndDescription() 
    {
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
        System.out.println();
    } */

}
