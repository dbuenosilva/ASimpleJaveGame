/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "Salving the World from COVID-19" application. 
 * "Salving the World from COVID-19" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west, up and down.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @author  Diego Bueno da Silva
 * @version 2020.05.04
 */
public class Room 
{
    private String description; 
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room upExit;
    private Room downExit;

    // private HashMap<String, Room> exits;
    // private HashMap<String, Item> items;

    /** Room constructor
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        //exits = new HashMap<String, Room>();
        //items = new HashMap<String, Item>();
    }

    public Room getNorthExit() 
    {
        return(this.northExit);
    }
    public Room getSouthExit()
    {
        return(this.southExit);
    }
    public Room getEastExit()
    {
        return(this.eastExit);
    }
    public Room getWestExit()
    {
        return(this.westExit);
    }
    public Room getUpExit()
    {
        return(this.upExit);
    }
    public Room getDownExit()
    {
        return(this.downExit);
    }


    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param up The up exit.
     * @param donw The down exit
     */


/**
* Define an exit from this room.
* @param direction The direction of the exit.
* @param neighbor The room in the given direction. */
//public void setExit(String direction, Room neighbor)
//{
//exits.put(direction, neighbor); 
//}


/**
* Define an exit from this room.
* @param ??? The direction of the exit.
* @param item The item saved inside the room. */
//public void setItem(String direction, Item item)
//{
//exits.put(direction, item); 
//}




public void setExits(Room north, Room east, Room south, Room west, Room up, Room down) 
    {

/*
if(north != null) {
    exits.put("north", north); 
}
if(east != null) {
    exits.put("east", east); 
}
if(south != null) {
    exits.put("south", south);
}
if(west != null) {
    exits.put("west", west);
}
if(up != null) {
    exits.put("up", up);
}
if(up != null) {
    exits.put("up", up);
}

        */



        if(north != null) {
            northExit = north;
        }
        if(east != null) {
            eastExit = east;
        }
        if(south != null) {
            southExit = south;
        }
        if(west != null) {
            westExit = west;
        }
        if(up != null) {
            upExit = up;
        }        
        if(down != null) {
            downExit = down;
        }
    }

    /**
    * Return the room that is reached if we go from this * room in direction "direction "If there is no room in * that direction, return null.
    */
    //public Room getExit(String direction) 
    //{
    //    return exits.get(direction); 
    //}

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /** getExitString() 
    * Return a String listing the exits from the room.,
    * For example, if the room has exits to the north and west, this method 
    * should return a String containing: "north west"
    * @return A description of the available exits. */

    private String getExitString() 
    {
        String exitString = "Exits: "; 
        
        if(northExit != null) {
            exitString += "north ";         
        }
        
        if(eastExit != null) {
            exitString += "east ";
        }
        
        if(southExit != null) {
            exitString += "south ";
        }

        if(westExit != null) {
            exitString += "west ";
        }
    
        if(upExit != null) {
            exitString += "up ";
        }

        if(downExit != null) {
            exitString += "down ";
        }

        return exitString;
    }

    /** getLongDescription()
    * Returns a String containing the description of the current room 
    * and a list of the exits of a room, of the form:
    *       You are in the kitchen.
    *       Exits: north west
    * @return A description of the room, including exits. */
    
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }


/*

String returnString = "Exits:"; Set<String> keys = exits.keySet(); for(String exit : keys) {
returnString += " " + exit; }
return returnString;    
  }
*/

}
