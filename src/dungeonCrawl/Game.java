/**
 * ---------------------------------------------------------------------------
 * File name: Game.java
 * Project name: Project 5
 * ---------------------------------------------------------------------------
 * Creator's name and email: Casey Edwards, zcee10@goldmail.etsu.edu
 * Course:  CSCI 1260-077
 * Creation Date: Apr 13, 2016
 * ---------------------------------------------------------------------------
 */

package dungeonCrawl;

import java.util.Random;

/**
 * Controls the components of the game.
 * Holds the Player and the Dungeon of the game, and has methods for interacting
 * with the dungeon to advance gameplay.
 *
 * <hr>
 * Date created: Apr 13, 2016
 * <hr>
 * @author Casey Edwards
 */
public class Game
{
	private Dungeon dungeon;
	private Player player;
	
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Apr 13, 2016 
	 *
	 * 
	 */
	public Game()
	{
		setDungeon(new Dungeon());
		setPlayer(new Player());
	}

	
	/**
	 * Getter for dungeon
	 *
	 * <hr>
	 * Date Created: Apr 13, 2016
	 *
	 * <hr>
	 * @return dungeon
	 */
	public Dungeon getDungeon()
	{
		return dungeon;
	}

	
	/**
	 * Setter for dungeon.
	 *
	 * <hr>
	 * Date Created: Apr 13, 2016
	 * 
	 * <hr>
	 * @param dungeon The dungeon.
	 */
	public void setDungeon(Dungeon dungeon)
	{
		this.dungeon = dungeon;
	}

	
	/**
	 * Getter for player
	 *
	 * <hr>
	 * Date Created: Apr 13, 2016
	 *
	 * <hr>
	 * @return player
	 */
	public Player getPlayer()
	{
		return player;
	}

	
	/**
	 * Setter for player.
	 *
	 * <hr>
	 * Date Created: Apr 13, 2016
	 * 
	 * <hr>
	 * @param player The player.
	 */
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	/**
	 * Attempts to move the player in the given direction.         
	 *
	 * <hr>
	 * Date created: Apr 13, 2016
	 *
	 * <hr>
	 * @param direction The direction to move.
	 * @return True for success, false for failure.
	 */
	public boolean movePlayer(Direction direction)
	{
		return dungeon.movePlayer(direction);
	}
	
	/**
	 * Determines if the player has won the game.
	 * If the player has exited the dungeon, his/her column coordinate
	 * will be equal to the size of any row.          
	 *
	 * <hr>
	 * Date created: Apr 13, 2016
	 *
	 * <hr>
	 * @return True for victory, otherwise false.
	 */
	public boolean checkForWin()
	{
		boolean playerWins = false;  // Win flag
		int winColumn = dungeon.getRooms().get(0).size(); // Column number to reach for win condition.
		
		// If the player's column position matches the win column, set the flag to true.
		if (dungeon.getPlayerposition()[1] == winColumn)
			playerWins = true;
		
		return playerWins;
	}
	
	/**
	 * This method handles the player interaction with a room.
	 * It will first display a description of the room and the location of exits, then
	 * checks for any weapons in the room, then enters combat with the enemy (if present).
	 * Returns a String representation of the entire encounter.         
	 *
	 * <hr>
	 * Date created: Apr 13, 2016
	 *
	 * <hr>
	 * @return String representation of the encounter.
	 */
	public String roomEncounter()
	{
		Room currentRoom = dungeon.getCurrentRoom(); // Room currently occupied by the player.
		String encounter = ""; // Description of the player's actions upon entering the room.
		
		// Describe the room and its exits.
		encounter += describeRoom(currentRoom);
		
		// If a weapon exists, examine it.
		if (currentRoom.getWeapon() != null)
			encounter += examineWeapon(currentRoom);
		
		// If an enemy exists, fight it.
		if (currentRoom.getEnemy() != null)
			encounter += battle(currentRoom);
		
		// Return the encounter description.
		return encounter;
	}
	
	/**
	 * Returns a String description of the given room.
	 * Randomly generates a description of the surroundings, then lists
	 * the exits.         
	 *
	 * <hr>
	 * Date created: Apr 13, 2016
	 *
	 * <hr>
	 * @param currentRoom Room to be descried.
	 * @return String description of the room.
	 */
	public String describeRoom(Room currentRoom)
	{
		String description = ""; // Room description.
		Random rand = new Random(); // Random number generator.
		
		// Generate a random room description
		switch (rand.nextInt(4))
		{
			case 0:
				description += "\n\nThe walls are cracked and dry. The ancient dust tickles your nose.";
				break;
			case 1:
				description += "\n\nThe air around you is thick with moisture. You notice small\n"
							+ "trickles of water running down the walls.";
				break;
			case 2:
				description += "\n\nThe darkness of this room is almost suffocating. A small candle\n"
							+ "in the middle of the room is the only source of light.";
				break;
			case 3:
				description += "\n\nBones litter the floor. The smell of rotting flesh nearly\n"
							+ "overpowers you. Something has been here recently.";
		}
		
		description += "\n\tExits: ";
		
		// Iterate over the rooms exits and add them to the description.
		for (Direction exit : currentRoom.getExits())
			description += " " + exit.toString().toLowerCase();
		
		description += ".\n\n";
		
		return description;
	}
	
