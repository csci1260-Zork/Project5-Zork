/**
 * ---------------------------------------------------------------------------
 * File name: Weapon.java
 * Project name: Project5-Zork
 * ---------------------------------------------------------------------------
 * Creator's name and email: Matthew Moore, zmjm40@goldmail.etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Apr 14, 2016
 * ---------------------------------------------------------------------------
 */

package dungeonCrawl;


/**
 * Abstract super class of weapon class that provide a damage bonus to the player.
 * To be randomly placed in dungeon. 
 *
 * <hr>
 * Date created: Apr 14, 2016
 * <hr>
 * @author Matthew Moore
 */
public abstract class Weapon
{
	protected int damageBonus;
	
	public int getDamageBonus()
	{
		return damageBonus;
	}

	
	public void setDamageBonus(int damageBonus)
	{
		this.damageBonus = damageBonus;
	}
	
}
