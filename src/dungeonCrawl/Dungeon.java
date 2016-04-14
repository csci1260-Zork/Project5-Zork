/**
 * ---------------------------------------------------------------------------
 * File name: Dungeon.java
 * Project name: Project 5
 * ---------------------------------------------------------------------------
 * Creator's name and email: Casey Edwards, zcee10@goldmail.etsu.edu
 * Course:  CSCI 1260-077
 * Creation Date: Apr 9, 2016
 * ---------------------------------------------------------------------------
 */

package dungeonCrawl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Contains a 2D ArrayList of rooms that represent a dungeon.
 * The dungeon will have a minimum of 1 and a maximum of 5 rows,
 * and a minimum of 5 and a maximum of 10 columns. The exit
 * is chosen randomly from the east-most cells, and at least one room
 * is chosen to contain a weapon (possibly more).
 *
 * <hr>
 * Date created: Apr 9, 2016
 * <hr>
 * @author Casey Edwards
 */
public class Dungeon
{
	ArrayList<ArrayList<Room>> rooms;  // 2D list of the rooms in the dungeon.
	Player player;	// The player character.
	int[] playerPosition; // The player's position on the map.
	
	/**
	 * Constructor.
	 * Generates the rooms, chooses a random exit along the eastern
	 * border, and places a weapon in a randomly chosen non-starting
	 * room. Also creates the player character, setting their initial
	 * position to {0, 0}.
	 *
	 * <hr>
	 * Date created: Apr 10, 2016 
	 *
	 * 
	 */
	public Dungeon()
	{
		player = new Player();	// Create the player
		setPlayerPosition(new int[2]); // Initialize playerPosition array
		setPlayerPosition(0, 0); // Set the player to the starting room (0, 0)
		generateRooms(); // Create the floorplan.
	}

	
	/**
	 * Getter for rooms
	 *
	 * <hr>
	 * Date Created: Apr 9, 2016
	 *
	 * <hr>
	 * @return rooms
	 */
	public ArrayList <ArrayList <Room>> getRooms()
	{
		return rooms;
	}

	
	/**
	 * Setter for rooms.
	 *
	 * <hr>
	 * Date Created: Apr 9, 2016
	 * 
	 * <hr>
	 * @param rooms The rooms.
	 */
	public void setRooms(ArrayList <ArrayList <Room>> rooms)
	{
		this.rooms = rooms;
	}
	
	/**
	 * Getter for player         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @return The player
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	/**
	 * Setter for player.         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @param player
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	/**
	 * Getter for playerPosition         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @return
	 */
	public int[] getPlayerposition()
	{
		return playerPosition;
	}
	
	/**
	 * Setter for playerPosition         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @param position The player's position.
	 */
	public void setPlayerPosition(int[] position)
	{
		this.playerPosition = position;
	}
	
	/**
	 * Overloaded setter for placing the position using two int values.         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @param row The player's row.
	 * @param column The player's column.
	 */
	public void setPlayerPosition(int row, int column)
	{
		this.playerPosition[0] = row;
		this.playerPosition[1] = column;
	}
	
	/**
	 * Retrieves the room occupied by the player.         
	 *
	 * <hr>
	 * Date created: Apr 13, 2016
	 *
	 * <hr>
	 * @return Player's current room.
	 */
	public Room getCurrentRoom()
	{
		return rooms.get(playerPosition[0]).get(playerPosition[1]);
	}
	
	/**
	 * Generates the rooms of the dungeon.
	 * Randomly selects the number of rooms
	 * and places the weapons.
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 */
	private void generateRooms()
	{
		ArrayList<ArrayList<Room>> newRooms = new ArrayList<ArrayList<Room>>(); // New dungeon placeholder.
		
		// Generate rows and columns.
		createRowsAndColumns(newRooms);
		
		// Trim outside exits.
		trimExits(newRooms);
		
		// Place the weapons.
		placeWeapons(newRooms);
		
		// Set the completed dungeon.
		setRooms(newRooms);
	}
	
