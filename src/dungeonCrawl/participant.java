/**
 * ---------------------------------------------------------------------------
 * File name: participant.java
 * Project name: Project5-Zork
 * ---------------------------------------------------------------------------
 * Creator's name and email: Allison Ivey, iveyas@goldmail.etsu.edu
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
 * @author Allison Ivey
 */
public class participant
{
	protected int maxHealth;
	protected int currentHealth;
	protected double missPct;
	protected int strength;
	
	public participant(int maxHealth, double missPct, int strength)
	{
		this.maxHealth = maxHealth;
		this.missPct = missPct;
		this.strength = strength;
	}
	
	public void setStrength(int strength)
	{
		this.strength = strength;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public void setMaxHealth(int maxHealth)
	{
		this.maxHealth = maxHealth;
	}
	
	public int getMaxHealth()
	{
		return maxHealth;
	}
	
	public void setMissPct(double missPct)
	{
		this.missPct = missPct;
	}
	
	public double getMissPct()
	{
		return missPct;
	}
	
	public void setCurrentHealth(int currentHealth)
	{
		this.currentHealth = currentHealth;
	}
	
	public int getCurrentHealth()
	{
		return currentHealth;
	}
	
	public boolean isAlive()
	{
		boolean isAlive = false;
		
		if(currentHealth <= 0)
		{
			isAlive = false;
		}
		
		return isAlive;
	}
	
	public void takeDamage(int damage)
	{
		currentHealth = currentHealth - damage;
	}
}
