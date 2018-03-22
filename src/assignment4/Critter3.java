/* CRITTERS Critter3.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Rahul Gupta>
 * <rg43226>
 * Wed 9-10:30
 * Kaajol Dhana
 * krd985
 * Wed 9-10:30
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;
//Refuses to fight, flips a coin and walks or run if in a random direction instead of fighting
/**
 * Critter3 finds shirts wherever it goes. It moves left or right and doesn't reproduce.
 *
 *
 */
public class Critter3 extends Critter {
    /**
     * Prints out 3 on grid for Critter3. Overrides Critter class method
     * @return String on grid
     */
    @Override
    public String toString()
    {
        return "3";
    }
    private int dir;
    private int countSteps;
    private int numShirts;
    /**
     * Constructor method for Critter3
     *
     */
    public Critter3()
    {
        dir = getRandomInt(8);
        countSteps = 0;
        numShirts=0;
    }
    /**
     * returns number of shirts Critter3 has
     * @return int number of shirts
     */
    private int getShirts()
    {
        return numShirts;
    }
    /**
     * @param oponent the critter encountered
     * generates random number to direction critter wants to walk
     * Critter3 never wants to fight
     *
     */
    @Override
    public boolean fight(String oponent)
    {
        if(getRandomInt(2)==0)
            this.walk(getRandomInt(8));
        else
            this.walk(getRandomInt(8));
        return false;
    }
    /**
     * walks left or right
     * increase count and shirt amount
     *
     */
    @Override
    public void doTimeStep()
    {
        this.countSteps++;
        this.numShirts++;
        if(numShirts%2==0)
            walk(0);
        else
            walk(4);
    }
    /**
     * prints out the location of each Critter3 and how many shirts it has
     *@param c1 a list of all instances of Critter alive at the moment
     */
    public static void runStats(java.util.List<Critter> c1)
    {
        System.out.println("There are " + c1.size() + " Critter3s in the grid.");
        for(int i=0; i<c1.size(); i++)
        {
            System.out.println("Critter 3." + i + " is located at: (" + c1.get(i).getX_coord() + ", " + c1.get(i).getY_coord() + ")");
            Critter3 crit = (Critter3) c1.get(i);
            System.out.println("It also has " + crit.numShirts + " shirts!");
        }
    }
}