	/**
	 * Returns a description of a weapon found in a room.
	 * This method will compare the weapon with the player's weapon (if any),
	 * and adjust the equipment accordingly.         
	 *
	 * <hr>
	 * Date created: Apr 13, 2016
	 *
	 * <hr>
	 * @param currentRoom Room containing the weapon.
	 * @return String description of the transaction.
	 */
	public String examineWeapon(Room currentRoom)
	{
		String description = ""; // Weapon examination.
		Weapon weapon = currentRoom.getWeapon(); // Weapon in the room.
		
		// Declare the presence of the weapon.
		description += "You see an old " + weapon.getClass().getSimpleName()
					 + " lying on the ground.\n";
		
		// If player has no weapon, pick it up.
		if (player.getWeapon().getClass().getSimpleName() == "Unarmed")
		{
			description += "\tEcstatic to have something to defend yourself with, you pick it\n"
						+ "\tup and try it out against an old, decrepit barrel in the corner.\n\n";
			
			// Set the player's weapon, then set the room's to null.
			player.setWeapon(weapon);
			currentRoom.setWeapon(null);
		}
		// If player has a weapon, compare their damage bonuses.
		else if (weapon.getDamageBonus() > player.getWeapon().getDamageBonus())
		{
			description += "\tIt seems a bit stronger than what you are carrying. You drop\n"
						+  "\tyour " + player.getWeapon().getClass().getSimpleName() 
						+  " and pick up the new weapon. You feel tougher already!\n\n";
			
			// Set the player's weapon, then set the room's to null.
			player.setWeapon(weapon);
			currentRoom.setWeapon(null);
		}
		// Player won't be picking up the weapon.
		else
		{
			description += "\tIt looks like a silly toy compared to your " 
						+ player.getWeapon().getClass().getSimpleName() + ", and you decide to\n"
						+ "\tleave it for the next poor soul to travel these halls.\n\n";
		}
		
		return description;
	}
	
	/**
	 * Performs combat between an enemy and the player.
	 * Builds a String representation of the combat as it goes.
	 * Each combat round, the player and enemy take swings at each other
	 * until one or the other dies.         
	 *
	 * <hr>
	 * Date created: Apr 13, 2016
	 *
	 * <hr>
	 * @param currentRoom The combat room.
	 * @return String depiction of combat.
	 */
	public String battle(Room currentRoom)
	{
		String description = ""; // Description of the combat.
		Enemy enemy = currentRoom.getEnemy(); // Enemy for the battle.
		Random rand = new Random(); // Random number generator.
		
		// Describe the enemy approaching the player.
		description += "A terrifying " + enemy.getClass().getSimpleName() 
						+ " leaps out from the darkness! Its hungry eyes watch your every move.\n"
					+  "\tYou know these may be the last breaths you take, but you charge in for battle!\n";
		
		// Perform combat turns until one of the combatants run out of health.
		while (player.getCurrentHealth() > 0 && enemy.getCurrentHealth() > 0)
		{
			// Check for player hit
			if (rand.nextDouble() >= player.getMissPct())
			{
				// Hit! Deal damage.
				enemy.takeDamage(player.getAttackPower());
				description += "\n\tYou land a mighty blow upon your foe, dealing "
								+ player.getAttackPower() + " damage!\n";
			}
			else
			{
				// Miss!
				description += "\n\tYou strike at the beast, but miss!\n";
			}
			
			// Check for enemy hit
			if (rand.nextDouble() >= enemy.getMissPct())
			{
				// Hit! Deal damage to player.
				player.takeDamage(enemy.getStrength());
				description += "\t\tThe creature strikes you, dealing "
							+ enemy.getStrength() + " damage!\n";
			}
			else
			{
				// Miss!
				description += "\t\tThe monster tries to clumsily attack, but misses!\n";
			}
		}
		
		// Describe the losing party falling to the floor.
		if (player.getCurrentHealth() <= 0)
			description += "\nYou fall to the floor as darkness envelops you...";
		else
			description += "\nThe horrendous " + enemy.getClass().getSimpleName()
						+ " falls vanquished to the dungeon floor.\n\tYou are victorious!\n";
		
		// Check the body for potions.
		if (enemy.getHasPotion() && player.isAlive())
		{
			description += "\nYou notice a small potion vial on the corpse. What luck!\n"
							+ "\tOne potion added.";
			player.addPotion();
		}
		
		return description;
	}
}
