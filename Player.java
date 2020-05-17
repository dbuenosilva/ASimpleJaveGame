
/**
* Class Player - a player in an "Saving the World from COVID-19" game.
 *
 * This class is part of the "Salving the World from COVID-19" application. 
 * "Salving the World from COVID-19" is a educative, text based adventure game.  
 *
 * A "Player" represents one player necessary to play the game. Its implementation
 * encapsule detais of player like his/her items and also support future
 * options for multiplayer and multithreaded programming.
 *
 * @author  Diego Bueno da Silva
 * @version 2020.05.04
 */
import java.util.ArrayList;
import java.util.Iterator;

public class Player
{
    private ArrayList<Item> itemsBelongedToPlayer; // Items collect by the player during the game
    private String name; // name of the player
    private Room currentRoom; // current room where this player is

    /**
     * Constructor for objects of class Player
     * Initialise with a name and a empty list of items
     */
    public Player(String name)
    {
        this.name = name;
        itemsBelongedToPlayer = new ArrayList<Item>();
    }

    /** 
    * Returns a String containing the name of the player
    * @return The name of the player.
    */    
    public String getName()
    {
        return (this.name);
    }

    /** 
    * Setting a new name to the player
    * @param name The name of the player.
    */    
    public void setName(String name)
    {
        this.name = name;
    }    

    /** 
    * Returns the room where the player is.
    * @return The room where the player is.
    */    
    public Room getCurrentRoom()
    {
        return (this.currentRoom);
    }

    /** 
    * Setting the currente room where the player is.
    * @param currentRoom The room where the player is.
    */    
    public void setCurrentRoom(Room currentRoom)
    {
        this.currentRoom = currentRoom;
    }       

    /** 
    * To add a new item to belong to the player
    * @param newItem A item to be acquired by the player
    * @return TRUE if the item was acquired. FALSE if the item was not acquired.    
    */
    public boolean addItemToThePlayer(Item newItem)
    {
        if(newItem.isCarried()) {
            return(this.itemsBelongedToPlayer.add(newItem));            
        }
        else {
            return(false);
        }
    }

    /** 
    * Remove a item from the player.
    * @param newItem A item to be removed from the player
    * @return TRUE if the item was removed. FALSE if the item was not removed.
    */    
    public boolean removeItemFromThePlayer(Item newItem)
    {
        return(itemsBelongedToPlayer.remove(newItem));
    }

    /** 
    * Return a list of items with the player.
    * @return A list of the available items with the player. 
    */
    public ArrayList<Item> getItemsWithThePlayer() 
    {
        return (this.itemsBelongedToPlayer);  
    }    

    /** 
    * Return a String listing the items with the player,
    * For example, if the player has items like mask and gloves, this method 
    * should return a String containing: "mask gloves"
    * @return A description of the items belonging to the player.
    */
    public String getStringItemsWithThePlayer() 
    {
        String returnString = ""; 
       
        Iterator<Item> items = itemsBelongedToPlayer.iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            returnString += "\n * " + currentItem.getName() + " - " + currentItem.getDescription();
        }

        if(returnString.isEmpty()) {
            returnString = "Sorry " + this.getName() + ", there is not items with you :(";
        }
        else {
            returnString = "Good job " + this.getName() + "!\n Items collected by you: "+ returnString ; 
        }

