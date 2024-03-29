/**
 * Class Room - a room in the building of "Salving the World from COVID-19" game.
 *
 * This class is part of the "Salving the World from COVID-19" application. 
 * "Salving the World from COVID-19" is a educative, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game. It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west, up and down.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author  Diego Bueno da Silva
 * @version 2020.05.04
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Room 
{
    final static int MAX_ITEM_PER_ROOM = 3;

    private String description; 
    private ArrayList<Item> listOfItemsInTheRoom;
    private ArrayList<Item> listOfCompulsoryItemsWithThePlayer;
    private HashMap<String, Room> exits;
    private boolean inside;

    /** 
     * Constructor creates a room described "description". Initially, it has
     * no exits. "description" is something like "the Reception" or
     * "Outside of the building".
     * @param description The room's description.
     */
    public Room(String description, boolean inside) 
    {
        this.description = description;
        listOfItemsInTheRoom = new ArrayList<Item>();
        listOfCompulsoryItemsWithThePlayer = new ArrayList<Item>();
        exits = new HashMap<String, Room>();
        this.inside = inside;
    }

    /** 
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /** 
     * Setting a new descrition to the room
     * @param description The new room's description.
     */
    public void setDescription(String description)
    {
         this.description = description;
    }

    /** 
    * Return the room that is reached if we go from this 
    * room in direction "direction " 
    * If there is no room in that direction, return null.
    */
    public Room getExit(String direction) 
    {
        return exits.get(direction); 
    }

    /** 
    * Setting a possible exit to this room.
    * @param direction The direction of the exit.
    * @param neighbor The room in the given direction. 
    */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor); 
    }
    
    /** 
    * Return a String listing the exits from the room.,
    * For example, if the room has exits to the north and west, this method 
    * should return a String containing: "north west"
    * @return A description of the available exits. 
    */
    private String getExitString() 
    {
        String returnString = "\nPossible exits:"; 
        Set<String> keys = exits.keySet(); 
        
        for(String exit : keys) {
            returnString += " " + exit; 
        }
        returnString += "\n";
        return returnString;  
    }

    /** 
    * Returns a String containing the description of the current room 
    * and a list of the exits of a room, of the form:
    *       You are in the kitchen.
    *       Exits: north west
    * @return A description of the room, including exits. 
    */    
    public String getLongDescription()
    {
        return "You are " + description + ". " + getExitString() + getItemsString();
    }

    /** 
    * To add a new item in this room
    * @param newItem A item to be addd in the room
    * @return TRUE if the item was added. FALSE if the item was not added.    
    */
    public boolean addItemInTheRoom(Item newItem)
    {
        if(listOfItemsInTheRoom.size() < MAX_ITEM_PER_ROOM ) {
            return(this.listOfItemsInTheRoom.add(newItem));            
        }
        else {
            return(false);
        }
    }

    /** 
    * Remove a item from the room.
    * @param newItem A item to be removed from the room
    * @return TRUE if the item was removed. FALSE if the item was not removed.
    */    
    public boolean removeItemFromTheRoom(Item newItem)
    {
        return(listOfItemsInTheRoom.remove(newItem));
    }

    /** 
    * Return a list of items in the room.
    * @return A list of the available items in the room. 
    */
    public ArrayList<Item> getItemsInTheRoom() 
    {
        return (this.listOfItemsInTheRoom);  
    }    

    /** 
    * Return a String listing the items in the room.,
    * For example, if the room has items like mask and gloves, this method 
    * should return a String containing: "mask gloves"
    * @return A description of the available items. 
    */
    public String getItemsString() 
    {
        String returnString = ""; 
       
        Iterator<Item> items = listOfItemsInTheRoom.iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            returnString += "\n * " + currentItem.getName() + " - " + currentItem.getDescription();
        }

        if(returnString.isEmpty()) {
            returnString = "There is not items available " + this.getDescription();
        }
        else {
            returnString = "Items available " + this.getDescription() + returnString ; 
        }

        return returnString;  
    }    

    /** 
    * To add a new Compulsory PPE to be used when gettin in this room
    * @param description A compulsory Personal Protective Equipment to be used when gettin in this room
    */
    public void addCompulsoryItemsWithThePlayer(Item newItem)
    {
        if(newItem.isCarried()) {
            this.listOfCompulsoryItemsWithThePlayer.add(newItem);
        }
        else {
        }
    }

    /** 
    * Return a list of Compulsory Personal Protective Equipment to access the room.
    * @return A list of Compulsory Personal Protective Equipment necessary to access this room safely. */
    public ArrayList<Item> getListOfCompulsoryItemsWithThePlayer()
    {
        return(listOfCompulsoryItemsWithThePlayer);
    }
    
    /* 
    * Return a String listing the items compusory the get in the room.
    * For example, if the room obligates the play to take mask 
    * and gloves, this method should return a String containing: 
    * "mask" "gloves"
    * @return A description of the items inside another item. 
    */
    public String getStringOfCompulsoryItemsWithThePlayer() 
    {
        String returnString = ""; 
       
        Iterator<Item> items = this.getListOfCompulsoryItemsWithThePlayer().iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            returnString += " * " + currentItem.getName() + " - " + currentItem.getDescription() + "\n";
        }

        if(returnString.isEmpty()) {
            returnString = "There is not items compulsory for " + this.getDescription();
        }

        return returnString;  
    }      

    /* 
    * Return a TRUE if the player can get in the room.
    * For example, if the room obligates the play to take mask 
    * and gloves, this method checks if the player has taken mask 
    * and gloves before getting the room.
    * @return True if the player can access the room, false otherwise. 
    */
    public boolean evaluateIfPlayerCanGetInTheRoom(Player player) 
    {
       
        Iterator<Item> items = this.getListOfCompulsoryItemsWithThePlayer().iterator();
        while(items.hasNext()){   
            
            // checking if the player has each compulsory item
            if ( player.evaluateItemWithThePlayer(items.next().getName()) == null ) {
                return( false );
            }
        }
        return true;  
    }     

    /** 
    * Evaluate if the item is in the room.
    * @param nameOfitemToCheck A String name of item to be checked if it is in the room
    * @return The object Item if its is in the room. Null if the item is not in the room.
    */    
    public Item evaluateItemInTheRoom(String nameOfitemToCheck)
    {
        Iterator<Item> items = this.getItemsInTheRoom().iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            if(currentItem.getName().equals(nameOfitemToCheck)) {
                return (currentItem);
            }
        }
        return (null);
    }

    /** 
     * @return True if the room is inside of the building.
     */
    public boolean isInsideOfTheBuilding()
    {
        return this.inside;
    }

    /** 
     * Setting if the room is inside of the building or not.
     * @param description True if the room is inside, otherwise false.
     */
    public void setInside(boolean inside)
    {
         this.inside = inside;
    }

}
