/* CRITTERS Critter4.java
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
//Rarely fights and doesn't reproduce
/**
 * Critter4 likes doing its own thing. It goes shopping whenever it faces conflict.
 *
 *
 */
public class Critter4 extends Critter {
    /**
     * Prints out 4 on grid for Critter4. Overrides Critter class method
     * @return String on grid
     */
    @Override
    public String toString()
    {
        return "4";
    }
    private int dir;
    private int countSteps;
    private boolean goneShopping;
    /**
     * Constructor method for Critter4
     *
     */
    public Critter4()
    {
        dir = 6;
        countSteps = 0;
        goneShopping=true;
    }
    /**
     * @param oponent the critter encountered
     * generates random number to decide if Critter will go shopping
     * Critter4 never wants to fight
     *
     */
    @Override
    public boolean fight(String oponent)
    {
        if(getRandomInt(10) > 4)
        {
            goneShopping=false;
            return true;
        }
        goneShopping=true;
        return false;
    }
    /**
     * runs every time step
     * increase count amount
     *
     */
    @Override
    public void doTimeStep()
    {
        this.countSteps++;
        run(getRandomInt(8));
    }
    /**
     * prints out the location of each Critter4 and whether it has gone shopping or not
     *@param c1 a list of all instances of Critter4 alive at the moment
     */
    public static void runStats(java.util.List<Critter> c1)
    {
        System.out.println("There are " + c1.size() + " Critter4s in the grid.");
        for(int i=0; i<c1.size(); i++)
        {
            System.out.println("Critter 4." + i + " is located at: (" + c1.get(i).getX_coord() + ", " + c1.get(i).getY_coord() + ")");
            Critter4 crit = (Critter4) c1.get(i);
            if(crit.goneShopping)
            {
                System.out.println("Looks like it went shopping for shirts!");
            }
            else
            {
                System.out.println("It stayed for the fight so it didn't go shopping for shirts");
            }
        }
    }
}