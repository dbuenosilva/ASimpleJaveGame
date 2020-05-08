
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

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Game 
{
    final static boolean INSIDE = true;
    final static boolean OUTSIDE = false;
    private final Parser parser;
    private Room currentRoom;
    private Player player01;
    private Item mask, safePassword, gloves, protectionGlasses, safeKey, 
    handSanitiser, guineaPigs, covid19VaccineFormula, covid19Vaccine, safe;

        
    /** Game constructor
     * Create the game and initialise its internal map,
     * rooms and its items.
     */
    public Game() 
    {
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

    /** createRooms method
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

        currentRoom = outside;  // starting the game outside
    }

    /** play method
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        player01 = new Player(JOptionPane.showInputDialog("Please enter the name of the player 01"));

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
        currentRoom = null;
        player01 = null;
        mask = safePassword = gloves = protectionGlasses = safeKey =
        handSanitiser = guineaPigs = covid19VaccineFormula =  covid19Vaccine = safe = null;
        createItems();
        createRooms();     
    }

    /** printWelcome method
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
        System.out.println("Type \"help\" if you need help with commands and \"map\" the see the initial map.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());     
    }

    /** processCommand method
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(final Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Invalid command  :( \n Try \"help\" to see a set of valid commands");
            return false;
        }

        final String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            wantToQuit = goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }        
        else if (commandWord.equals("inventory")) {
            inventory();
        }  
        else if (commandWord.equals("open")) {
            open(command);
        }  
        else if (commandWord.equals("map")) {
            map();
        }  

        return wantToQuit;
    }

    /*************** implementations of user commands ****************/

    /** printHelp method
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Come on " + player01.getName() + "! Are you at least wearing Personal Protective Equipment?\n");
        System.out.println("Pay attention to where you are going! You can die from COVID-19...");
        System.out.println(parser.showCommands());
    }

    /** goRoom method
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
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Sorry " + player01.getName());
            System.out.println("There is no door in the direction " + direction + " " +  currentRoom.getDescription());
        }
        else {           
                currentRoom = nextRoom;
                // Evaluate if the player could get in the room
                if (nextRoom.evaluateIfPlayerCanGetInTheRoom(player01) && currentRoom.isInsideOfTheBuilding() ) {
                    System.out.println(currentRoom.getLongDescription());
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

    /** look method
     * Display where the play is and what items are in the room
     * After a sequence of commands the player may become lost,
     * "look" command will help the player remember where he is.
     */
    private void look()
    {
        System.out.println(currentRoom.getLongDescription()); 
    }

    /** take method
     * Pick up a item available in the room.
     * The player can carry this item thought the building if
     * it is a good item. Some items may kill the player in case
     * he/she does not use PPE.
     */
    private void take(final Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }

        final String nameOfItem = command.getSecondWord();        
        Iterator<Item> itemsInTheRoom = currentRoom.getItemsInTheRoom().iterator();

        while(itemsInTheRoom.hasNext()){   
            
            Item itemToBeCollected;
            itemToBeCollected = itemsInTheRoom.next();

            if(itemToBeCollected.getName().equals(nameOfItem)) {

                // transfering the item from the room to the player
                if (currentRoom.removeItemFromTheRoom(itemToBeCollected)) {  

                    if(player01.addItemToThePlayer(itemToBeCollected)) {
                        System.out.println("Well done " + player01.getName() + "!");                        
                        System.out.println("Item " + itemToBeCollected.getName() + " collected.");
                        return; // stop the loop, item found and added successful!  
                    }
                    else {// somethis went wrong to add the item to player
                        System.out.println("Error to try to take the item \"" + itemToBeCollected.getName() + "\" from the room.");    
                        System.out.println("Hint: Check if the item \"" + itemToBeCollected.getName() + "\" can be carried or maybe openned.");                                          
                        currentRoom.addItemInTheRoom(itemToBeCollected); // Roolback previous operation due some error                        
                        return;// stop the loop, item found but there is some inconsistent 
                    }
                }
                else { // somethis went wrong to room give the item to player
                    System.out.print("Error to try to take the item \"" + itemToBeCollected.getName() + "\" from the room.");                    
                    return;// stop the loop, item found but there is some inconsistent                    
                }                
            }

            // If it reach this point, the item asked is not in the room
            System.out.println("Unfortunately, the item \"" + nameOfItem + "\" is not in the room :(");
            System.out.println(currentRoom.getItemsString());                        
        }
      
    }

    /** drop method
     * Drop a item available in the room.
     * The player can drop an item in the room in case
     * the room is not full.
     */
    private void drop(final Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }

        final String nameOfItem = command.getSecondWord();
        Iterator<Item> items = player01.getItemsWithThePlayer().iterator();

        while(items.hasNext()){   
            Item itemToBeDropped = items.next();
            if(itemToBeDropped.getName().equals(nameOfItem)) {

                // transfering the item from the player to the room
                if(currentRoom.addItemInTheRoom(itemToBeDropped)) { // if the room accepts this item
                    if(player01.removeItemFromThePlayer(itemToBeDropped)) {  // and if player can drop the item
                        System.out.print("Item " + itemToBeDropped.getName() + " dropped " );
                        System.out.println(currentRoom.getDescription());
                        return;  // stop the loop, item found and dropped!                               
                    }
                    else { // somethis went wrong to player drop the item
                        System.out.print("Error to try to drop the item " + itemToBeDropped.getName() + " in the room " );
                        currentRoom.removeItemFromTheRoom(itemToBeDropped); // rollback previous instruction
                        return;// stop the loop, item found but there is some inconsistent
                    }
                }
                else { // The room maybe is full
                    System.out.println("Error to try to drop the item " + itemToBeDropped.getName() + " in the room,");
                    System.out.println("Try \"look\" command and check if " + currentRoom.getDescription() + " is full,");                    
                    System.out.println("or \"inventory\" command and check if you really have the item " + itemToBeDropped.getDescription());
                    return;// stop the loop, item found but there is some inconsistent
                }                           
            }
        }
        // The item asked is not with the player
        System.out.println("Sorry " + player01.getName() + ", you do not have the item " + nameOfItem + " :(");
        System.out.println("Try \"inventory\" command and check what items do you have with you.");
    }   

    /** open method
     * Open a item available in the room.
     * The player can open an item in the room in case
     * the item has some objects inside its.
     */
    private void open(final Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Open what?");
            return;
        }

        final String nameOfItemToOpen = command.getSecondWord();

        // Checking if the item to be open is in the room
        Item itemToBeOpenned = currentRoom.evaluateItemInTheRoom(nameOfItemToOpen);

        if( itemToBeOpenned != null) { 

            // Item is in the room, then getting all the necessary items to open it.
            Iterator<Item> items = itemToBeOpenned.getItemNeededToOpenThis().iterator();
            while(items.hasNext()){   
                Item currentItem = items.next();

                // If the player do not have one of necessary item
                if ( player01.evaluateItemWithThePlayer(currentItem.getName()) == null ) {
                    System.out.println("Sorry " + player01.getName() + ", you do not have the item \"" + currentItem.getName() + "\" to open the " + itemToBeOpenned.getName() + " :(");                  
                    System.out.println("You need to take the items: " + itemToBeOpenned.getNeededItemsString() + " to open the \"" + itemToBeOpenned.getName() + "\" "); 
                    System.out.println("Hint: use the command \"inventory\" to check which of them you have already collected.");               
                    return;
                }
            }

            // If the program reaches this point, the player has all the necessary items to open it.
            // Let's check what are inside of it!
            Iterator<Item> itemsInsideOfItemOpenned = itemToBeOpenned.getItemsInsideAnotherItem().iterator();            

            // auxiliary array to save items inside the safe
            ArrayList<Item> itemsCauthtByThePlayer = new ArrayList<Item>(); 

            // Checking if this item is empty
            if ( ! itemsInsideOfItemOpenned.hasNext() ) { 
                System.out.println("There is nothing inside of " + itemToBeOpenned.getName() + " :("); 
            }
            else { // Saving the list of item inside it to remove soon and to give to the player
                while(itemsInsideOfItemOpenned.hasNext()) {                    
                    itemsCauthtByThePlayer.add(itemsInsideOfItemOpenned.next());  
                }
            }

            // Moving the itens inside it to the player
            Iterator<Item> itemsToMove = itemsCauthtByThePlayer.iterator();            
            while(itemsToMove.hasNext()) {
                Item itemToMove = itemsToMove.next();
                if(player01.addItemToThePlayer(itemToMove)) {  // give to player                
                    if (itemToBeOpenned.removeItemFromAnotherItem(itemToMove) ) { // removing from it
                        System.out.println("Congrats " + player01.getName() + "!!!");                        
                        System.out.println("\"" + itemToBeOpenned.getName() + "\" openned and item " + itemToMove.getName() + " collected successfully!"); 
                        System.out.println("Hint: type \"inventory\" and check if you have all required items to win the game!" );                                          
                    } 
                    else { // in case something goes wrong, rollback the one part on operation
                        System.out.println("Sorry " + player01.getName() + ", the item \"" + itemToMove.getName() + "\" could not be collected from " + itemToBeOpenned.getDescription() + " :(");
                        player01.removeItemFromThePlayer(itemToMove); // rollback previous operation                       
                    }
                }
                else { // For some reason, this item can not be moved...
                    System.out.println("Sorry " + player01.getName() + ", the item \"" + itemToMove.getName() + "\" could not be collect :(");
                    System.out.println("Hint: check if the item " + itemToBeOpenned.getDescription() + " can be carried or maybe openned.");                  
                }
            }
        }      
        else { // Item that was required to be openned is not in the room
            System.out.println("Sorry " + player01.getName() + ", there is not item \"" + nameOfItemToOpen + "\" " + currentRoom.getDescription() + " :(");
        }        
    }  

    /** inventory method
     * Print out all items owned by the player.
     */
    private void inventory() 
    {
        System.out.println(player01.getStringItemsWithThePlayer());     
    }

    /** map method
     * Show a image of the initial map to the player.
     */
    private void map() 
    {
       ImageIcon map = new ImageIcon(System.getProperty("user.dir") + "/map.png");
       JOptionPane.showMessageDialog(null, "", "Map of building", 
                                            JOptionPane.INFORMATION_MESSAGE, map);   
    }

    /** resultOftheGame method
     * The resultOftheGame method ends the game and shows the result
     * to the play. In case he/she get out of building with the 
     * vaccine and handsanitiser he/she won the game, otherwise gameover!
     */
    private void resultOftheGame() {

        Iterator<Item> itemsThePlayerShouldHave = currentRoom.getListOfCompulsoryItemsWithThePlayer().iterator();

        while( itemsThePlayerShouldHave.hasNext()) {

            Item itemInAnalyse = itemsThePlayerShouldHave.next();
            
            // Checking if the player has the item
            if( player01.getItemWithThePlayer( itemInAnalyse.getName() ) != null ) {
                
                // Checking if the player left the building and win the game
                if ( ! currentRoom.isInsideOfTheBuilding() ) {

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
                System.out.println("You must never get " + currentRoom.getDescription() + " without take: ");
                System.out.println( currentRoom.getStringOfCompulsoryItemsWithThePlayer() );
                return;
            }
        }
    }
    /*************** end of implementations of user commands **************/    
}