	/**
	 * Creates and sets the rows in the dungeon with a number
	 * of random rooms per row (5-10).         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @param dungeon The dungeon to fill.
	 */
	private void createRowsAndColumns(ArrayList<ArrayList<Room>> dungeon)
	{
		Random rand = new Random(); // Random number generator.
		int numRows = rand.nextInt(5) + 1; // 1 through 5 rows.
		int numColumns = rand.nextInt(6) + 5; // 5 through 10 columns.
		ArrayList<Room> tempRow; // Temporary placeholder for each row being created.
		int i, j; // Loop counters
		
		// Create the rows, and add them to the dungeon.
		for (i = 0; i < numRows; i++)
		{
			// Create a new ArrayList of Room objects
			tempRow = new ArrayList<Room>(numColumns);
			
			// Populate with numColumns rooms.
			for (j = 0; j < numColumns; j++)
				tempRow.add(new Room());
			
			// Add the row to the dungeon
			dungeon.add(tempRow);
		} // end for
	}
	
	/**
	 * Trims the exits to the outside of the dungeon.
	 * Also sets one exit as the "win condition" exit.         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @param dungeon The dungeon to trim.
	 */
	private void trimExits(ArrayList<ArrayList<Room>> dungeon)
	{
		Random rand = new Random(); // Random number generator.
		final int NUM_ROWS = dungeon.size(),	// Number of rows.
				  NUM_COLUMNS = dungeon.get(0).size(); // Number of columns.
		
		// Remove NORTH exits from topmost rooms.
		for (Room r : dungeon.get(0))
			r.removeExit(Direction.NORTH);
		
		// Remove SOUTH exits from the southern rooms.
		for (Room r : dungeon.get(NUM_ROWS - 1))
			r.removeExit(Direction.SOUTH);
		
		// Remove WEST exits from leftmost rooms.
		for (ArrayList<Room> row : dungeon)
			row.get(0).removeExit(Direction.WEST);
		
		// Remove EAST exits from rightmost rooms.
		for (ArrayList<Room> row : dungeon)
			row.get(NUM_COLUMNS - 1).removeExit(Direction.EAST);
		
		// Add an EAST exit to the rightmost room in the row randomly 
		// selected to be the "Dungeon Exit" row.
		dungeon.get(rand.nextInt(NUM_ROWS)).get(NUM_COLUMNS - 1).addExit(Direction.EAST);
	}
	
	/**
	 * Places weapons randomly throughout the dungeon.
	 * Places one minimum, and an extra weapon per 15 rooms in the dungeon.         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @param dungeon
	 */
	private void placeWeapons(ArrayList<ArrayList<Room>> dungeon)
	{
		int numRows = dungeon.size(), // Number of rows.
		    numColumns = dungeon.get(0).size(), // Number of columns
			i; // Loop counter
		final int NUM_WEAPONS = ((numRows * numColumns) / 15) + 1; // Number of weapons to place.
		int[] weaponRoom = {0, 0}; // Room where the weapon will be placed.
		Random rand = new Random(); // Random number generator.
		Stack<Weapon> weapons = new Stack<Weapon>(); // Stack of the possible weapons.
		 
		// Populate the stack of possible weapons.
		weapons.add(new Stick());
		weapons.add(new Sword());
		weapons.add(new Polearm());
		weapons.add(new Hammer());
		weapons.add(new Atlatl());
		
		// Shuffle the weapons stack.
		Collections.shuffle(weapons);
		
		// Place unique weapons until the number of required weapons has been reached.
		for (i = 0; i < NUM_WEAPONS; i++)
		{
			// Choose a non-starting room (0, 0) to place a weapon.
			while (weaponRoom[0] == 0 && weaponRoom[1] == 0)
			{
				weaponRoom[0] = rand.nextInt(numRows);
				weaponRoom[1] = rand.nextInt(numColumns);
				
				// If room already has a weapon, keep selecting for a room.
				if (dungeon.get(weaponRoom[0]).get(weaponRoom[1]).getWeapon() != null)
				{
					weaponRoom[0] = 0;
					weaponRoom[1] = 0;
				}
			} // end while
			
			// Fill the chosen room with a weapon from the stack.
			dungeon.get(weaponRoom[0]).get(weaponRoom[1]).setWeapon(weapons.pop());
			
			// Reset the weaponRoom indices.
			weaponRoom[0] = 0;
			weaponRoom[1] = 0;
			
		} // end for
	}
	
