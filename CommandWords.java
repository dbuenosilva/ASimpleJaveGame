/**
 * This class is part of the the "Salving the World from COVID-19" application.
 * "Salving the World from COVID-19" is a educative, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game. It is
 * used to recognise commands as they are typed in.
 *
 * @author Michael Kölling and David J. Barnes
 * @author Diego Bueno da Silva
 * @version 2020.05.04
 */

import java.util.HashMap;

public class CommandWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, CommandWord> validCommands;

    /** 
     * Constructor initialises the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /** 
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /** 
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /** 
     * Print all valid commands to System.out.
     */
    public String showAll() 
    {
        String commandsString = "Valid commands: ";
        for(String command : validCommands.keySet()) {
            commandsString += command + "  ";
        }
        return(commandsString);
    }
}
