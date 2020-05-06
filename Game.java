    import java.util.ArrayList;

/**
 *  This class is the main class of the "Salving the World from COVID-19" application. 
 *  "Salving the World from COVID-19" is a educative, text based adventure game. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, all items and creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author  Diego Bueno da Silva
 * @version 2020.05.04
 */

public class Game 
{
    private final Parser parser;
    private Room currentRoom;
    private ArrayList<Item> itemsCollectedByPlayer;
    Item mask, safePassword, gloves, protectionGlasses, safeKey, 
    handSanitiser, guineaPigs, covid19VaccineFormula, covid19Vaccine;

        
    /** Game constructor
     * Create the game and initialise its internal map and items.
     */
    public Game() 
    {
        itemsCollectedByPlayer = new ArrayList<Item>();
        createItems();
        createRooms();
        parser = new Parser();
    }

    /** createItems method
     * Create all the items that the player perhaps take to save the World.
     * This method does not have parametres and return.
     */
    private void createItems()
    {  
        // creating the items of the game
        mask = new Item("Mask","an mask helps you breathe safely.",TypeOfItem.PPE);
        safePassword = new Item("Password","The password is necessary to open the safe.",TypeOfItem.KEY);
        gloves = new Item("Gloves","It is a good ideia to use when collecting things.",TypeOfItem.PPE);
        protectionGlasses = new Item("Protection glasses","It will protect your eyes from COVID-19 virus.",TypeOfItem.PPE);
        safeKey = new Item("Key","The key is necessary to open the safe.",TypeOfItem.KEY);
        handSanitiser = new Item("Hand sanitiser","You must wash your hands often to avoid COVID-19 virus.",TypeOfItem.ANTISEPTIC);
        guineaPigs = new Item("Guinea Pig","This kind animal may are infected by COVID-19 virus.",TypeOfItem.HARMFUL);
        covid19VaccineFormula = new Item("Formula","The valuable COVID-19 Vaccine Formula",TypeOfItem.FORMULA);
        covid19Vaccine = new Item("Vaccine","COVID-19 Vaccine to save the World!",TypeOfItem.VACCINE);
    }

    /** createRooms method
     * Create all the rooms, link their exits together and assign to their its items
     * This method does not have parametres and return.
     */
    private void createRooms()
    {
        Room outside, reception, ppeRoom, managerRoom, lavatory, iTroom, testingRoom, 
        upstairsVaultRoom, downstairsVaultRoom, upstairsLaboratory, downstairsLaboratory;

        // creating the rooms 
        outside = new Room("outside the main entrance of the building");
        reception = new Room("in the reception");
        ppeRoom = new Room("in the PPE room");
        managerRoom = new Room("in the Manager room");
        lavatory = new Room("in the Lavatory");
        iTroom = new Room("in the IT room");
        testingRoom = new Room("in the Testing room");
        upstairsVaultRoom = new Room("in upstairs of the Vault room");
        downstairsVaultRoom = new Room("in downstairs of the Vault room");
        upstairsLaboratory = new Room("in upstairs of the Laboratory");
        downstairsLaboratory = new Room("in downstairs of the Laboratory");

        /****************  initialising room exits and its items  *******************/

        // setting Outside exists
        outside.setExit("north", reception);

        // setting Reception exists and adding a mask
        reception.setExit("south", outside);
        reception.setExit("east", iTroom);
        reception.setExit("north", ppeRoom);
        reception.addItemInTheRoom(mask);        

        // setting PPE room exists and adding gloves
        ppeRoom.setExit("south", reception);
        ppeRoom.setExit("east", managerRoom);
        ppeRoom.addItemInTheRoom(gloves);

        // setting Manager room exists and adding key of the safe
        managerRoom.setExit("south", lavatory);
        managerRoom.setExit("west", ppeRoom);
        managerRoom.addItemInTheRoom(safeKey);

        // setting Lavatory room exists and adding hand sanitiser
        lavatory.setExit("south", iTroom);
        lavatory.setExit("north", managerRoom);
        lavatory.setExit("east", downstairsVaultRoom);
        lavatory.addItemInTheRoom(handSanitiser);        

        // setting IT room exists and adding password of the safe
        iTroom.setExit("north", lavatory);
        iTroom.setExit("east", testingRoom);
        iTroom.setExit("west", reception);
        iTroom.addItemInTheRoom(safePassword);        

        // setting Testing room exists and adding guinea pigs    
        testingRoom.setExit("west", iTroom);
        testingRoom.setExit("east", upstairsLaboratory);
        testingRoom.addItemInTheRoom(guineaPigs);        

        // setting upstairs Laboratory exists and adding the vaccine
        upstairsLaboratory.setExit("west", testingRoom);
        upstairsLaboratory.setExit("down", downstairsLaboratory);
        upstairsLaboratory.setExit("north", downstairsVaultRoom);
        upstairsLaboratory.addItemInTheRoom(covid19VaccineFormula);
        
        // setting downstairs Laboratory exists       
        downstairsLaboratory.setExit("up", upstairsLaboratory);
        // There is not item initially, only virus spread there

        // setting downstairs Vault room exists and adding glasses
        downstairsVaultRoom.setExit("west", lavatory);
        downstairsVaultRoom.setExit("south", upstairsLaboratory);
        downstairsVaultRoom.setExit("up", upstairsVaultRoom);
        downstairsVaultRoom.addItemInTheRoom(protectionGlasses);

        // setting upstairs Vault room exists and adding vaccine
        upstairsVaultRoom.setExit("down", downstairsVaultRoom);
        upstairsVaultRoom.addItemInTheRoom(covid19Vaccine);

        currentRoom = outside;  // starting the game outside of the building
        // currentRoom = currentRoom.getExit("outside");
    }

    /** play method
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            final Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Be safety!");
    }

    /** printWelcome method
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the \"Salving the World from COVID-19\" game!");
        System.out.println("In order to save the world of the COVID-19 pandemic, the player needs to collect");
        System.out.println("the vaccine for COVID-19 in the building which has high risk of contamination.\n");
        System.out.println("To access the Vault room, to open the safe and to pick up the vaccine, the player needs to get");
        System.out.println("a password in the IT room and a key in the Manager room. The player may pass for");
        System.out.println("the Testing room and Laboratory. However, if he/she accesses the Testing room or the downstairs");
        System.out.println("Laboratory without wearing a mask, a glove and a protection glasses,");
        System.out.println("he/she will die from COVID-19. In case the player does not access the Testing room and");
        System.out.println("the downstairs Laboratory, he/she does not have to wear PPE. To win the game, the player"); 
        System.out.println("must collec the COVID-19 vaccine and a hand sanitizer to wash his/her hands before leaving"); 
        System.out.println("the building. If the player collects the COVID-19 vaccine and also the COVID-19 vaccine formula,");
        System.out.println("he/she will get extra award despite win the game!");

        System.out.println("Type 'help' if you need help.");
     
        System.out.println(currentRoom.getLongDescription());     
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(final Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        final String commandWord = command.getCommandWord();
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
        System.out.println("around at the university.");
        System.out.println();

        System.out.println("Your command words are:");
        //System.out.println("Your command words are:"); parser.showCommands();
        System.out.println("   go quit help");

    }

    /** goRoom method
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(final Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        final String direction = command.getSecondWord();

        // Trying to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

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
    private boolean quit(final Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
