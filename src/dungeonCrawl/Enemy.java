/**
 * ---------------------------------------------------------------------------
 /**
* ---------------------------------------------------------------------------
 * File name: Enemy.java
 * Project name: Project5-Zork
 * ---------------------------------------------------------------------------
 * Creator's name and email: Allison Ivey, iveyas@goldmail.etsu.edu
 * Course:  CSCI 1260
 * Creation Date: Apr 13, 2016
 * ---------------------------------------------------------------------------
 */
package dungeonCrawl;

import java.util.Random;



/**
 * Enter type purpose here
 *
 * <hr>
 * Date created: Apr 13, 2016
 * <hr>
 * @author Allison Ivey
 */

public class Enemy extends Participants
{
	protected boolean hasPotion;
	
	/**
	 * Constructor        
	 *
	 * <hr>
	 * Date created: Apr 13, 2016 
	 *
	 * 
	 */
	public Enemy (int maxHealth, double missPct, int strength )
	{
		super(maxHealth, missPct, strength);
		Random ranNum = new Random();
		setHasPotion(ranNum.nextBoolean ( ));
	}

	public void setHasPotion(boolean hasPotion)
	{
		this.hasPotion = hasPotion;
	}

	public boolean getHasPotion()
	{
		return hasPotion;
	}
}

