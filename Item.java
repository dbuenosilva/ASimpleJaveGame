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

import java.util.ArrayList;
import java.util.Iterator;

public class Item
{
    private String name;
    private String description;
    private boolean itCanBeCarried;
    private ArrayList<Item> listOfItemsInsideThis;
    private ArrayList<Item> listOfItemsNeededtoOpenThis;    

    /** 
     * Constructor for objects of class Item which defines 
     * its name and descritipion.
     * By defaul, all item can be carried by the player
     * @param name The name of the item.
     * @param description The description of the item.
     */
    public Item(String name, String description)
    {
        this.name = name;
        this.description = description;
        this.itCanBeCarried = true;
        listOfItemsInsideThis = new ArrayList<Item>();
        listOfItemsNeededtoOpenThis = new ArrayList<Item>();       
    }

    /** 
    * Return a String description of the item,
    * @return A description of the item. 
    */
    public String getDescription()  
    {
        return(this.description);
    }

    /** 
    * To set a new description to the item
    * @param description The description to be set.
    */
    public void setDescription(String description)  
    {
        this.description = description;
    }
    
    /** 
    * Return the name  of the item
    * @return The name of the item. */

    public String getName()  
    {
        return(this.name);
    }

    /** 
    * To define the name of the item.
    * @param name The name of the item
    */
    public void setName(String name)  
    {
        this.name = name;
    }

    /** 
    * To set a if the item can be carried by the player or not
    * @param itCanBeCarried True if the item can be carried by the player, otherwise FALSE.
    */
    public void setItCanBeCarried(Boolean itCanBeCarried)  
    {
        this.itCanBeCarried = itCanBeCarried;
    }
    
    /** 
    * Return if the item can be carried by the player or not
    * @return True if the item can be carried by the player, otherwise FALSE. 
    */
    public boolean isCarried()  
    {
        return(this.itCanBeCarried);
    }   
    
    /** 
    * To add a new item inside of another item.
    * @param newItem A item to be add inside of another item.
    * @return TRUE if the item was added. FALSE if the item was not added.    
    */
    public boolean addItemInsideAnother(Item newItem)
    {
        return(this.listOfItemsInsideThis.add(newItem));            
    }

    /** 
    * Remove a item from the another item.
    * @param newItem A item to be removed from another item.
    * @return TRUE if the item was removed. FALSE if the item was not removed.
    */    
    public boolean removeItemFromAnotherItem(Item newItem)
    {
        return(this.listOfItemsInsideThis.remove(newItem));
    }

    /** 
    * Return a list of items inside of another item.
    * @return A list of the available items inside this item. 
    */
    public ArrayList<Item> getItemsInsideAnotherItem() 
    {
        return (this.listOfItemsInsideThis);  
    }    

    /** 
    * Return a String listing the items inside another item.,
    * For example, if the item has items like mask and vaccine, this method 
    * should return a String containing: "vaccine"
    * @return A description of the items inside another item. 
    */
    public String getItemsInsideString() 
    {
        String returnString = ""; 
       
        Iterator<Item> items = this.listOfItemsInsideThis.iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            returnString += "\n * " + currentItem.getName() + " - " + currentItem.getDescription();
        }

        if(returnString.isEmpty()) {
            returnString = "There is not items inside of " + this.getDescription();
        }
        else {
            returnString = "Items inside of " + this.getDescription() + returnString ; 
        }

        return returnString;  
    }      

    /** 
    * Return the item that is needed to open this item
    * if the name searched does not exist, will return null
    * @return The list of needed item to open the item.
    */
    public ArrayList<Item> getItemNeededToOpenThis() 
    {
        return (this.listOfItemsNeededtoOpenThis);  
    } 

    /** 
    * Adding a necessary item to open this.
    * @param item The needed item. 
    */
    public void addItemNeededToOpenThis(Item item)
    {
        listOfItemsNeededtoOpenThis.add(item); 
    }
    
    /** 
    * Return a String listing the needed items to open this,
    * For example, if the item is a safe that need items like key and password, this method 
    * should return a String containing: "key password"
    * @return A description of the needed items to open another item. 
    */
    public String getNeededItemsString() 
    {
        String returnString = ""; 
       
        Iterator<Item> items = this.listOfItemsNeededtoOpenThis.iterator();
        while(items.hasNext()){   
            Item currentItem = items.next();
            returnString += "\n * " + currentItem.getName() + " - " + currentItem.getDescription();
        }

        if(returnString.isEmpty()) {
            returnString = "It is not necessary item to open " + this.getDescription();
        }

        return returnString;    
    }
}
