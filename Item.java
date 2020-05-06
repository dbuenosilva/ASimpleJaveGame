
/**
* Class Item - a item in an "Saving the World from COVID-19" game.
 *
 * This class is part of the "Salving the World from COVID-19" application. 
 * "Salving the World from COVID-19" is a educative, text based adventure game.  
 *
 * A "Item" represents one object necessary to win the game.  It also can 
 * be a item that kill you in case you pick it up. A item can be at one
 * location simutaneosly. Also, a item can be a PPE (Personal Protective Equipment)
 *
 * @author  Diego Bueno da Silva
 * @version 2020.05.04
 */
public class Item
{
    private String name;
    private String description;
    private TypeOfItem type;

    /** Item Constructor
     * Constructor for objects of class Item which defines 
     * its name and descritipion.
     * @param name The name of the item.
     * @param description The description of the item.
     */
    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

   /** Item Constructor
     * Constructor for objects of class Item which defines 
     * its name, descritipion and also its type.
     * @param name The name of the item.
     * @param description The description of the item.
     * @param type The description of the item. 
     */
    public Item(String name, String description, TypeOfItem type)
    {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    /** getDescription method 
    * Return a String description of the item,
    * @return A description of the item. 
    */
    public String getDescription()  
    {
        return(this.description);
    }

    /** setDescription method 
    * To set a new description to the item
    * @param description The description to be set.
    */
    public void setDescription(String description)  
    {
        this.description = description;
    }
    
    /** getName method 
    * Return the name  of the item
    * @return The name of the item. */

    public String getName()  
    {
        return(this.name);
    }

    /** setName method 
    * To define the name of the item.
    * @param name The name of the item
    */
    public void setName(String name)  
    {
        this.name = name;
    }

    /** getTypeOfItem method 
    * Return the type Of the Item
    * @return type The type of the item 
    */
    public TypeOfItem getTypeOfItem()  
    {
        return(this.type);
    }

    /** setTypeOfItem method 
    * To set the type of the item
    * @param setTypeOfItem Define the type of the item like PPE, VACCINE and so on. 
    */
    public void setTypeOfItem(TypeOfItem type)  
    {
        this.type = type;
    }
}
