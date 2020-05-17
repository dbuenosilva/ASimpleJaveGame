/**
 * Representations for all the valid command words for
 * the "Salving the World from COVID-19" application.
 * @author  Diego Bueno da Silva
 * @version 2020.05.04
*/
public enum CommandWord 
{
    // A value for each command word along with its
    // corresponding user interface string.
    DROP("drop"), GO("go"), HELP("help"), INVENTORY("inventory"), LOOK("look"), 
    MAP("map"), OPEN("open"), QUIT("quit"), TAKE("take"), UNKNOWN("?");

    // The command string.
    private String commandString;

    /** 
    * Initialize with the corresponding command string. 
    * @param commandString The command string.
    */
    CommandWord(String commandString)
    {
        this.commandString = commandString; 
    }

    /** 
    * @return The command word as a string. 
    */
    public String toString() 
    {
        return commandString; 
    }    
}