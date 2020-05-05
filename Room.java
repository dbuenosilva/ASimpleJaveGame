
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
import java.util.Set;

public class Room 
{
    private String description; 
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
        //exits = new HashMap<String, Room>();
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
        String returnString = "Exits:"; 
        Set<String> keys = exits.keySet(); 
        
        for(String exit : keys) {
            returnString += " " + exit; 
        }
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
        return "You are " + description + ".\n" + getExitString();
    }

    /** addCompulsoryPersonalProtectiveEquipment method 
    * To add a new Compulsory PPE to be used when gettin in this room
    * @param description A compulsory Personal Protective Equipment to be used when gettin in this room
    */
    public void addCompulsoryPersonalProtectiveEquipment(Item newItem)
    {
        if(newItem.getTypeOfItem().equals(TypeOfItem.PPE)) {
            this.listOfCompulsoryPersonalProtectiveEquipment.add(newItem);
        }
        else {
            System.out.println("Error to try add PPE to the List of Compulsory PPE:");
            System.out.println("\nThe item " + newItem.getDescription() + " is not PPE!" );
        }
    }

    /** getCompulsoryPersonalProtectiveEquipment method 
    * Return a list of Compulsory Personal Protective Equipment to access the room.
    * @return A list of Compulsory Personal Protective Equipment necessary to access this room safely. */
    public ArrayList<Item> getCompulsoryPersonalProtectiveEquipment()
    {
        return(listOfCompulsoryPersonalProtectiveEquipment);
    }

}
