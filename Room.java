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
    final static int MAX_ITEM_PER_ROOM = 1;

    private String description; 
    private ArrayList<Item> listOfItemsInTheRoom;
    private ArrayList<Item> listOfCompulsoryPersonalProtectiveEquipment;
    private HashMap<String, Room> exits;

    /** Room constructor
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "the Reception" or
     * "Outside of the building".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        listOfItemsInTheRoom = new ArrayList<Item>();
        listOfCompulsoryPersonalProtectiveEquipment = new ArrayList<Item>();
        exits = new HashMap<String, Room>();

    }

    /** getDescription method
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /** setDescription method
     * Setting a new descrition to the room
     * @param description The new room's description.
     */
    public void setDescription(String description)
    {
         this.description = description;
    }

    /** getExit method
    * Return the room that is reached if we go from this 
    * room in direction "direction " 
    * If there is no room in that direction, return null.
    */
    public Room getExit(String direction) 
    {
        return exits.get(direction); 
    }

    /** setExit method
    * Setting a possible exit to this room.
    * @param direction The direction of the exit.
    * @param neighbor The room in the given direction. 
    */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor); 
    }
    
    /** getExitString method
    * Return a String listing the exits from the room.,
    * For example, if the room has exits to the north and west, this method 
    * should return a String containing: "north west"
    * @return A description of the available exits. 
    */
    private String getExitString() 
    {
        String returnString = "Possible exits:"; 
        Set<String> keys = exits.keySet(); 
        
        for(String exit : keys) {
            returnString += " " + exit; 
        }
        returnString += "\n\n";
        return returnString;  
    }

    /** getLongDescription method
    * Returns a String containing the description of the current room 
    * and a list of the exits of a room, of the form:
    *       You are in the kitchen.
    *       Exits: north west
    * @return A description of the room, including exits. 
    */    
    public String getLongDescription()
    {
        return "\nYou are " + description + ".\n" + getExitString() + getItemsString();
    }

    /** addItemInTheRoom method 
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

    /** removeItemFromTheRoom method 
    * Remove a item from the room.
    * @param newItem A item to be removed from the room
    * @return TRUE if the item was removed. FALSE if the item was not removed.
    */    
    public boolean removeItemFromTheRoom(Item newItem)
    {
        return(listOfItemsInTheRoom.remove(newItem));
    }

    /** getItemsInTheRoom method
    * Return a list of items in the room.
    * @return A list of the available items in the room. 
    */
    public ArrayList<Item> getItemsInTheRoom() 
    {
        return (this.listOfItemsInTheRoom);  
    }    

    /** getItemsString method
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

    /** addCompulsoryPersonalProtectiveEquipment method 
    * To add a new Compulsory PPE to be used when gettin in this room
    * @param description A compulsory Personal Protective Equipment to be used when gettin in this room
    */
    public void addCompulsoryPersonalProtectiveEquipment(Item newItem)
    {
        if(newItem.isCarried()) {
            this.listOfCompulsoryPersonalProtectiveEquipment.add(newItem);
        }
        else {
        }
    }

    /** getCompulsoryPersonalProtectiveEquipment method 
    * Return a list of Compulsory Personal Protective Equipment to access the room.
    * @return A list of Compulsory Personal Protective Equipment necessary to access this room safely. */
    public ArrayList<Item> getCompulsoryPersonalProtectiveEquipment()
    {
        return(listOfCompulsoryPersonalProtectiveEquipment);
    }

    /** evaluateItemInTheRoom method
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



}
