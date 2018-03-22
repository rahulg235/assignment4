/* CRITTERS Critter1.java
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
//wants to reproduce alot
/**
 * Critter1 wants to populate the world with Critter1's. It does not change position but reproduces in each timestep.
 *
 *
 */
public class Critter1 extends Critter {
    /**
     * String representation of Critter1 used in displayWorld
     * @return String symbol representing the Critter
     */
    @Override
    public String toString(){
        return "1";
    }
    private int dir;
    private int countKids;
    /**
     * Constructor method for Critter1
     *
     */
    public Critter1(){
        dir = getRandomInt(8);
        countKids = 0;
    }
    /**
     * @param oponent
     * generates random number to decide if Critter will reproduce
     *Critter1 never wants to fight
     *
     */
    @Override
    public boolean fight(String oponent) {
        if(getRandomInt(10) > 4){
            Critter1 c = new Critter1();
            this.countKids++;
            reproduce(c,c.dir);
        }
        return false;
    }
    /**
     * reproduces in each timestep
     * number of kids a critter has is counted
     *
     */

    @Override
    public void doTimeStep() {
        Critter1 c = new Critter1();
        this.countKids++;
        reproduce(c,c.dir);
    }
    /**
     * prints out the average number of kids each Critter1 had
     *
     */
    public static void runStats(java.util.List<Critter> c1){
        int total_kids = 0;
        for(Object obj: c1){
            Critter1 crit1 = (Critter1) obj;
            total_kids+= crit1.countKids;
        }
        System.out.println("There were "+ c1.size() + " Critter1s");
        int avg = total_kids/c1.size();
        System.out.println("On average, each Critter1 had " + avg+ " kids");

    }
}
