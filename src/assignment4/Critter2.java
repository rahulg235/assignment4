package assignment4;
//can only move up (direction 0)
public class Critter2 extends Critter {
    @Override
    public String toString() {
        return "2";
    }

    private int dir;
    private int numSteps;


    public Critter2() {
        dir = getRandomInt(0);

    }

    @Override
    public boolean fight(String oponent) {
        numSteps++;
        walk(dir);
        return false;
    }

    @Override
    public void doTimeStep() {
        numSteps+=2;
        run(dir);

    }
}
    /*
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
*/
