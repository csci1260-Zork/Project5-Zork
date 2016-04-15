/**
 * ---------------------------------------------------------------------------
 * File name: Player.java
 * Project name: Project5-Zork
 * ---------------------------------------------------------------------------
 * Creator's name and email: Matthew Moore, zmjm40@goldmail.etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Apr 13, 2016
 * ---------------------------------------------------------------------------
 */

package dungeonCrawl;


/**
 * Allows creation of a player object and management of player specific attributes.
 *
 * <hr>
 * Date created: Apr 13, 2016
 * <hr>
 * @author Matthew Moore
 */
public class Player extends Participants
{
	//Weapon adds to the player's damage
	private Weapon weapon;
	//Potions heal the player. Player starts with 1 by default.
	private int potions;
	//Represents strength of player's attacks.
	private int attackPower;
	
	/**
	 * Default Constructor        
	 *
	 * <hr>
	 * Date created: Apr 15, 2016 
	 *
	 * 
	 */
	public Player()
	{
		//Default player has 100hp, 10% miss chance, and 5 strength(5 strength = 5 attackPower with unarmed weapon)
		super(100, .1, 5 );
		//player starts unarmed
		this.weapon = new Unarmed();
		//player starts with one potion
		this.potions = 1;
		//attack power = strength + bonus dmg from weapon. Default = 5
		setAttackPower();
		
	}
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Apr 15, 2016 
	 *
	 * 
	 * @param maxHealth
	 * @param missPct
	 * @param strength
	 * @param weapon
	 * @param potions
	 * @param attackPower
	 */
	public Player(int maxHealth, double missPct, int strength, Weapon weapon, int potions)
	{
		super(maxHealth, missPct, strength);
		this.weapon = weapon;
		this.potions = potions;	
		setAttackPower();
	}
	
	/**
	 * Sets the weapon for the player.         
	 *
	 * <hr>
	 * Date created: Apr 15, 2016
	 *
	 * <hr>
	 * @param weapon
	 */
	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}
	
	/**
	 * Returns the weapon the player has.      
	 *
	 * <hr>
	 * Date created: Apr 15, 2016
	 *
	 * <hr>
	 * @return
	 */
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	/**
	 * Sets the number of potions a player has.       
	 *
	 * <hr>
	 * Date created: Apr 15, 2016
	 *
	 * <hr>
	 * @param potions
	 */
	public void setPotions(int potions)
	{
		this.potions = potions;
	}
	
	/**
	 * Returns the number of potions the player has.        
	 *
	 * <hr>
	 * Date created: Apr 15, 2016
	 *
	 * <hr>
	 * @return potions
	 */
	public int getPotions()
	{
		return potions;
	}
	
	/**
	 * Sets attackPower. Based on default strength and current weapon.        
	 *
	 * <hr>
	 * Date created: Apr 15, 2016
	 *
	 * <hr>
	 * @param attackPower
	 */
	public void setAttackPower()
	{
		this.attackPower = strength + weapon.getDamageBonus ( );
	}
	
	/**
	 * Returns attackPower         
	 *
	 * <hr>
	 * Date created: Apr 15, 2016
	 *
	 * <hr>
	 * @return attackPower
	 */
	public int getAttackPower()
	{
		return attackPower;
	}
	
	/**
	 * Restores a portion of the Player's health.
	 * Not sure what the value of health restored should be. Making it 10hp for now.     
	 *
	 * <hr>
	 * Date created: Apr 14, 2016
	 *
	 * <hr>
	 */
	public void drinkPotion()
	{
		currentHealth += 10;
	}
	
	/**
	 * Increases the potions a player has by 1.         
	 *
	 * <hr>
	 * Date created: Apr 15, 2016
	 *
	 * <hr>
	 */
	public void addPotion()
	{
		potions++;
	}
	
	
	
	
	
	
	
	

}
