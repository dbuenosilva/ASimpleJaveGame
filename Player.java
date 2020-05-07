
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

    /**
     * Constructor for objects of class Player
     * Initialise with a name and a empty list of items
     */
    public Player(String name)
    {
        this.name = name;
        itemsBelongedToPlayer = new ArrayList<Item>();
    }

    /** getName method
    * Returns a String containing the name of the player
    * @return The name of the player.
    */    
    public String getName()
    {
        return (this.name);
    }

    /** setName method
    * Setting a new name to the player
    * @param name The name of the player.
    */    
    public void setName(String name)
    {
        this.name = name;
    }    

    /** addItemToThePlayer method 
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

    /** removeItemFromThePlayer method 
    * Remove a item from the player.
    * @param newItem A item to be removed from the player
    * @return TRUE if the item was removed. FALSE if the item was not removed.
    */    
    public boolean removeItemFromThePlayer(Item newItem)
    {
        return(itemsBelongedToPlayer.remove(newItem));
    }

    /** getItemsWithThePlayer method
    * Return a list of items with the player.
    * @return A list of the available items with the player. 
    */
    public ArrayList<Item> getItemsWithThePlayer() 
    {
        return (this.itemsBelongedToPlayer);  
    }    

    /** getStringItemsWithThePlayer method
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
            returnString = "Good job " + this.getName() + "!\n Items collected by you \n"+ returnString ; 
        }

        return returnString;  
    }    

    /** evaluateItemWithThePlayer method
    * Evaluate if the item belongs to the player
    * @param nameOfItemToCheck A String name of item to be checked if it belongs to the player.
    * @return The object Item if it belongs to the player. Null if the item does not belong to the player..
    */    
    public Item evaluateItemWithThePlayer(String nameOfItemToCheck)
    {
        Iterator<Item> items = getItemsWithThePlayer().iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            if(currentItem.getDescription().equals(nameOfItemToCheck)) {
                return (currentItem);
            }
        }
        return (null);
    }    
}
