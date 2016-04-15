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
 * Enter type purpose here
 *
 * <hr>
 * Date created: Apr 13, 2016
 * <hr>
 * @author Matthew Moore
 */
public class Player extends Participant
{
	private Weapon weapon;
	private int potions;
	private int attackPower;
	
	public Player()
	{
		
	}
	
	public Player(Weapon weapon, int potions)
	{
		
	}
	
	public void setWeapon(Weapon weapon){
		
	}
	
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	public void setPotions(int potions)
	{
		
	}
	
	public int getPotions()
	{
		return potions;
	}
	
	public void setAttackPower(int attackPower)
	{
		
	}
	
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
	
	public void addPotion()
	{
		potions++;
	}
	
	
	
	
	
	
	
	

}