	/**
	 * Moves the player position to the specified direction.
	 * Returns true if the operation is successful, false otherwise.        
	 *
	 * <hr>
	 * Date created: Apr 12, 2016
	 *
	 * <hr>
	 * @param direction Direction to move player.
	 * @return True or False for success/failure.
	 */
	public boolean movePlayer(Direction direction)
	{
		boolean success = false;  // Flags true if the operation is successful.
		
		// Check to see if the room the player is occupying contains the correct
		// exit. If so, adjust the player position and set the success flag to true.
		if (rooms.get(playerPosition[0]).get(playerPosition[1]).getExits().contains(direction))
		{
			// Flag success to true
			success = true;
			
			// Adjust the position based on the direction of travel.
			switch (direction)
			{
				case EAST: // Increment the column
					playerPosition[1]++;
					break;
				case WEST: // Decrement the column
					playerPosition[1]--;
					break;
				case NORTH: // Decrement the row
					playerPosition[0]--;
					break;
				case SOUTH: // Increment the row
					playerPosition[0]++;
					break;
			} // end switch
		}// end if
		
		return success;
	}
	
	/**
	 * Returns a String representation of the state of the dungeon.         
	 *
	 * <hr>
	 * Date created: Apr 12, 2016 
	 *
	 * <hr>
	 * @return The dungeon's string.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String mapString = ""; // The string representation.
		int y = 0;	// Tracks the current row.
		int x = 0;  // Tracks the current column. These two variables are
					// used to track the player's location on the map.
		
		// Iterate over the rows.
		for (ArrayList<Room> row : rooms)
		{
			// Set x to zero at the start of every row.
			x = 0;
			
			// Iterate over each room in the row.
			for (Room r : row)
			{
				mapString += "|_"; // The first part of each room.
				
				// If the row and column match the player's position, mark
				// it with a 'P', otherwise mark the floor.
				if (playerPosition[0] == y && playerPosition[1] == x)
					mapString += "P";
				else 
					mapString += "_";
				
				// If the room has an enemy, mark it with the enemy's initial.
				// Otherwise mark the floor.
				if (r.getEnemy() != null)
				{
					switch(r.getEnemy().getClass().getSimpleName())
					{
						case "Boglin": mapString += "B";
						break;
						case "Squilderdash": mapString += "S";
						break;
						case "Ragdon": mapString += "R";
						break;
					} // end switch
				} // end if
				else 
					mapString += "_";
				
				// If the room has a weapon, mark it with the weapon code.
				// Otherwise, mark the floor.
				if (r.getWeapon() != null)
				{
					switch(r.getWeapon().getClass().getSimpleName())
					{
						case "Stick": mapString += "T";
						break;
						case "Sword": mapString += "W";
						break;
						case "Polearm": mapString += "P";
						break;
						case "Hammer": mapString += "H";
						break;
						case "Atlatl": mapString += "A";
					} // end switch
				} // end if
				else 
					mapString += "_";
				
				
				mapString += "_|"; // The end of each room
				x++; // Iterate the column counter.
			} // end for (columns)
			mapString += "\n"; // Next row goes on a new line.
			y++; // Iterate the row counter
		} // end for (rows)
		
		return mapString;
	}
}
