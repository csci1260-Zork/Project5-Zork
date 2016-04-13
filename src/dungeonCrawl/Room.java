/**
 * ---------------------------------------------------------------------------
 * File name: Room.java
 * Project name: Project 5
 * ---------------------------------------------------------------------------
 * Creator's name and email: Casey Edwards, zcee10@goldmail.etsu.edu
 * Course:  CSCI 1260-077
 * Creation Date: Apr 9, 2016
 * ---------------------------------------------------------------------------
 */

package dungeonCrawl;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Room in a dungeon.
 * Has a 50% chance of containing a monster, a weapon (if chosen to
 * contain one), and a list of exits.
 *
 * <hr>
 * Date created: Apr 9, 2016
 * <hr>
 * @author Casey Edwards
 */
public class Room
{
	ArrayList<Direction> exits;  // List of exits
	Enemy enemy;				 // Enemy within, if any
	Weapon weapon;				 // Weapon within, if any
	
	
	/**
	 * Default constructor.
	 * Has a 50% chance of populating the room with an enemy.        
	 *
	 * <hr>
	 * Date created: Apr 9, 2016 
	 *
	 * 
	 */
	public Room()
	{
		Random rand = new Random(); // Random number generator.
		
		// Determine if the room holds an enemy.
		if (rand.nextInt(100) >= 50) // 50% chance
		{
			// Room has an enemy, determine monster type with a random roll.
			switch (rand.nextInt(3))
			{
				case 0: // Create a Boglin
					setEnemy(new Boglin());
					break;
				case 1: // Create a Squilderdash
					setEnemy(new Squilderdash());
					break;
				case 2: // Create a Ragdon
					setEnemy(new Ragdon());
					break;
			} // end switch
		} // end if
		
		// Create the exits ArrayList.
		exits = new ArrayList<Direction>();
		
		// Set all exits open, and weapon to null.
		for (Direction d : Direction.values())
			addExit(d);
		
		setWeapon(null);
	}
	
	
	/**
	 * Parameterized constructor.
	 * Has a 50% chance of populating the room with an enemy, and sets 
	 * the exits using the passed in ArrayList of Directions.        
	 *
	 * <hr>
	 * Date created: Apr 9, 2016 
	 *
	 * 
	 * @param exits Exits of the room.
	 */
	public Room(ArrayList<Direction> exits)
	{
		Random rand = new Random(); // Random number generator.
		
		// Determine if the room holds an enemy.
		if (rand.nextInt(100) >= 50) // 50% chance
		{
			// Room has an enemy, determine monster type with a random roll.
			switch (rand.nextInt(3))
			{
				case 0: // Create a Boglin
					setEnemy(new Boglin());
					break;
				case 1: // Create a Squilderdash
					setEnemy(new Squilderdash());
					break;
				case 2: // Create a Ragdon
					setEnemy(new Ragdon());
					break;
			} // end switch
		} // end if
		
		// Set the exits. Set weapon to null.
		setExits(exits);
		setWeapon(null);
	}

	
	/**
	 * Getter for exits
	 *
	 * <hr>
	 * Date Created: Apr 9, 2016
	 *
	 * <hr>
	 * @return exits
	 */
	public ArrayList <Direction> getExits()
	{
		return exits;
	}

	
	/**
	 * Setter for exits.
	 *
	 * <hr>
	 * Date Created: Apr 9, 2016
	 * 
	 * <hr>
	 * @param exits The exits.
	 */
	public void setExits(ArrayList <Direction> exits)
	{
		this.exits = exits;
	}

	
	/**
	 * Getter for enemy
	 *
	 * <hr>
	 * Date Created: Apr 9, 2016
	 *
	 * <hr>
	 * @return enemy
	 */
	public Enemy getEnemy()
	{
		return enemy;
	}

	
	/**
	 * Setter for enemy.
	 *
	 * <hr>
	 * Date Created: Apr 9, 2016
	 * 
	 * <hr>
	 * @param enemy The enemy.
	 */
	public void setEnemy(Enemy enemy)
	{
		this.enemy = enemy;
	}

	
	/**
	 * Getter for weapon
	 *
	 * <hr>
	 * Date Created: Apr 9, 2016
	 *
	 * <hr>
	 * @return weapon
	 */
	public Weapon getWeapon()
	{
		return weapon;
	}

	
	/**
	 * Setter for weapon.
	 *
	 * <hr>
	 * Date Created: Apr 9, 2016
	 * 
	 * <hr>
	 * @param weapon The weapon.
	 */
	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}
	
	/**
	 * Adds an exit to the list. If the exit already exists, do nothing.         
	 *
	 * <hr>
	 * Date created: Apr 9, 2016
	 *
	 * <hr>
	 * @param exit The new exit.
	 */
	public void addExit(Direction exit)
	{
		// If the list of exits doesn't contain the passed exit,
		// add it to the list. Otherwise do nothing.
		if (!exits.contains(exit))
			exits.add(exit);
	}
	
	/**
	 * Removes an exit from the list. If the exit doesn't exist, do nothing.         
	 *
	 * <hr>
	 * Date created: Apr 10, 2016
	 *
	 * <hr>
	 * @param exit The exit to remove.
	 */
	public void removeExit(Direction exit)
	{
		// If the exit exists in the room, remove it.
		if (exits.contains(exit))
			exits.remove(exit);
	}
	
	/**
	 * Informs the caller whether the room contains the specified exit.
	 * Returns true if so, false if it does not exist.         
	 *
	 * <hr>
	 * Date created: Apr 9, 2016
	 *
	 * <hr>
	 * @param exit The exit to look for.
	 * @return True or false, whether the exit exists.
	 */
	public boolean hasExit(Direction exit)
	{
		// Returns true if the exit exists in the room, false if not.
		return exits.contains(exit);
	}
	
}
