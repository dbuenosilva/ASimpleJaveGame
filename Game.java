
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

import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Game 
{
    final static boolean INSIDE = true; // constant to set room as inside of the building
    final static boolean OUTSIDE = false; // constant to set room as outside of the building
    private final Parser parser; // parse of the game
    private Player player01; // the player
    private Item mask, safePassword, gloves, protectionGlasses, safeKey, 
    handSanitiser, guineaPigs, covid19VaccineFormula, covid19Vaccine, safe;

    /**  
     * Constructor creates the game and initialise its internal map,
     * rooms and its items.
     */
    public Game() 
    {
        createPlayers();
        createItems();
        createRooms();      
        parser = new Parser();
    }

    /** 
     * Create all the items that the player perhaps take to save the World.
     * This method does not have parametres and return.
     */
    private void createItems()
    {  
        // creating the items of the game
        mask = new Item("mask","A mask helps you breathe safely.");
        safePassword = new Item("password","The password is necessary to open the safe.");
        gloves = new Item("gloves","It is a good ideia to use when collecting things.");
        protectionGlasses = new Item("protection-glasses","It will protect your eyes from COVID-19 virus.");
        safeKey = new Item("key","The key is necessary to open the safe.");
        handSanitiser = new Item("hand-sanitiser","You must wash your hands often to avoid COVID-19 virus.");
        guineaPigs = new Item("guinea-pig","This kind animal may are infected by COVID-19 virus.");
        covid19VaccineFormula = new Item("formula","The valuable COVID-19 Vaccine Formula");
        covid19Vaccine = new Item("vaccine","COVID-19 Vaccine to save the World!");
        safe = new Item("safe","Inside the safe contains the COVID-19 Vaccine that can save the World!");
        
        // Only the item Safe can not be carried by the player ( it is too heavy! )
        safe.setItCanBeCarried(false); 

        //Defining the required items to open the safe
        safe.addItemNeededToOpenThis(safePassword);
        safe.addItemNeededToOpenThis(safeKey);

    }

    /** 
     * Create the players who perhaps saves the World.
     * This method does not have parametres and return.
     */
    private void createPlayers() 
    {
        player01 = new Player(JOptionPane.showInputDialog("Please enter the name of the player 01"));
    }

    /** 
     * Create all the rooms, link their exits together and assign to their its items
     * This method does not have parametres and return.
     */
    private void createRooms()
    {
        Room outside, reception, ppeRoom, managerRoom, lavatory, iTroom, testingRoom, 
        upstairsVaultRoom, downstairsVaultRoom, upstairsLaboratory, downstairsLaboratory;

        // creating the rooms 
        outside = new Room("outside the main entrance of the building",OUTSIDE);
        reception = new Room("in the reception",INSIDE);
        ppeRoom = new Room("in the PPE room",INSIDE);
        managerRoom = new Room("in the Manager room",INSIDE);
        lavatory = new Room("in the Lavatory",INSIDE);
        iTroom = new Room("in the IT room",INSIDE);
        testingRoom = new Room("in the Testing room",INSIDE);
        upstairsVaultRoom = new Room("in upstairs of the Vault room",INSIDE);
        downstairsVaultRoom = new Room("in downstairs of the Vault room",INSIDE);
        upstairsLaboratory = new Room("in upstairs of the Laboratory",INSIDE);
        downstairsLaboratory = new Room("in downstairs of the Laboratory",INSIDE);

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
        upstairsVaultRoom.addItemInTheRoom(safe);

        // Putting the vaccine inside the safe
        safe.addItemInsideAnother(covid19Vaccine);


        /*********** setting compusory items that player should have to acess the room ***********/        
        outside.addCompulsoryItemsWithThePlayer(covid19Vaccine);
        outside.addCompulsoryItemsWithThePlayer(handSanitiser);

        testingRoom.addCompulsoryItemsWithThePlayer(mask);
        testingRoom.addCompulsoryItemsWithThePlayer(gloves);
        testingRoom.addCompulsoryItemsWithThePlayer(protectionGlasses);

        downstairsLaboratory.addCompulsoryItemsWithThePlayer(mask);
        downstairsLaboratory.addCompulsoryItemsWithThePlayer(gloves);
        downstairsLaboratory.addCompulsoryItemsWithThePlayer(protectionGlasses);

        player01.setCurrentRoom(outside);  // starting the game outside
    }

    /** 
     *  Start the game. Loops until end of play.
     */
    public void play() 
    {            
        // If the player got Game Over, give him/her a new opportunity to play
        if( player01 == null ) {
            createPlayers();
            createItems();
            createRooms(); 
         }

        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            final Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Be safety!");

        // Reset objects in case the player wants to try again
        player01 = null;
        mask = safePassword = gloves = protectionGlasses = safeKey =
        handSanitiser = guineaPigs = covid19VaccineFormula =  covid19Vaccine = safe = null;    
    }

    /** 
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the \"Salving the World from COVID-19\" game!");
        System.out.println("In order to save the world of the COVID-19 pandemic, you need to find and to collect");
        System.out.println("the \"vaccine\" for COVID-19 in the building which has high risk of contamination.");
        System.out.println();
        System.out.println("To access the Vault room, to open the safe and to pick up the \"vaccine\", you need to ");
        System.out.println("get a \"password\" in the IT room and a \"key\" in the Manager room. You can pass for ");
        System.out.println("the Testing room and Laboratory. However, if you access the Testing room or the downstairs ");
        System.out.println("Laboratory without wearing a \"mask\", a \"glove\" and a \"protection-glasses\", you will die ");
        System.out.println("from COVID-19. In case you do not access the Testing room and the downstairs Laboratory,");
        System.out.println("you do not have to wear mask, gloves and protection-glasses."); 
        System.out.println();
        System.out.println("To win the game, you have to collec the COVID-19 \"vaccine\" and a \"hand-sanitizer\" to wash your hands ");
        System.out.println("before leaving the building. If you collects the COVID-19 vaccine and also the COVID-19 "); 
        System.out.println("vaccine formula, you will get an award despite win the game!");
        System.out.println();
        System.out.println("Type \"help\" if you need help with commands and \"map\" to see the initial map.");
        System.out.println();
        System.out.println(player01.getCurrentRoom().getLongDescription());     
    }

    /** 
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(final Command command) 
    {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN: {
                System.out.println("Command invalid! Please try \"help\" to check available commands.");
                break;
            }
            case HELP: {
                printHelp();
                break;
            }
            case GO: {
                wantToQuit = goRoom(command);
                break;
            }
            case LOOK: {
                player01.look();
                break;
            }
            case TAKE: {
                player01.take(command);
                break;
            }
            case DROP: {
                player01.drop(command);
                break;
            }
            case INVENTORY: {
                player01.inventory();
                break;
            }
            case OPEN: {
                player01.open(command);
                break;
            }
            case MAP: {
                map();
                break;
            }          
            case QUIT: {
                wantToQuit = quit(command); 
                break;                                                  
            }
        }
        return wantToQuit;
    }

    /** 
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Come on " + player01.getName() + "! Are you at least wearing Personal Protective Equipment?\n");
        System.out.println("Pay attention to where you are going! You can die from COVID-19...");
        System.out.println(parser.getCommandsString());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     * Return false in case the player gets gave over or win the game
     * @param command to processes the second word.
     * @return  false in case the player gets gave over or win the game
     */
    private boolean goRoom(final Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return (false);
        }

        final String direction = command.getSecondWord();

        // Trying to leave current room.
        Room nextRoom = player01.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("Sorry " + player01.getName());
            System.out.println("There is no door in the direction " + direction + " " +  player01.getCurrentRoom().getDescription());
        }
        else {           
            player01.setCurrentRoom(nextRoom);
            // Evaluate if the player could get in the room
            if (nextRoom.evaluateIfPlayerCanGetInTheRoom(player01) && player01.getCurrentRoom().isInsideOfTheBuilding() ) {
                System.out.println(player01.getCurrentRoom().getLongDescription());
            }
            else {
                resultOftheGame();                    
                return( true );
            }
        }   
    return( false );         
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

    /** 
     * Show a image of the initial map to the player.
     */
    private void map() 
    {
       ImageIcon map = new ImageIcon(System.getProperty("user.dir") + "/map.png");
       JOptionPane.showMessageDialog(null, "", "Map of building", 
                                            JOptionPane.INFORMATION_MESSAGE, map);   
    }

    /** 
     * The resultOftheGame method ends the game and shows the result
     * to the play. In case he/she get out of building with the 
     * vaccine and handsanitiser he/she won the game, otherwise gameover!
     */
    private void resultOftheGame() {

        Iterator<Item> itemsThePlayerShouldHave = player01.getCurrentRoom().getListOfCompulsoryItemsWithThePlayer().iterator();

        while( itemsThePlayerShouldHave.hasNext()) {

            Item itemInAnalyse = itemsThePlayerShouldHave.next();
            
            // Checking if the player has the item
            if( player01.getItemWithThePlayer( itemInAnalyse.getName() ) != null ) {
                
                // Checking if the player left the building and win the game
                if ( ! player01.getCurrentRoom().isInsideOfTheBuilding() ) {

                    // Checking if he/she colleted the vaccine
                    if ( player01.evaluateItemWithThePlayer("vaccine") != null ) {
                         
                        // Checking if he/she got the hand-sanitiser
                        if ( player01.evaluateItemWithThePlayer("hand-sanitiser") != null ) {

                            System.out.println("CONGRATULATIONS " + player01.getName() + "! YOU WIN THE GAME");
                            System.out.println();

                            // Checking if he/she got extra prizer, the formula
                            if ( player01.evaluateItemWithThePlayer("formula") != null ) {
                                System.out.println("YOU ARE HERO!!! Have also got the COVID-19 vaccine formula!");
                            }
                            else {
                                System.out.println("YOU ARE CHAMPS!!!");
                            }
                            System.out.println();
                            System.out.println("However, please keep washing you hands :)");
                            System.out.println(); 
                            return;
                        }
                        // Game Over for forgot collect hand-sanitiser to wash his/her hand
                        else {                       
                            System.out.println("GAME OVER " + player01.getName() + "! You almost win the game...");
                            System.out.println();
                            System.out.println("However, you forgot to take the hand-sanitiser to wash you hands :(");
                            System.out.println();   
                            return;
                        }             
                    }
                }
            }

            else { // the player does not have all the items 
                System.out.println("GAME OVER " + player01.getName() + "!");
                System.out.println();
                System.out.println("You must never get " + player01.getCurrentRoom().getDescription() + " without take: ");
                System.out.println( player01.getCurrentRoom().getStringOfCompulsoryItemsWithThePlayer() );
                return;
            }
        }
    }
}