        return returnString;  
    }    

    /** 
    * Evaluate if the item belongs to the player
    * @param nameOfItemToCheck A String name of item to be checked if it belongs to the player.
    * @return The object Item if it belongs to the player. Null if the item does not belong to the player..
    */    
    public Item evaluateItemWithThePlayer(String nameOfItemToCheck)
    {
        Iterator<Item> items = getItemsWithThePlayer().iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            if(currentItem.getName().equals(nameOfItemToCheck)) {
                return (currentItem);
            }
        }
        return (null);
    }    

    /** 
    * Return the Object of type Item in case player has it.
    * @param nameOfitemToCheck A String name of item to be checked if the player has it.
    * @return The object Item if the player has it. Null if the player does not have it.
    */    
    public Item getItemWithThePlayer(String nameOfitemToCheck)
    {
        Iterator<Item> items = this.getItemsWithThePlayer().iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            if(currentItem.getName().equals(nameOfitemToCheck)) {
                return (currentItem);
            }
        }
        return (null);
    }

    /** 
     * Pick up a item available in the room.
     * The player can carry this item thought the building if
     * it is a good item. Some items may kill the player in case
     * he/she does not use PPE.
     */
    public void take(final Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Take what?");
            return;
        }

        final String nameOfItem = command.getSecondWord();        
        Iterator<Item> itemsInTheRoom = this.getCurrentRoom().getItemsInTheRoom().iterator();

        while(itemsInTheRoom.hasNext()){   
            
            Item itemToBeCollected;
            itemToBeCollected = itemsInTheRoom.next();

            if(itemToBeCollected.getName().equals(nameOfItem)) {

                // transfering the item from the room to the player
                if (this.getCurrentRoom().removeItemFromTheRoom(itemToBeCollected)) {  

                    if(this.addItemToThePlayer(itemToBeCollected)) {
                        System.out.println("Well done " + this.getName() + "!");                        
                        System.out.println("Item " + itemToBeCollected.getName() + " collected.");
                        return; // stop the loop, item found and added successful!  
                    }
                    else {// somethis went wrong to add the item to player
                        System.out.println("Error to try to take the item \"" + itemToBeCollected.getName() + "\" from the room.");    
                        System.out.println("Hint: Check if the item \"" + itemToBeCollected.getName() + "\" can be carried or maybe openned.");                                          
                        this.getCurrentRoom().addItemInTheRoom(itemToBeCollected); // Roolback previous operation due some error                        
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
            System.out.println(this.getCurrentRoom().getItemsString());                        
        }
      
    }

    /** 
     * Drop a item available in the room.
     * The player can drop an item in the room in case
     * the room is not full.
     */
    public void drop(final Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what?");
            return;
        }

        final String nameOfItem = command.getSecondWord();
        Iterator<Item> items = this.getItemsWithThePlayer().iterator();

        while(items.hasNext()){   
            Item itemToBeDropped = items.next();
            if(itemToBeDropped.getName().equals(nameOfItem)) {

                // transfering the item from the player to the room
                if(this.getCurrentRoom().addItemInTheRoom(itemToBeDropped)) { // if the room accepts this item
                    if(this.removeItemFromThePlayer(itemToBeDropped)) {  // and if player can drop the item
                        System.out.print("Item " + itemToBeDropped.getName() + " dropped " );
                        System.out.println(this.getCurrentRoom().getDescription());
                        return;  // stop the loop, item found and dropped!                               
                    }
                    else { // somethis went wrong to player drop the item
                        System.out.print("Error to try to drop the item " + itemToBeDropped.getName() + " in the room " );
                        this.getCurrentRoom().removeItemFromTheRoom(itemToBeDropped); // rollback previous instruction
                        return;// stop the loop, item found but there is some inconsistent
                    }
                }
                else { // The room maybe is full
                    System.out.println("Error to try to drop the item " + itemToBeDropped.getName() + " in the room,");
                    System.out.println("Try \"look\" command and check if " + this.getCurrentRoom().getDescription() + " is full,");                    
                    System.out.println("or \"inventory\" command and check if you really have the item " + itemToBeDropped.getDescription());
                    return;// stop the loop, item found but there is some inconsistent
                }                           
            }
        }
        // The item asked is not with the player
        System.out.println("Sorry " + this.getName() + ", you do not have the item " + nameOfItem + " :(");
        System.out.println("Try \"inventory\" command and check what items do you have with you.");
    }   

    /** 
     * Open a item available in the room.
     * The player can open an item in the room in case
     * the item has some objects inside its.
     */
    public void open(final Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Open what?");
            return;
        }

        final String nameOfItemToOpen = command.getSecondWord();

        // Checking if the item to be open is in the room
        Item itemToBeOpenned = this.getCurrentRoom().evaluateItemInTheRoom(nameOfItemToOpen);

        if( itemToBeOpenned != null) { 

            // Item is in the room, then getting all the necessary items to open it.
            Iterator<Item> items = itemToBeOpenned.getItemNeededToOpenThis().iterator();
            while(items.hasNext()){   
                Item currentItem = items.next();

                // If the player do not have one of necessary item
                if ( this.evaluateItemWithThePlayer(currentItem.getName()) == null ) {
                    System.out.println("Sorry " + this.getName() + ", you do not have the item \"" + currentItem.getName() + "\" to open the " + itemToBeOpenned.getName() + " :(");                  
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
                if(this.addItemToThePlayer(itemToMove)) {  // give to player                
                    if (itemToBeOpenned.removeItemFromAnotherItem(itemToMove) ) { // removing from it
                        System.out.println("Congrats " + this.getName() + "!!!");                        
                        System.out.println("\"" + itemToBeOpenned.getName() + "\" openned and item " + itemToMove.getName() + " collected successfully!"); 
                        System.out.println("Hint: type \"inventory\" and check if you have all required items to win the game!" );                                          
                    } 
                    else { // in case something goes wrong, rollback the one part on operation
                        System.out.println("Sorry " + this.getName() + ", the item \"" + itemToMove.getName() + "\" could not be collected from " + itemToBeOpenned.getDescription() + " :(");
                        this.removeItemFromThePlayer(itemToMove); // rollback previous operation                       
                    }
                }
                else { // For some reason, this item can not be moved...
                    System.out.println("Sorry " + this.getName() + ", the item \"" + itemToMove.getName() + "\" could not be collect :(");
                    System.out.println("Hint: check if the item " + itemToBeOpenned.getDescription() + " can be carried or maybe openned.");                  
                }
            }
        }      
        else { // Item that was required to be openned is not in the room
            System.out.println("Sorry " + this.getName() + ", there is not item \"" + nameOfItemToOpen + "\" " + this.getCurrentRoom().getDescription() + " :(");
        }        
    }  
   
    /** 
     * Display where the play is and what items are in the room
     * After a sequence of commands the player may become lost,
     * "look" command will help the player remember where he is.
     */
    public void look()
    {
        System.out.println(this.getCurrentRoom().getLongDescription()); 
    }

    /** 
     * Print out all items owned by the player.
     */
    public void inventory() 
    {
        System.out.println(this.getStringItemsWithThePlayer());     
    }    

}
