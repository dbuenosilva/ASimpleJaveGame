/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        "drop","go","help","inventory","open","quit","take","where"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /** getCommandsString method
    * Return a string with all valid commands in order to orient the player 
    * @return A description of the available commands. 
    */
   public String getCommandsString()
   {
       String returnString = "";

       for(String command : validCommands) { 
            returnString += "> " + command + "\n";
       }
       if(returnString.isEmpty()) {
           returnString = "There is not commands available";
       }
       else {
           returnString = "The commands that you can use are: \n\n"  + returnString ; 
       }

       return returnString;   
    }
}
